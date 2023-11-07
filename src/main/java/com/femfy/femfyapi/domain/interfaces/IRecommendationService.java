package com.femfy.femfyapi.domain.interfaces;


import com.femfy.femfyapi.delivery.dto.TypeRecommendationsDTO;
import java.util.List;

public interface IRecommendationService {
	
    public TypeRecommendations saveRecommendation(TypeRecommendations typeRecommendations);

    public String deleteRecommendation(Long idRecommendation);

    public List<TypeRecommendationsDTO> getRecommendatios();
}
