package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.domain.entity.QuestionsUserFamilyHistory;
import java.util.List;
import java.util.Optional;

public interface IQuestionsUserFamilyHistoryService {

    List<QuestionsUserFamilyHistory> getQuestionsUserFamilyHistories();

    Optional<QuestionsUserFamilyHistory> getQuestionsUserFamilyHistory(Long id);

    QuestionsUserFamilyHistory updateQuestionsUserFamilyHistory(QuestionsUserFamilyHistory updated);

    QuestionsUserFamilyHistory saveQuestionsUserFamilyHistory(QuestionsUserFamilyHistory questionsUserFamilyHistory);

    void deleteQuestionsUserFamilyHistory(Long id);

	List<QuestionsUserFamilyHistory> getQuestionsUserFamilyHistoriesByUserId(Long id);
}