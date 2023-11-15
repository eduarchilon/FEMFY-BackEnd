package com.femfy.femfyapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.QuestionsUserAnotherHormonalCauses;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserAnotherHormonalCausesService;
import com.femfy.femfyapi.domain.repository.QuestionsUserAnotherHormonalCausesRepository;

@Service
public class QuestionsUserAnotherHormonalCausesService implements IQuestionsUserAnotherHormonalCausesService {

    private final QuestionsUserAnotherHormonalCausesRepository questionsUserAnotherHormonalCausesRepository;

    public QuestionsUserAnotherHormonalCausesService(QuestionsUserAnotherHormonalCausesRepository questionsUserAnotherHormonalCausesRepository) {
        this.questionsUserAnotherHormonalCausesRepository = questionsUserAnotherHormonalCausesRepository;
    }

    @Override
    public List<QuestionsUserAnotherHormonalCauses> getAllQuestionsUserAnotherHormonalCauses() {
    	return questionsUserAnotherHormonalCausesRepository.findAll();
    }

    @Override
    public Optional<QuestionsUserAnotherHormonalCauses> getQuestionsUserAnotherHormonalCausesById(Long id) {
    	return questionsUserAnotherHormonalCausesRepository.findById(id);
    }

    @Override
    public QuestionsUserAnotherHormonalCauses saveQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCauses hormonalCauses) {
    	return questionsUserAnotherHormonalCausesRepository.save(hormonalCauses); 
    }

    @Override
    public QuestionsUserAnotherHormonalCauses updateQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCauses updated) {
        Long idToUpdate = updated.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserAnotherHormonalCauses existingHormonalCauses = findQuestionsUserAnotherHormonalCausesById(idToUpdate);

        updateHormonalCausesFields(existingHormonalCauses, updated);

        existingHormonalCauses = questionsUserAnotherHormonalCausesRepository.save(existingHormonalCauses);

        return existingHormonalCauses;
    }

    @Override
    public void deleteQuestionsUserAnotherHormonalCauses(Long id) {
        questionsUserAnotherHormonalCausesRepository.deleteById(id);
    }

    private QuestionsUserAnotherHormonalCauses findQuestionsUserAnotherHormonalCausesById(Long id) {
        return questionsUserAnotherHormonalCausesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Causas Hormonales no encontrada con el ID: " + id));
    }

    private void updateHormonalCausesFields(QuestionsUserAnotherHormonalCauses existingHormonalCauses, QuestionsUserAnotherHormonalCauses updated) {
        if (updated == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo para la actualización.");
        }

        if (updated.getPolycysticOvarySyndrome() != null) {
        	existingHormonalCauses.setPolycysticOvarySyndrome(updated.getPolycysticOvarySyndrome());
        }

        if (updated.getHypothyroidism() != null) {
        	existingHormonalCauses.setHypothyroidism(updated.getHypothyroidism());
        }

        if (updated.getHyperprolactinemia() != null) {
        	existingHormonalCauses.setHyperprolactinemia(updated.getHyperprolactinemia());
        }

        if (updated.getSheehanSyndrome() != null) {
        	existingHormonalCauses.setSheehanSyndrome(updated.getSheehanSyndrome());
        }

        if (updated.getPrematureOvarianFailure() != null) {
        	existingHormonalCauses.setPrematureOvarianFailure(updated.getPrematureOvarianFailure());
        }

        if (updated.getHypothalamicDisorders() != null) {
        	existingHormonalCauses.setHypothalamicDisorders(updated.getHypothalamicDisorders());
        }

        if (updated.getInsulinResistance() != null) {
        	existingHormonalCauses.setInsulinResistance(updated.getInsulinResistance());
        }

        if (updated.getAnother() != null) {
        	existingHormonalCauses.setAnother(updated.getAnother());
        }

        if (updated.getAnotherDescription() != null) {
        	existingHormonalCauses.setAnotherDescription(updated.getAnotherDescription());
        }
    }

	@Override
	public List<QuestionsUserAnotherHormonalCauses> getAQuestionsUserAnotherHormonalCausesByUserId(Long id) {
		return questionsUserAnotherHormonalCausesRepository.findByUserId(id);
	}
}