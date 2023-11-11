package com.femfy.femfyapi.service;

import java.util.List;
import java.util.Optional;

import dto.QuestionsUserAnotherHormonalCausesDTO;

public interface IQuestionsUserAnotherHormonalCausesService {

    List<QuestionsUserAnotherHormonalCausesDTO> getAllQuestionsUserAnotherHormonalCauses();

    Optional<QuestionsUserAnotherHormonalCausesDTO> getQuestionsUserAnotherHormonalCausesById(Long id);

    QuestionsUserAnotherHormonalCausesDTO saveQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO dto);

    QuestionsUserAnotherHormonalCausesDTO updateQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO updatedDTO);

    void deleteQuestionsUserAnotherHormonalCauses(Long id);
    
    List<QuestionsUserAnotherHormonalCausesDTO> getAQuestionsUserAnotherHormonalCausesByUserId(Long id);
}