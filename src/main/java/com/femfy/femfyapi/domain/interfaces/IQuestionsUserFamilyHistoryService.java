package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.delivery.dto.QuestionsUserFamilyHistoryDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserFamilyHistoryService {

    List<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistories();

    Optional<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistory(Long id);

    QuestionsUserFamilyHistoryDTO updateQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO updatedDTO);

    QuestionsUserFamilyHistoryDTO saveQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO questionsUserFamilyHistoryDTO);

    void deleteQuestionsUserFamilyHistory(Long id);
}