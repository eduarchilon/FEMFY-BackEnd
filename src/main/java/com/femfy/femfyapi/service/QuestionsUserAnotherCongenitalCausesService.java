package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserAnotherCongenitalCauses;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.QuestionsUserAnotherCongenitalCausesRepository;
import dto.QuestionsUserAnotherCongenitalCausesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserAnotherCongenitalCausesService implements IQuestionsUserAnotherCongenitalCausesService {

    private final QuestionsUserAnotherCongenitalCausesRepository congenitalCausesRepository;

    @Autowired
    public QuestionsUserAnotherCongenitalCausesService(QuestionsUserAnotherCongenitalCausesRepository congenitalCausesRepository) {
        this.congenitalCausesRepository = congenitalCausesRepository;
    }

    @Override
    public List<QuestionsUserAnotherCongenitalCausesDTO> getAllQuestionsUserAnotherCongenitalCauses() {
        List<QuestionsUserAnotherCongenitalCauses> congenitalCausesList = congenitalCausesRepository.findAll();
        return congenitalCausesList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserAnotherCongenitalCausesDTO> getQuestionsUserAnotherCongenitalCausesById(Long id) {
        Optional<QuestionsUserAnotherCongenitalCauses> congenitalCauses = congenitalCausesRepository.findById(id);
        return congenitalCauses.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserAnotherCongenitalCausesDTO saveQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCausesDTO congenitalCausesDTO) {
        QuestionsUserAnotherCongenitalCauses congenitalCauses = mapToEntity(congenitalCausesDTO);
        congenitalCauses = congenitalCausesRepository.save(congenitalCauses);
        return mapToDTO(congenitalCauses);
    }

    @Override
    public QuestionsUserAnotherCongenitalCausesDTO updateQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCausesDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserAnotherCongenitalCauses existingCongenitalCauses = findCongenitalCausesById(idToUpdate);

        updateCongenitalCausesFields(existingCongenitalCauses, updatedDTO);

        existingCongenitalCauses = congenitalCausesRepository.save(existingCongenitalCauses);

        return mapToDTO(existingCongenitalCauses);
    }

    @Override
    public void deleteQuestionsUserAnotherCongenitalCauses(Long id) {
        congenitalCausesRepository.deleteById(id);
    }

    private QuestionsUserAnotherCongenitalCausesDTO mapToDTO(QuestionsUserAnotherCongenitalCauses congenitalCauses) {
        if (congenitalCauses == null) {
            throw new EntityNotFoundException("Causas congénitas de la usuaria no encontradas");
        }

        QuestionsUserAnotherCongenitalCausesDTO dto = new QuestionsUserAnotherCongenitalCausesDTO();
        dto.setId(congenitalCauses.getId());

        if (congenitalCauses.isMalformationsUterine()) {
            dto.setMalformationsUterine(true);
        }

        if (congenitalCauses.isTurnerSyndrome()) {
            dto.setTurnerSyndrome(true);
        }

        if (congenitalCauses.isAnother()) {
            dto.setAnother(true);
        }

        if (congenitalCauses.getAnotherDescription() != null) {
            dto.setAnotherDescription(congenitalCauses.getAnotherDescription());
        }

        return dto;
    }

    private QuestionsUserAnotherCongenitalCauses mapToEntity(QuestionsUserAnotherCongenitalCausesDTO dto) {
        QuestionsUserAnotherCongenitalCauses congenitalCauses = new QuestionsUserAnotherCongenitalCauses();
        congenitalCauses.setId(dto.getId());

        congenitalCauses.setMalformationsUterine(dto.isMalformationsUterine());
        congenitalCauses.setTurnerSyndrome(dto.isTurnerSyndrome());
        congenitalCauses.setAnother(dto.isAnother());
        congenitalCauses.setAnotherDescription(dto.getAnotherDescription());

        return congenitalCauses;
    }

    private QuestionsUserAnotherCongenitalCauses findCongenitalCausesById(Long id) {
        return congenitalCausesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Causas congénitas de la usuaria no encontradas con el ID: " + id));
    }

    private void updateCongenitalCausesFields(QuestionsUserAnotherCongenitalCauses existingCongenitalCauses, QuestionsUserAnotherCongenitalCausesDTO updatedDTO) {
        if (updatedDTO.isMalformationsUterine()) {
            existingCongenitalCauses.setMalformationsUterine(updatedDTO.isMalformationsUterine());
        }

        if (updatedDTO.isTurnerSyndrome()) {
            existingCongenitalCauses.setTurnerSyndrome(updatedDTO.isTurnerSyndrome());
        }

        if (updatedDTO.isAnother()) {
            existingCongenitalCauses.setAnother(updatedDTO.isAnother());
        }

        if (updatedDTO.getAnotherDescription() != null) {
            existingCongenitalCauses.setAnotherDescription(updatedDTO.getAnotherDescription());
        }
    }
}