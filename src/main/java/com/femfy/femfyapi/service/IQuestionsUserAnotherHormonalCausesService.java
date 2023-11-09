package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserAnotherHormonalCauses;
import dto.QuestionsUserAnotherHormonalCausesDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserAnotherHormonalCausesService {

    List<QuestionsUserAnotherHormonalCausesDTO> getAllQuestionsUserAnotherHormonalCauses();

    Optional<QuestionsUserAnotherHormonalCausesDTO> getQuestionsUserAnotherHormonalCausesById(Long id);

    QuestionsUserAnotherHormonalCausesDTO saveQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO dto);

    QuestionsUserAnotherHormonalCausesDTO updateQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO updatedDTO);

    void deleteQuestionsUserAnotherHormonalCauses(Long id);
}