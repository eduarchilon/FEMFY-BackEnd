package com.femfy.femfyapi.service;

import java.util.List;

import dto.TypeRecommendationsDTO;

public interface IRecommendationService {
	
    public TypeRecommendationsDTO saveRecommendation(TypeRecommendationsDTO dto);

    public String deleteRecommendation(Long idRecommendation);

    public List<TypeRecommendationsDTO> getRecommendatios();
}
