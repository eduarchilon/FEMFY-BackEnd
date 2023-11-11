package com.femfy.femfyapi.service;

import dto.QuestionsUserAnotherCongenitalCausesDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserAnotherCongenitalCausesService {

    List<QuestionsUserAnotherCongenitalCausesDTO> getAllQuestionsUserAnotherCongenitalCauses();

    Optional<QuestionsUserAnotherCongenitalCausesDTO> getQuestionsUserAnotherCongenitalCausesById(Long id);

    QuestionsUserAnotherCongenitalCausesDTO saveQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCausesDTO congenitalCausesDTO);

    QuestionsUserAnotherCongenitalCausesDTO updateQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCausesDTO updatedDTO);

    void deleteQuestionsUserAnotherCongenitalCauses(Long id);
    
    List<QuestionsUserAnotherCongenitalCausesDTO> getAQuestionsUserAnotherCongenitalCausesByUserId(Long id);
}