package com.femfy.femfyapi.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.femfy.femfyapi.domain.entity.QuestionsUserAnotherCongenitalCauses;

public interface IQuestionsUserAnotherCongenitalCausesService {

    List<QuestionsUserAnotherCongenitalCauses> getAllQuestionsUserAnotherCongenitalCauses();

    Optional<QuestionsUserAnotherCongenitalCauses> getQuestionsUserAnotherCongenitalCausesById(Long id);

    QuestionsUserAnotherCongenitalCauses saveQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCauses congenitalCauses);

    QuestionsUserAnotherCongenitalCauses updateQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCauses updated);

    void deleteQuestionsUserAnotherCongenitalCauses(Long id);
    
    List<QuestionsUserAnotherCongenitalCauses> getAQuestionsUserAnotherCongenitalCausesByUserId(Long id);
}