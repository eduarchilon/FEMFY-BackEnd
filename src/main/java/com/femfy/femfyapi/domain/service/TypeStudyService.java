package com.femfy.femfyapi.domain.service;

import java.util.List;


import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.ITypeStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.TypeStudy;
import com.femfy.femfyapi.domain.repository.TypeStudyRepository;



@Service
public class TypeStudyService implements ITypeStudyService {

    private final TypeStudyRepository typeStudyRepository;

    @Autowired
    public TypeStudyService(TypeStudyRepository typeStudyRepository) {
        this.typeStudyRepository = typeStudyRepository;
    }

    @Override
    public TypeStudy saveTypeStudy(TypeStudy typeStudy) {
        return this.typeStudyRepository.save(typeStudy);
    }
    
    
    @Override
    public TypeStudy updateTypeStudy(TypeStudy typeStudy){
        return this.typeStudyRepository.save(typeStudy);
    }

    @Override
    public String deleteTypeStudy(Long idTypeStudy) {
        try {
            this.typeStudyRepository.deleteById(idTypeStudy);
            return "OK";
        } catch (EntityNotFoundException e) {
            return "Error: No se encontró ningún registro con el ID proporcionado.";
        }
    }

    @Override
    public TypeStudy getTypeStudy(Long idTypeStudy) {
        return this.typeStudyRepository.findById(idTypeStudy).orElseGet(TypeStudy::new);
    }

    @Override
    public List<TypeStudy> getTypeStudies() {
        return this.typeStudyRepository.findAll();
    }


}