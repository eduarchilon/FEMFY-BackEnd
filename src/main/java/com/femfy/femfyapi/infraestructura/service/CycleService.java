package com.femfy.femfyapi.infraestructura.service;

import com.femfy.femfyapi.infraestructura.Utils;
import com.femfy.femfyapi.domain.entity.Cycle;
import com.femfy.femfyapi.domain.interfaces.ICycleService;
import com.femfy.femfyapi.domain.exception.CustomException;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.repository.CycleRepository;
import com.femfy.femfyapi.delivery.dto.CycleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CycleService implements ICycleService {

    @Autowired
    CycleRepository cycleRepository;

    @Override
    public CycleDTO registerCycleStart(CycleDTO cycleDto) throws Exception {
        try{
            Cycle cycle = new Cycle();
            copyProperties(cycleDto, cycle);
            cycleRepository.save(cycle);

            cycleDto.setId(cycle.getId());
            return cycleDto;
        }catch (Exception e){
            throw new CustomException("Error al registrar inicio del ciclo: " + e.getMessage());
        }
    }

    @Override
    public CycleDTO registerCycleEnd(CycleDTO cycleDto) throws IOException, CustomException {
        if (cycleDto.getId() == null) {
            throw new IllegalArgumentException("El ID es requerido para esta operacion");
        }

        try{
            Cycle cycleBD = cycleRepository.findById(cycleDto.getId()).orElseThrow(() -> new CustomException("No se encontró el ciclo")) ;

            cycleBD.setDateEnd(Utils.parseDate(String.valueOf(cycleDto.getDateEnd())));

            cycleRepository.save(cycleBD);

            return mapToDTO(cycleBD);
        }catch (Exception e){
            throw new CustomException("Error al registrar fin del ciclo: " + e.getMessage());
        }
    }

    @Override
    public List<CycleDTO> getCycleHistory(Long idUser) throws CustomException {
        List<CycleDTO> dtoList = new ArrayList<>();
        List<Cycle> cycleList;
        if (idUser == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido para esta operacion");
        }

        try {

            cycleList = cycleRepository.findAllByIdUser(idUser);

            for(Cycle cycle : cycleList){
                CycleDTO dto = new CycleDTO();
                dto.setStatus(cycle.getStatus());
                dto.setDateBeging((cycle.getDateBeging()));
                dto.setIdUser(cycle.getIdUser());
                dto.setDaysOfBleeding(cycle.getDaysOfBleeding());
                dto.setDateEnd((cycle.getDateEnd()));
                dto.setId(cycle.getId());

                dtoList.add(dto);
            }
        }catch (Exception e) {
            throw new CustomException("Error al obtener el historial de ciclos: " + e.getMessage());
        }
        return dtoList;
    }

    @Override
    public CycleDTO getCycleByIdUserAndDateBeging(Long idUser, String dateBeging) throws CustomException {
        CycleDTO cycleDTO = new CycleDTO();
        if (idUser == null && dateBeging == null) {
            throw new IllegalArgumentException("El ID de usuario y la fecha de inicio son requeridos para esta operacion");
        }

        try{
            Date dateSql = Utils.parseDate(dateBeging);
            Cycle cycle =  cycleRepository.findByIdUserAndDateBeging(idUser, dateSql);
            cycleDTO.setDateBeging((cycle.getDateBeging()));
            cycleDTO.setStatus(cycle.getStatus());
            cycleDTO.setIdUser(cycle.getIdUser());
            cycleDTO.setDaysOfBleeding(cycle.getDaysOfBleeding());
            cycleDTO.setId(cycle.getId());

        }catch (Exception e){
            throw new CustomException("Error al obtener el ciclo del usuario: " + e.getMessage());
        }
        return cycleDTO;
    }

    @Override
    public Map<String, String> deleteCycle(Long id) throws CustomException {
        if (id == null) {
            throw new IllegalArgumentException("El ID es requerido para esta operacion");
        }
        try{
            cycleRepository.deleteById(id);
            Map<String, String> res = new HashMap<>();
            res.put("Response", "OK");
            return res;
        }catch (Exception e ){
            throw new CustomException("Error al eliminar ciclo; " + e.getMessage());
        }

    }

    @Override
    public CycleDTO updateCycle(CycleDTO cycleDto) throws CustomException {
        Long idToUpdate = cycleDto.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        Cycle cycleDB = cycleRepository.findById(idToUpdate).orElseThrow();
        copyProperties(cycleDto, cycleDB);
        Cycle cycleUpdate = cycleRepository.save(cycleDB);

        return mapToDTO(cycleUpdate);
    }

    private CycleDTO mapToDTO (Cycle cycle){
        if(cycle == null){
            throw new EntityNotFoundException("Ciclo no encontrado");
        }

        CycleDTO dto = new CycleDTO();
        dto.setId(cycle.getId());

        if(cycle.getIdUser() != null){
            dto.setIdUser(cycle.getIdUser());
        }

        dto.setStatus(cycle.getStatus());
        dto.setDateEnd(cycle.getDateEnd());
        dto.setDateBeging(cycle.getDateBeging());
        dto.setDaysOfBleeding(cycle.getDaysOfBleeding());
        return dto;
    }

    private void copyProperties(CycleDTO dto, Cycle cycle) throws CustomException {
        if(dto.getStatus() != null) cycle.setStatus(dto.getStatus());
        if(dto.getDateBeging() != null) cycle.setDateBeging(Utils.parseDate(dto.getDateBeging().toString()));
        if(dto.getDateEnd() != null) cycle.setDateEnd(Utils.parseDate(String.valueOf(dto.getDateEnd())));
        if(dto.getIdUser() != null) cycle.setIdUser(dto.getIdUser());
        if(dto.getDaysOfBleeding() != null) cycle.setDaysOfBleeding(dto.getDaysOfBleeding());
    }


}
