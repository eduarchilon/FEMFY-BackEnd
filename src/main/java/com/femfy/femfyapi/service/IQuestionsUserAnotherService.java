package com.femfy.femfyapi.service;

import dto.QuestionsUserAnotherDTO;
import java.util.List;
import java.util.Optional;

public interface IQuestionsUserAnotherService {

    List<QuestionsUserAnotherDTO> getQuestionsUserAnother();

    Optional<QuestionsUserAnotherDTO> getQuestionsUserAnother(Long id);

    List<QuestionsUserAnotherDTO> getQuestionsUserAnotherByUser(Long userId);

    QuestionsUserAnotherDTO saveQuestionsUserAnother(QuestionsUserAnotherDTO questionsUserAnotherDTO);

    QuestionsUserAnotherDTO updateQuestionsUserAnother(QuestionsUserAnotherDTO updatedDTO);

    void deleteQuestionsUserAnother(Long id);
}