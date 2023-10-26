package com.femfy.femfyapi.infraestructura.mapper;

import com.femfy.femfyapi.delivery.dto.CycleDTO;
import com.femfy.femfyapi.domain.entity.Cycle;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class CycleMapper {

    public static CycleDTO mapToDTO (Cycle cycle){
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

    public static Cycle mapToEntity(CycleDTO dto) {
        Cycle cycle = new Cycle();
        cycle.setId(dto.getIdUser());
        cycle.setId(dto.getId());
        cycle.setStatus(dto.getStatus());
        cycle.setDaysOfBleeding(dto.getDaysOfBleeding());
        cycle.setDateEnd(dto.getDateEnd());
        cycle.setDateBeging(dto.getDateBeging());

        return cycle;
    }
}
