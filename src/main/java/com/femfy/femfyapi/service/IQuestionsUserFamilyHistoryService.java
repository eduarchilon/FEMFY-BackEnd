package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserFamilyHistoryService {

    List<QuestionsUserFamilyHistory> getFamilyHistories();

    Optional<QuestionsUserFamilyHistory> getFamilyHistory(Long id);

    QuestionsUserFamilyHistory saveOrUpdateFamilyHistory(QuestionsUserFamilyHistory questionsUserFamilyHistory);

    void deleteFamilyHistory(Long id);
}
