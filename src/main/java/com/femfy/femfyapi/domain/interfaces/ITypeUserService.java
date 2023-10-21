package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

import com.femfy.femfyapi.delivery.dto.TypeUserDTO;

public interface ITypeUserService {
    public TypeUserDTO saveTypeUser(TypeUserDTO typeUserDTO);

    public TypeUserDTO updateTypeUser(TypeUserDTO typeUserDTO);

    public String deleteTypeUser(Long idTypeUser);

    public TypeUserDTO getTypeUser(Long idTypeUser);

    public List<TypeUserDTO> getTypeUsers();
}
