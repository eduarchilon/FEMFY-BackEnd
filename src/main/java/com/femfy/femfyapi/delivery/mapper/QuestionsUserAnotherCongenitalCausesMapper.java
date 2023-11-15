package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.QuestionsUserAnotherCongenitalCausesDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserAnotherCongenitalCauses;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class QuestionsUserAnotherCongenitalCausesMapper {

	public static QuestionsUserAnotherCongenitalCausesDTO mapToDTO(QuestionsUserAnotherCongenitalCauses userAnotherCongenitalCauses) {
		if(userAnotherCongenitalCauses == null) {
			throw new EntityNotFoundException("Question User Congenital Causes not found");
		}
		
		QuestionsUserAnotherCongenitalCausesDTO dto = new QuestionsUserAnotherCongenitalCausesDTO();
		dto.setId(userAnotherCongenitalCauses.getId());
		dto.setMalformationsUterine(userAnotherCongenitalCauses.getMalformationsUterine());
		dto.setTurnerSyndrome(userAnotherCongenitalCauses.getTurnerSyndrome());
		dto.setAnother(userAnotherCongenitalCauses.getAnother());
		dto.setAnotherDescription(userAnotherCongenitalCauses.getAnotherDescription());
		dto.setUserId(userAnotherCongenitalCauses.getUser().getId());
		
		return dto;
	}
	
	public static QuestionsUserAnotherCongenitalCauses mapToEntity(QuestionsUserAnotherCongenitalCausesDTO dto) {
		User user = new User();
		user.setId(dto.getUserId());
		
		QuestionsUserAnotherCongenitalCauses userAnotherCongenitalCauses = new QuestionsUserAnotherCongenitalCauses();
		userAnotherCongenitalCauses.setId(dto.getId());
		userAnotherCongenitalCauses.setUser(user);
		userAnotherCongenitalCauses.setMalformationsUterine(dto.getMalformationsUterine());
		userAnotherCongenitalCauses.setTurnerSyndrome(dto.getTurnerSyndrome());
		userAnotherCongenitalCauses.setAnother(dto.getAnother());
		userAnotherCongenitalCauses.setAnotherDescription(dto.getAnotherDescription());
		
		return userAnotherCongenitalCauses;
	}
}
