package com.femfy.femfyapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.TypeUser;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.exception.IntegreteDataViolationException;
import com.femfy.femfyapi.domain.interfaces.ITypeUserService;
import com.femfy.femfyapi.domain.repository.TypeUserRepository;


@Service
public class TypeUserService implements ITypeUserService {

    private final TypeUserRepository typeUserRepository;

    public TypeUserService(TypeUserRepository typeUserRepository) {
        this.typeUserRepository = typeUserRepository;
    }

    @Override
    public TypeUser saveTypeUser(TypeUser typeUser) {
        return this.typeUserRepository.save(typeUser);
    }

    @Override
    public TypeUser updateTypeUser(TypeUser typeUser) {
        return this.typeUserRepository.save(typeUser);
    }

    @Override
    public void deleteTypeUser(Long idTypeUser) {
        this.typeUserRepository.deleteById(idTypeUser);
    }

    @Override
    public TypeUser getTypeUser(Long idTypeUser) {
        return this.typeUserRepository.findById(idTypeUser).orElseGet(TypeUser::new);
    }

    @Override
    public List<TypeUser> getTypeUsers() {
        return this.typeUserRepository.findAll();
    }

}