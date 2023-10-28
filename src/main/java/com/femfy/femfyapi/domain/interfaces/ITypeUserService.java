package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

import com.femfy.femfyapi.domain.entity.TypeUser;


public interface ITypeUserService {
    public TypeUser saveTypeUser(TypeUser typeUser);

    public TypeUser updateTypeUser(TypeUser typeUser);

    public String deleteTypeUser(Long idTypeUser);

    public TypeUser getTypeUser(Long idTypeUser);

    public List<TypeUser> getTypeUsers();
}
