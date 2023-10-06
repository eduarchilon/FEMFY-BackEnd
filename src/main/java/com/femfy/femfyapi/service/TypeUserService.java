package com.femfy.femfyapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.TypeUser;
import com.femfy.femfyapi.repository.TypeUserRepository;

import dto.TypeUserDTO;

@Service
public class TypeUserService implements ITypeUserService {

    private TypeUserRepository typeUserRepository;
    @Autowired
    public TypeUserService(TypeUserRepository typeUserRepository) {
        this.typeUserRepository = typeUserRepository;
    }

    @Override
    public TypeUserDTO saveTypeUser(TypeUserDTO typeUserDTO) {
        try {
            TypeUser typeUser = new TypeUser();
            typeUser.setDescription(typeUserDTO.getDescription());
            this.typeUserRepository.save(typeUser);
        } catch (Exception e) {
            System.out.print("NO SE LOGRA HACER EL MAPEO");
            // TODO: handle exception
        }
        return typeUserDTO;
    }

    @Override
    public TypeUserDTO updateTypeUser(TypeUserDTO typeUserDTO) {
        try {
            TypeUser typeUser = new TypeUser();
            typeUser.setId(typeUserDTO.getIdTypeUser());
            typeUser.setDescription(typeUserDTO.getDescription());
            this.typeUserRepository.save(typeUser);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return typeUserDTO;
    }

    @Override
    public String deleteTypeUser(Long idTypeUser) {
        String responseDelete = "";
        try {
            this.typeUserRepository.deleteById(idTypeUser);
            responseDelete = "OK";
            return responseDelete;
        } catch (Exception e) {
            System.out.print("Error" + e.getMessage());
            responseDelete = "Error";
            return responseDelete;
            // TODO: handle exception
        }
    }

    @Override
    public TypeUserDTO getTypeUser(Long idTypeUser) {
        TypeUser typeUser = new TypeUser();
        TypeUserDTO typeUserDTO = new TypeUserDTO();
        try {
            typeUser = this.typeUserRepository.findById(idTypeUser).get();
            typeUserDTO.setIdTypeUser(typeUser.getId());
            typeUserDTO.setDescription(typeUser.getDescription());
            return typeUserDTO;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return typeUserDTO;
    }

    @Override
    public List<TypeUserDTO> getTypeUsers() {
        List<TypeUserDTO> typeUserList = new ArrayList<>();
        List<TypeUser> typeUserListdb = new ArrayList<>();
        typeUserListdb = this.typeUserRepository.findAll();

        for (TypeUser typeUser : typeUserListdb) {
            TypeUserDTO dto = new TypeUserDTO();
            dto.setIdTypeUser(typeUser.getId());
            dto.setDescription(typeUser.getDescription());
            typeUserList.add(dto);
        }
        return typeUserList;
    }
}
