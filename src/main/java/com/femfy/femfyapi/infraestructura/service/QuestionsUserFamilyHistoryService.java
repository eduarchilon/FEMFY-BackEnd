package com.femfy.femfyapi.infraestructura.service;

import com.femfy.femfyapi.delivery.dto.QuestionsUserFamilyHistoryDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserFamilyHistoryService;
import com.femfy.femfyapi.domain.repository.QuestionsUserFamilyHistoryRepository;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserFamilyHistoryService implements IQuestionsUserFamilyHistoryService {

    private final QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository;

    @Autowired
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
        if(source.isBreastCancer()){
            target.setBreastCancer(source.isBreastCancer());
        }
        if(source.isOvarianCancer()){
            target.setOvarianCancer(source.isOvarianCancer());
        }
        if(source.isEndometriosis()){
            target.setEndometriosis(source.isEndometriosis());
        }
        if(source.isUterineFibroids()){
            target.setUterineFibroids(source.isUterineFibroids());
        }
        if(source.isSop()){
            target.setSop(source.isSop());
        }
        if(source.isEarlyMenopause()){
            target.setEarlyMenopause(source.isEarlyMenopause());
        }
    }

    @Override
    public void deleteQuestionsUserFamilyHistory(Long id) {
        questionsUserFamilyHistoryRepository.deleteById(id);
    }




}