package com.femfy.femfyapi.domain.interfaces;


import com.femfy.femfyapi.domain.entity.Recommendation;

import java.util.List;

public interface IRecommendationService {
	
    public Recommendation saveRecommendation(Recommendation typeRecommendations);

    public String deleteRecommendation(Long idRecommendation);

    public List<Recommendation> getRecommendatios();
}
