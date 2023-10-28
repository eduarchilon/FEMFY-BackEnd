package com.femfy.femfyapi.infraestructura.mapper;

import com.femfy.femfyapi.delivery.dto.TypeStudyDTO;
import com.femfy.femfyapi.domain.entity.TypeStudy;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class TypeStudyMapper {

    public static TypeStudyDTO mapToDTO(TypeStudy typeStudy){
        if(typeStudy == null){
            throw new EntityNotFoundException("Tipo de estudio no encontrado");
        }

        TypeStudyDTO dto = new TypeStudyDTO();
        dto.setIdTypeStudy(typeStudy.getId());
        dto.setDescription(typeStudy.getDescription());

        return dto;
    }

    public static TypeStudy mapToEntity(TypeStudyDTO dto){
        TypeStudy typeStudy = new TypeStudy();
        typeStudy.setId(dto.getIdTypeStudy());
        typeStudy.setDescription(dto.getDescription());

        return typeStudy;

    }
}
