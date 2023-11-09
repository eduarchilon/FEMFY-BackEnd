package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserAnotherHormonalCauses;
import com.femfy.femfyapi.repository.QuestionsUserAnotherHormonalCausesRepository;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import dto.QuestionsUserAnotherHormonalCausesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserAnotherHormonalCausesService implements IQuestionsUserAnotherHormonalCausesService {

    private final QuestionsUserAnotherHormonalCausesRepository questionsUserAnotherHormonalCausesRepository;

    @Autowired
    public QuestionsUserAnotherHormonalCausesService(QuestionsUserAnotherHormonalCausesRepository questionsUserAnotherHormonalCausesRepository) {
        this.questionsUserAnotherHormonalCausesRepository = questionsUserAnotherHormonalCausesRepository;
    }

    @Override
    public List<QuestionsUserAnotherHormonalCausesDTO> getAllQuestionsUserAnotherHormonalCauses() {
        List<QuestionsUserAnotherHormonalCauses> entities = questionsUserAnotherHormonalCausesRepository.findAll();
        return entities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserAnotherHormonalCausesDTO> getQuestionsUserAnotherHormonalCausesById(Long id) {
        Optional<QuestionsUserAnotherHormonalCauses> entity = questionsUserAnotherHormonalCausesRepository.findById(id);
        return entity.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserAnotherHormonalCausesDTO saveQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO dto) {
        QuestionsUserAnotherHormonalCauses entity = mapToEntity(dto);
        entity = questionsUserAnotherHormonalCausesRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public QuestionsUserAnotherHormonalCausesDTO updateQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserAnotherHormonalCauses existingEntity = findQuestionsUserAnotherHormonalCausesById(idToUpdate);

        updateQuestionsUserAnotherHormonalCausesFields(existingEntity, updatedDTO);

        existingEntity = questionsUserAnotherHormonalCausesRepository.save(existingEntity);

        return mapToDTO(existingEntity);
    }

    @Override
    public void deleteQuestionsUserAnotherHormonalCauses(Long id) {
        questionsUserAnotherHormonalCausesRepository.deleteById(id);
    }

    private QuestionsUserAnotherHormonalCausesDTO mapToDTO(QuestionsUserAnotherHormonalCauses entity) {
        if (entity == null) {
            throw new EntityNotFoundException("Entidad no encontrada");
        }

        QuestionsUserAnotherHormonalCausesDTO dto = new QuestionsUserAnotherHormonalCausesDTO();
        dto.setId(entity.getId());

        dto.setPolycysticOvarySyndrome(entity.isPolycysticOvarySyndrome());
        dto.setHypothyroidism(entity.isHypothyroidism());
        dto.setHyperprolactinemia(entity.isHyperprolactinemia());
        dto.setSheehanSyndrome(entity.isSheehanSyndrome());
        dto.setPrematureOvarianFailure(entity.isPrematureOvarianFailure());
        dto.setHypothalamicDisorders(entity.isHypothalamicDisorders());
        dto.setInsulinResistance(entity.isInsulinResistance());
        dto.setAnother(entity.isAnother());
        dto.setAnotherDescription(entity.getAnotherDescription());

        return dto;
    }

    private QuestionsUserAnotherHormonalCauses mapToEntity(QuestionsUserAnotherHormonalCausesDTO dto) {
        QuestionsUserAnotherHormonalCauses entity = new QuestionsUserAnotherHormonalCauses();
        entity.setId(dto.getId());

        entity.setPolycysticOvarySyndrome(dto.isPolycysticOvarySyndrome());
        entity.setHypothyroidism(dto.isHypothyroidism());
        entity.setHyperprolactinemia(dto.isHyperprolactinemia());
        entity.setSheehanSyndrome(dto.isSheehanSyndrome());
        entity.setPrematureOvarianFailure(dto.isPrematureOvarianFailure());
        entity.setHypothalamicDisorders(dto.isHypothalamicDisorders());
        entity.setInsulinResistance(dto.isInsulinResistance());
        entity.setAnother(dto.isAnother());
        entity.setAnotherDescription(dto.getAnotherDescription());

        return entity;
    }

    private QuestionsUserAnotherHormonalCauses findQuestionsUserAnotherHormonalCausesById(Long id) {
        return questionsUserAnotherHormonalCausesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
    }

    private void updateQuestionsUserAnotherHormonalCausesFields(QuestionsUserAnotherHormonalCauses existingEntity, QuestionsUserAnotherHormonalCausesDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo para la actualización.");
        }

        if (dto.isPolycysticOvarySyndrome()) {
            existingEntity.setPolycysticOvarySyndrome(dto.isPolycysticOvarySyndrome());
        }

        if (dto.isHypothyroidism()) {
            existingEntity.setHypothyroidism(dto.isHypothyroidism());
        }

        if (dto.isHyperprolactinemia()) {
            existingEntity.setHyperprolactinemia(dto.isHyperprolactinemia());
        }

        if (dto.isSheehanSyndrome()) {
            existingEntity.setSheehanSyndrome(dto.isSheehanSyndrome());
        }

        if (dto.isPrematureOvarianFailure()) {
            existingEntity.setPrematureOvarianFailure(dto.isPrematureOvarianFailure());
        }

        if (dto.isHypothalamicDisorders()) {
            existingEntity.setHypothalamicDisorders(dto.isHypothalamicDisorders());
        }

        if (dto.isInsulinResistance()) {
            existingEntity.setInsulinResistance(dto.isInsulinResistance());
        }

        if (dto.isAnother()) {
            existingEntity.setAnother(dto.isAnother());
        }

        if (dto.getAnotherDescription() != null) {
            existingEntity.setAnotherDescription(dto.getAnotherDescription());
        }
    }
}