package com.femfy.femfyapi.infraestructura.service;

import java.util.List;
import java.util.stream.Collectors;

import com.femfy.femfyapi.domain.interfaces.ITypeStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.TypeStudy;
import com.femfy.femfyapi.domain.repository.TypeStudyRepository;

import com.femfy.femfyapi.delivery.dto.TypeStudyDTO;

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
        } catch (EmptyResultDataAccessException e) {
            return "Error: No se encontró ningún registro con el ID proporcionado.";
        } catch (DataIntegrityViolationException e) {
            return "Error: No se puede eliminar este registro debido a restricciones de integridad de datos.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
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