package com.femfy.femfyapi.domain.service;

import com.femfy.femfyapi.domain.entity.QuestionsUserAnotherCongenitalCauses;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserAnotherCongenitalCausesService;
import com.femfy.femfyapi.domain.repository.QuestionsUserAnotherCongenitalCausesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsUserAnotherCongenitalCausesService implements IQuestionsUserAnotherCongenitalCausesService {

    private final QuestionsUserAnotherCongenitalCausesRepository congenitalCausesRepository;

    public QuestionsUserAnotherCongenitalCausesService(QuestionsUserAnotherCongenitalCausesRepository congenitalCausesRepository) {
        this.congenitalCausesRepository = congenitalCausesRepository;
    }

    @Override
    public List<QuestionsUserAnotherCongenitalCauses> getAllQuestionsUserAnotherCongenitalCauses() {
    	return congenitalCausesRepository.findAll();
        
    }

    @Override
    public Optional<QuestionsUserAnotherCongenitalCauses> getQuestionsUserAnotherCongenitalCausesById(Long id) {
        return congenitalCausesRepository.findById(id);
    }

    @Override
    public QuestionsUserAnotherCongenitalCauses saveQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCauses congenitalCauses) {
        return congenitalCausesRepository.save(congenitalCauses);
    }

    @Override
    public QuestionsUserAnotherCongenitalCauses updateQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCauses updated) {
        Long idToUpdate = updated.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserAnotherCongenitalCauses existingCongenitalCauses = findCongenitalCausesById(idToUpdate);

        updateCongenitalCausesFields(existingCongenitalCauses, updated);

        existingCongenitalCauses = congenitalCausesRepository.save(existingCongenitalCauses);

        return existingCongenitalCauses;
    }

    @Override
    public void deleteQuestionsUserAnotherCongenitalCauses(Long id) {
        congenitalCausesRepository.deleteById(id);
    }

    private QuestionsUserAnotherCongenitalCauses findCongenitalCausesById(Long id) {
        return congenitalCausesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Causas congénitas de la usuaria no encontradas con el ID: " + id));
    }

    private void updateCongenitalCausesFields(QuestionsUserAnotherCongenitalCauses existingCongenitalCauses, QuestionsUserAnotherCongenitalCauses updated) {
        if (updated.getMalformationsUterine() != null) {
            existingCongenitalCauses.setMalformationsUterine(updated.getMalformationsUterine());
        }

        if (updated.getTurnerSyndrome() != null) {
            existingCongenitalCauses.setTurnerSyndrome(updated.getTurnerSyndrome());
        }

        if (updated.getAnother() != null) {
            existingCongenitalCauses.setAnother(updated.getAnother());
        }

        if (updated.getAnotherDescription() != null) {
            existingCongenitalCauses.setAnotherDescription(updated.getAnotherDescription());
        }
    }

	@Override
	public List<QuestionsUserAnotherCongenitalCauses> getAQuestionsUserAnotherCongenitalCausesByUserId(Long id) {
		return congenitalCausesRepository.findByUserId(id);
        
	}
}