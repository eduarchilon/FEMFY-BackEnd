package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.QuestionsUserAnotherHormonalCausesDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserAnotherHormonalCauses;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class QuestionsUserAnotherHormonalCausesMapper {

	public static QuestionsUserAnotherHormonalCausesDTO mapToDTO(QuestionsUserAnotherHormonalCauses userAnotherHormonalCauses){
		if(userAnotherHormonalCauses == null) {
			throw new EntityNotFoundException("Question User Another Hormonal Causes Not Found");	
		}
		
		QuestionsUserAnotherHormonalCausesDTO dto = new QuestionsUserAnotherHormonalCausesDTO();
		dto.setId(userAnotherHormonalCauses.getId());
		dto.setPolycysticOvarySyndrome(userAnotherHormonalCauses.getPolycysticOvarySyndrome());
		dto.setHypothyroidism(userAnotherHormonalCauses.getHypothyroidism());
		dto.setHyperprolactinemia(userAnotherHormonalCauses.getHyperprolactinemia());
		dto.setSheehanSyndrome(userAnotherHormonalCauses.getSheehanSyndrome());
		dto.setPrematureOvarianFailure(userAnotherHormonalCauses.getPrematureOvarianFailure());
		dto.setHypothalamicDisorders(userAnotherHormonalCauses.getHypothalamicDisorders());
		dto.setInsulinResistance(userAnotherHormonalCauses.getInsulinResistance());
		dto.setAnother(userAnotherHormonalCauses.getAnother());
		dto.setAnotherDescription(userAnotherHormonalCauses.getAnotherDescription());
		dto.setUserId(userAnotherHormonalCauses.getUser().getId());		
		
		return dto;
	}
	
	public static QuestionsUserAnotherHormonalCauses mapToEntity(QuestionsUserAnotherHormonalCausesDTO dto) {
		User user = new User();
		user.setId(dto.getUserId());
		
		QuestionsUserAnotherHormonalCauses userAnotherHormonalCauses = new QuestionsUserAnotherHormonalCauses();
		userAnotherHormonalCauses.setId(dto.getId());
		userAnotherHormonalCauses.setUser(user);
		userAnotherHormonalCauses.setPolycysticOvarySyndrome(dto.getPolycysticOvarySyndrome());
		userAnotherHormonalCauses.setHypothyroidism(dto.getHypothyroidism());
		userAnotherHormonalCauses.setHyperprolactinemia(dto.getHyperprolactinemia());
		userAnotherHormonalCauses.setSheehanSyndrome(dto.getSheehanSyndrome());
		userAnotherHormonalCauses.setPrematureOvarianFailure(dto.getPrematureOvarianFailure());
		userAnotherHormonalCauses.setHypothalamicDisorders(dto.getHypothalamicDisorders());
		userAnotherHormonalCauses.setInsulinResistance(dto.getInsulinResistance());
		userAnotherHormonalCauses.setAnother(dto.getAnother());
		userAnotherHormonalCauses.setAnotherDescription(dto.getAnotherDescription());
		
		return userAnotherHormonalCauses;
	}
}
