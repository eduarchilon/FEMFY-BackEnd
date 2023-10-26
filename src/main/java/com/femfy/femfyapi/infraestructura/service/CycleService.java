package com.femfy.femfyapi.infraestructura.service;

import com.femfy.femfyapi.infraestructura.Utils;
import com.femfy.femfyapi.domain.entity.Cycle;
import com.femfy.femfyapi.domain.interfaces.ICycleService;
import com.femfy.femfyapi.domain.exception.CustomException;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.repository.CycleRepository;
import com.femfy.femfyapi.delivery.dto.CycleDTO;
import com.femfy.femfyapi.infraestructura.mapper.CycleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CycleService implements ICycleService {


    private final CycleRepository cycleRepository;

    @Autowired
    public CycleService(CycleRepository cycleRepository){
        this.cycleRepository = cycleRepository;
    }

    @Override
    public Cycle registerCycleStart(Cycle cycleDto) throws Exception {
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
    public Cycle registerCycleEnd(Cycle cycleDto) throws IOException, CustomException {
        if (cycleDto.getId() == null) {
            throw new IllegalArgumentException("El ID es requerido para esta operacion");
        }

        try{
            Cycle cycleBD = cycleRepository.findById(cycleDto.getId()).orElseThrow(() -> new CustomException("No se encontró el ciclo")) ;

            cycleBD.setDateEnd(cycleDto.getDateEnd());

            cycleRepository.save(cycleBD);

            return cycleBD;
        }catch (Exception e){
            throw new CustomException("Error al registrar fin del ciclo: " + e.getMessage());
        }
    }

    @Override
    public List<Cycle> getCycleHistory(Long idUser) throws CustomException {
        if (idUser == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido para esta operacion");
        }

        try {

            return cycleRepository.findAllByIdUser(idUser);

        }catch (Exception e) {
            throw new CustomException("Error al obtener el historial de ciclos: " + e.getMessage());
        }
    }

    @Override
    public Cycle getCycleByIdUserAndDateBeging(Long idUser, String dateBeging) throws CustomException {
        Cycle cycle;
        if (idUser == null || dateBeging == null) {
            throw new IllegalArgumentException("El ID de usuario y la fecha de inicio son requeridos para esta operacion");
        }

        try {
            Date dateSql = Utils.parseDate(dateBeging);
            cycle = cycleRepository.findByIdUserAndDateBeging(idUser, dateSql);

        } catch (Exception e) {
            throw new CustomException("Error al obtener el ciclo del usuario: " + e.getMessage());
        }

        return cycle;
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
    public Cycle updateCycle(Cycle cycle) throws IllegalArgumentException {
        Long idToUpdate = cycle.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        Cycle cycleDB = cycleRepository.findById(idToUpdate).orElseThrow();
        copyProperties(cycle, cycleDB);

        return cycleRepository.save(cycleDB);
    }



    private void copyProperties(Cycle cycle, Cycle cycleDB){
        if(cycle.getStatus() != null) cycleDB.setStatus(cycle.getStatus());
        if(cycle.getDateBeging() != null) cycleDB.setDateBeging(cycle.getDateBeging());
        if(cycle.getDateEnd() != null) cycleDB.setDateEnd(cycle.getDateEnd());
        if(cycle.getIdUser() != null) cycleDB.setIdUser(cycle.getIdUser());
        if(cycle.getDaysOfBleeding() != null) cycleDB.setDaysOfBleeding(cycle.getDaysOfBleeding());
    }


}
