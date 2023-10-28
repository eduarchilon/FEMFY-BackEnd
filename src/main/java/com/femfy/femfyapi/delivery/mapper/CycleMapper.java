package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.CycleDTO;
import com.femfy.femfyapi.domain.entity.Cycle;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.infraestructura.Utils;

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
        dto.setDateEnd(cycle.getDateEnd() != null ? cycle.getDateEnd().toString() : null);
        dto.setDateBeging(cycle.getDateBeging() != null? cycle.getDateBeging().toString() : null);
        dto.setDaysOfBleeding(cycle.getDaysOfBleeding());
        return dto;
    }

    public static Cycle mapToEntity(CycleDTO dto) {
        Cycle cycle = new Cycle();
        cycle.setIdUser(dto.getIdUser());
        cycle.setId(dto.getId());
        cycle.setStatus(dto.getStatus());
        cycle.setDaysOfBleeding(dto.getDaysOfBleeding());
        cycle.setDateEnd( dto.getDateEnd() != null ? Utils.parseDate(dto.getDateEnd()) : null);
        cycle.setDateBeging(dto.getDateBeging() != null ? Utils.parseDate(dto.getDateBeging()) : null);

        return cycle;
    }
}
