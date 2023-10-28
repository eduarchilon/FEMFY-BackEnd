package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.TypeUserDTO;
import com.femfy.femfyapi.domain.entity.TypeUser;

public class TypeUserMapper {

    public static TypeUserDTO mapToDTO(TypeUser typeUser) {
        TypeUserDTO dto = new TypeUserDTO();
        dto.setIdTypeUser(typeUser.getId());
        dto.setDescription(typeUser.getDescription());
        return dto;
    }

    public static TypeUser mapToEntity(TypeUserDTO typeUserDTO) {
        TypeUser typeUser = new TypeUser();
        typeUser.setId(typeUserDTO.getIdTypeUser());
        typeUser.setDescription(typeUserDTO.getDescription());
        return typeUser;
    }
}
