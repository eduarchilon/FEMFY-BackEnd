package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.domain.entity.QuestionsUserMenstruation;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserMenstruationService {

    List<QuestionsUserMenstruation> getQuestionsUserMenstruations();

    Optional<QuestionsUserMenstruation> getQuestionsUserMenstruation(Long id);

    QuestionsUserMenstruation updateQuestionsUserMenstruation(QuestionsUserMenstruation updated);
    QuestionsUserMenstruation saveQuestionsUserMenstruation(QuestionsUserMenstruation questionsUserMenstruation);
    void deleteQuestionsUserMenstruation(Long id);
}