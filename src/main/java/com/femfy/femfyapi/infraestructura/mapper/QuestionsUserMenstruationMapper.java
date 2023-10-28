package com.femfy.femfyapi.infraestructura.mapper;

import com.femfy.femfyapi.delivery.dto.QuestionsUserMenstruationDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class QuestionsUserMenstruationMapper {

    public static QuestionsUserMenstruationDTO mapToDTO(QuestionsUserMenstruation menstruation) {
        if (menstruation == null) {
            throw new EntityNotFoundException("Menstruation not found");
        }

        QuestionsUserMenstruationDTO dto = new QuestionsUserMenstruationDTO();
        dto.setId(menstruation.getId());
        dto.setUserId(menstruation.getUser().getId());
        dto.setLastTime(menstruation.getLastTime());
        dto.setLastCycleDuration(menstruation.getLastCycleDuration());
        dto.setRegular(menstruation.getRegular());
        dto.setRegularCycleDuration(menstruation.getRegularCycleDuration());
        dto.setBleedingDuration(menstruation.getBleedingDuration());

        return dto;
    }

    public static QuestionsUserMenstruation mapToEntity(QuestionsUserMenstruationDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserMenstruation menstruation = new QuestionsUserMenstruation();
        menstruation.setId(dto.getId());
        menstruation.setUser(user);
        menstruation.setLastTime(dto.getLastTime());
        menstruation.setLastCycleDuration(dto.getLastCycleDuration());
        menstruation.setRegular(dto.getRegular());
        menstruation.setRegularCycleDuration(dto.getRegularCycleDuration());
        menstruation.setBleedingDuration(dto.getBleedingDuration());
        return menstruation;
    }}
