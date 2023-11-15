package com.femfy.femfyapi.domain.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserFamilyHistoryService;
import com.femfy.femfyapi.domain.repository.QuestionsUserFamilyHistoryRepository;

@Service
public class QuestionsUserFamilyHistoryService implements IQuestionsUserFamilyHistoryService {

    private final QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository;

    public QuestionsUserFamilyHistoryService(QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository) {
        this.questionsUserFamilyHistoryRepository = questionsUserFamilyHistoryRepository;
    }

    @Override
    public List<QuestionsUserFamilyHistory> getQuestionsUserFamilyHistories() {
        return questionsUserFamilyHistoryRepository.findAll();
    }

    @Override
    public Optional<QuestionsUserFamilyHistory> getQuestionsUserFamilyHistory(Long id) {
        return questionsUserFamilyHistoryRepository.findById(id);
    }

    @Override
    public QuestionsUserFamilyHistory saveQuestionsUserFamilyHistory(QuestionsUserFamilyHistory questionsUserFamilyHistory) {
        return questionsUserFamilyHistoryRepository.save(questionsUserFamilyHistory);
    }

    @Override
    public QuestionsUserFamilyHistory updateQuestionsUserFamilyHistory(QuestionsUserFamilyHistory updated) {
        Long idToUpdate = updated.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserFamilyHistory existingFamilyHistory = questionsUserFamilyHistoryRepository.findById(idToUpdate)
                .orElseThrow(() -> new RuntimeException("No se encontró el objeto para actualizar"));

        copyProperties(updated, existingFamilyHistory);

        return questionsUserFamilyHistoryRepository.save(existingFamilyHistory);
    }

    private void copyProperties(QuestionsUserFamilyHistory source, QuestionsUserFamilyHistory target) {
        if(source.getBreastCancer() != null){
            target.setBreastCancer(source.getBreastCancer());
        }
        if(source.getOvarianCancer() != null){
            target.setOvarianCancer(source.getOvarianCancer());
        }
        if(source.getEndometriosis() != null){
            target.setEndometriosis(source.getEndometriosis());
        }
        if(source.getUterineFibroids() != null){
            target.setUterineFibroids(source.getUterineFibroids());
        }
        if(source.getSop() != null){
            target.setSop(source.getSop());
        }
        if(source.getEarlyMenopause() != null){
            target.setEarlyMenopause(source.getEarlyMenopause());
        }
    }

    @Override
    public void deleteQuestionsUserFamilyHistory(Long id) {
        questionsUserFamilyHistoryRepository.deleteById(id);
    }

	@Override
	public List<QuestionsUserFamilyHistory> getQuestionsUserFamilyHistoriesByUserId(Long id) {
        return questionsUserFamilyHistoryRepository.findByUserId(id);
	}
}