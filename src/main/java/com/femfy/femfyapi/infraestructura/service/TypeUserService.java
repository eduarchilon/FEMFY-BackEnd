package com.femfy.femfyapi.infraestructura.service;

import com.femfy.femfyapi.domain.interfaces.ITypeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.femfy.femfyapi.domain.entity.TypeUser;
import com.femfy.femfyapi.domain.repository.TypeUserRepository;

import com.femfy.femfyapi.delivery.dto.TypeUserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeUserService implements ITypeUserService {

    private final TypeUserRepository typeUserRepository;

    @Autowired
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
    public String deleteTypeUser(Long idTypeUser) {
        try {
            this.typeUserRepository.deleteById(idTypeUser);
            return "OK";
        } catch (EmptyResultDataAccessException e) {
            return "Error: No se encontró ningún registro con el ID proporcionado.";
        } catch (DataIntegrityViolationException e) {
            return "Error: No se puede eliminar este registro debido a restricciones de integridad de datos.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
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