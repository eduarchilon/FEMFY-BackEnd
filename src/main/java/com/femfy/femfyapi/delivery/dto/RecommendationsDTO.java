package com.femfy.femfyapi.delivery.dto;

import java.util.List;

import lombok.Data;

@Data
public class RecommendationsDTO {
	
	List<TypeRecommendationsDTO> recommendationsByDocuemts;
	List<TypeRecommendationsDTO> recommendationsByFamilyHistory;
	List<TypeRecommendationsDTO> recommendationsByTypeUser;
}
