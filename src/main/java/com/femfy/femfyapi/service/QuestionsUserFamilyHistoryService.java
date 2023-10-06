package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.repository.QuestionsUserFamilyHistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsUserFamilyHistoryService implements IQuestionsUserFamilyHistoryService {

    @Autowired
    private QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository;

    @Override
    public List<QuestionsUserFamilyHistory> getFamilyHistories() {
        return questionsUserFamilyHistoryRepository.findAll();
    }

    @Override
    public Optional<QuestionsUserFamilyHistory> getFamilyHistory(Long id) {
        return questionsUserFamilyHistoryRepository.findById(id);
    }

    @Override
    public QuestionsUserFamilyHistory saveOrUpdateFamilyHistory(QuestionsUserFamilyHistory questionsUserFamilyHistory) {
        questionsUserFamilyHistoryRepository.save(questionsUserFamilyHistory);
        return questionsUserFamilyHistory;
    }

    @Override
    public void deleteFamilyHistory(Long id) {
        questionsUserFamilyHistoryRepository.deleteById(id);
    }
}