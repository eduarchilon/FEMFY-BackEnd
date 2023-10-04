package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserMenstruation;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserMenstruationService {

    List<QuestionsUserMenstruation> getQuestionsUserMenstruations();

    Optional<QuestionsUserMenstruation> getQuestionsUserMenstruation(Long id);

    QuestionsUserMenstruation saveOrUpdateQuestionsUserMenstruation(QuestionsUserMenstruation questionsUserMenstruation);

    void deleteQuestionsUserMenstruation(Long id);
}