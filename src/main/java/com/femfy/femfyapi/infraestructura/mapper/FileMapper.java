package com.femfy.femfyapi.infraestructura.mapper;

import com.femfy.femfyapi.delivery.dto.FileDTO;
import com.femfy.femfyapi.domain.entity.FileUser;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class FileMapper {

    public static FileDTO mapToDTO (FileUser file){
        if(file == null){
            throw new EntityNotFoundException("Archivo no encontrado");
        }

        FileDTO dto = new FileDTO();
        dto.setIdFile(file.getId());

        if(file.getIdUser() != null){
            dto.setIdUser(file.getIdUser());
        }

        dto.setFileExt(file.getFileExt());
        dto.setDescription(file.getDescription());
        dto.setStudyDate(file.getStudyDate());
        dto.setFileName(file.getFileName());
        dto.setTypeStudy(TypeStudyMapper.mapToDTO(file.getTypeStudy()));
        return dto;
    }

    public static FileUser mapToEntity(FileDTO dto) {
        FileUser file = new FileUser();
        file.setFileExt(dto.getFileExt());
        file.setDescription(dto.getDescription());
        file.setStudyDate(dto.getStudyDate());
        file.setFileName(dto.getFileName());
        file.setTypeStudy(TypeStudyMapper.mapToEntity(dto.getTypeStudy()));

        return file;
    }
}
