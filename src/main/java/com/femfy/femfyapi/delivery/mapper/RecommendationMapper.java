package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.TypeRecommendationsDTO;
import com.femfy.femfyapi.domain.entity.Recommendation;

public class RecommendationMapper {

    public static TypeRecommendationsDTO mapToDTO (Recommendation recommendation) {
        TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
        dto.setTypeDisease(recommendation.getTypeDisease());
        dto.setDescription(recommendation.getDescription());
        dto.setAgeReference(recommendation.getAgeReference());
        dto.setIdRecommendation(recommendation.getId());
        return dto;
    }

    public static Recommendation mapToEntity(TypeRecommendationsDTO dto) {
        Recommendation recommendation = new Recommendation();
        recommendation.setTypeDisease(dto.getTypeDisease());
        recommendation.setDescription(dto.getDescription());
        recommendation.setAgeReference(dto.getAgeReference());
        return recommendation;
    }
}
