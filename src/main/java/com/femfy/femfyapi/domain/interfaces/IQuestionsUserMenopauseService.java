package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.delivery.dto.QuestionsUserMenopauseDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserMenopauseService {

    List<QuestionsUserMenopauseDTO> getQuestionsUserMenopause();

    Optional<QuestionsUserMenopauseDTO> getQuestionsUserMenopause(Long id);

    QuestionsUserMenopauseDTO updateQuestionsUserMenopause(QuestionsUserMenopauseDTO updatedDTO);

    QuestionsUserMenopauseDTO saveQuestionsUserMenopause(QuestionsUserMenopauseDTO questionsUserMenopauseDTO);

    void deleteQuestionsUserMenopause(Long id);
}