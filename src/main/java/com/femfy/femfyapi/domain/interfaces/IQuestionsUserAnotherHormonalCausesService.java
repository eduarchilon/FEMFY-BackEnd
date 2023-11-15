package com.femfy.femfyapi.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.femfy.femfyapi.domain.entity.QuestionsUserAnotherHormonalCauses;

public interface IQuestionsUserAnotherHormonalCausesService {

    List<QuestionsUserAnotherHormonalCauses> getAllQuestionsUserAnotherHormonalCauses();

    Optional<QuestionsUserAnotherHormonalCauses> getQuestionsUserAnotherHormonalCausesById(Long id);

    QuestionsUserAnotherHormonalCauses saveQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCauses hormonalCauses);

    QuestionsUserAnotherHormonalCauses updateQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCauses updated);

    void deleteQuestionsUserAnotherHormonalCauses(Long id);
    
    List<QuestionsUserAnotherHormonalCauses> getAQuestionsUserAnotherHormonalCausesByUserId(Long id);
}