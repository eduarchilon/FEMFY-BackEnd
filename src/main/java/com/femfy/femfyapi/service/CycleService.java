package com.femfy.femfyapi.service;

import com.femfy.femfyapi.Utils;
import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.exception.CustomException;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.CycleRepository;
import dto.CycleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CycleService implements ICycleService{

    @Autowired
    CycleRepository cycleRepository;

    @Override
    public CycleDTO registerCycleStart(Cycle cycle) throws Exception {
        try{
            CycleDTO dto = new CycleDTO();
            dto.setStatus(cycle.getStatus());
            dto.setIdUser(cycle.getIdUser());
            dto.setDaysOfBleeding(cycle.getDaysOfBleeding());
            dto.setDateBeging(cycle.getDateBeging());
            
            cycleRepository.save(cycle);
            
            dto.setId(cycle.getId());
            return dto;
        }catch (Exception e){
            throw new CustomException("Error al registrar inicio del ciclo: " + e.getMessage());
        }
    }

    @Override
    public CycleDTO registerCycleEnd(Cycle cycle) throws IOException, CustomException {
        try{
            CycleDTO cycleDTO = this.getCycleByIdUserAndDateBeging(cycle.getIdUser(), String.valueOf(cycle.getDateBeging()));
            cycle.setDateBeging(Utils.parseDate(String.valueOf(cycle.getDateBeging())));
            cycleDTO.setDateEnd(cycle.getDateEnd());
            cycle.setId(cycleDTO.getId());
            cycle.setDaysOfBleeding(cycleDTO.getDaysOfBleeding());
            cycleRepository.save(cycle);
            return cycleDTO;
        }catch (Exception e){
            throw new CustomException("Error al registrar fin del ciclo: " + e.getMessage());
        }
    }

    @Override
    public List<CycleDTO> getCycleHistory(Long idUser) throws CustomException {
        List<CycleDTO> dtoList = new ArrayList<>();
        List<Cycle> cycleList = new ArrayList<>();
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
    public String deleteCycle(Long id) throws CustomException {
        try{
            cycleRepository.deleteById(id);
            return "OK";
        }catch (Exception e ){
            throw new CustomException("Error al eliminar ciclo; " + e.getMessage());
        }

    }

    @Override
    public CycleDTO updateCycle(CycleDTO cycleDTO) throws CustomException {
        Long idToUpdate = cycleDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        Cycle cycleDB = cycleRepository.findById(idToUpdate).orElseThrow();
        cycleDB.setStatus(cycleDTO.getStatus());
        cycleDB.setDateEnd(Utils.parseDate(String.valueOf(cycleDTO.getDateEnd())));
        cycleDB.setDateBeging(Utils.parseDate(String.valueOf(cycleDTO.getDateBeging())));
        cycleDB.setDaysOfBleeding(cycleDTO.getDaysOfBleeding());

        Cycle cycle = cycleRepository.save(cycleDB);

        return mapToDTO(cycle);
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


}
