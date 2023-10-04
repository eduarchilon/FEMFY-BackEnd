package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.repository.FamilyHistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyHistoryService implements IFamilyHistoryService {

    @Autowired
    private FamilyHistoryRepository familyHistoryRepository;

    @Override
    public List<QuestionsUserFamilyHistory> getFamilyHistories() {
        return familyHistoryRepository.findAll();
    }

    @Override
    public Optional<QuestionsUserFamilyHistory> getFamilyHistory(Long id) {
        return familyHistoryRepository.findById(id);
    }

    @Override
    public QuestionsUserFamilyHistory saveOrUpdateFamilyHistory(QuestionsUserFamilyHistory questionsUserFamilyHistory) {
        familyHistoryRepository.save(questionsUserFamilyHistory);
        return questionsUserFamilyHistory;
    }

    @Override
    public void deleteFamilyHistory(Long id) {
        familyHistoryRepository.deleteById(id);
    }
}