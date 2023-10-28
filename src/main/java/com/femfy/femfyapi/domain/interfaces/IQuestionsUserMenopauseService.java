package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.domain.entity.QuestionsUserMenopause;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserMenopauseService {

    List<QuestionsUserMenopause> getQuestionsUserMenopause();

    Optional<QuestionsUserMenopause> getQuestionsUserMenopause(Long id);

    QuestionsUserMenopause updateQuestionsUserMenopause(QuestionsUserMenopause updated);

    QuestionsUserMenopause saveQuestionsUserMenopause(QuestionsUserMenopause questionsUserMenopause);

    void deleteQuestionsUserMenopause(Long id);
}