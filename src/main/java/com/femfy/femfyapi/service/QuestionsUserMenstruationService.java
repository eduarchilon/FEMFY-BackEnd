package com.femfy.femfyapi.service;

import dto.QuestionsUserMenstruationDTO;
import com.femfy.femfyapi.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.repository.QuestionsUserMenstruationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserMenstruationService implements IQuestionsUserMenstruationService {

    private final QuestionsUserMenstruationRepository questionsUserMenstruationRepository;

    @Autowired
    public QuestionsUserMenstruationService(QuestionsUserMenstruationRepository questionsUserMenstruationRepository) {
        this.questionsUserMenstruationRepository = questionsUserMenstruationRepository;
    }

    @Override
    public List<QuestionsUserMenstruationDTO> getQuestionsUserMenstruations() {
        List<QuestionsUserMenstruation> menstruationList = questionsUserMenstruationRepository.findAll();
        return menstruationList.stream()
                .map(QuestionsUserMenstruationService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserMenstruationDTO> getQuestionsUserMenstruation(Long id) {
        Optional<QuestionsUserMenstruation> menstruation = questionsUserMenstruationRepository.findById(id);
        return menstruation.map(QuestionsUserMenstruationService::mapToDTO);
    }

    @Override
    public QuestionsUserMenstruationDTO saveOrUpdateQuestionsUserMenstruation(QuestionsUserMenstruationDTO questionsUserMenstruationDTO) {
        QuestionsUserMenstruation menstruation = mapToEntity(questionsUserMenstruationDTO);
        menstruation = questionsUserMenstruationRepository.save(menstruation);
        return mapToDTO(menstruation);
    }

    @Override
    public void deleteQuestionsUserMenstruation(Long id) {
        questionsUserMenstruationRepository.deleteById(id);
    }

    private static QuestionsUserMenstruationDTO mapToDTO(QuestionsUserMenstruation menstruation) {
        QuestionsUserMenstruationDTO dto = new QuestionsUserMenstruationDTO();
        dto.setId(menstruation.getId());
        dto.setLastTime((Date) menstruation.getLastTime());
        dto.setLastCycleDuration(menstruation.getLastCycleDuration());
        dto.setRegular(menstruation.getRegular());
        dto.setRegularCycleDuration(menstruation.getRegularCycleDuration());
        dto.setBleedingDuration(menstruation.getBleedingDuration());
        return dto;
    }

    private static QuestionsUserMenstruation mapToEntity(QuestionsUserMenstruationDTO dto) {
        QuestionsUserMenstruation menstruation = new QuestionsUserMenstruation();
        menstruation.setId(dto.getId());
        menstruation.setLastTime(dto.getLastTime());
        menstruation.setLastCycleDuration(dto.getLastCycleDuration());
        menstruation.setRegular(dto.getRegular());
        menstruation.setRegularCycleDuration(dto.getRegularCycleDuration());
        menstruation.setBleedingDuration(dto.getBleedingDuration());
        return menstruation;
    }
}
