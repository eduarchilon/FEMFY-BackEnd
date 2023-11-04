package com.femfy.femfyapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.Recommendation;
import com.femfy.femfyapi.repository.RecommendationsRepository;

import dto.TypeRecommendationsDTO;

@Service
public class RecommendationService implements IRecommendationService {

    private final RecommendationsRepository recommendationsRepository;

    @Autowired
    public RecommendationService(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    @Override
    public TypeRecommendationsDTO saveRecommendation(TypeRecommendationsDTO dto) {
    	Recommendation recommendation = mapToRecoomendation(dto);
    	recommendation = this.recommendationsRepository.save(recommendation);
        return mapToTypeRecommendationsDTO(recommendation);
    }
    
    @Override
    public String deleteRecommendation(Long idRecommendation) {
        try {
            this.recommendationsRepository.deleteById(idRecommendation);
            return "OK";
        } catch (EmptyResultDataAccessException e) {
            return "Error: No se encontró ningún registro con el ID proporcionado.";
        } catch (DataIntegrityViolationException e) {
            return "Error: No se puede eliminar este registro debido a restricciones de integridad de datos.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<TypeRecommendationsDTO> getRecommendatios() {
        List<Recommendation> recommendations = this.recommendationsRepository.findAll();
        return recommendations.stream().map(this::mapToTypeRecommendationsDTO).collect(Collectors.toList());
    }
    
    private TypeRecommendationsDTO mapToTypeRecommendationsDTO (Recommendation recommendation) {
    	TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
        dto.setTypeDisease(recommendation.getTypeDisease());
        dto.setDescription(recommendation.getDescription());
        dto.setAgeReference(recommendation.getAgeReference());
        dto.setIdRecommendation(recommendation.getId());
        return dto;
    }

    private Recommendation mapToRecoomendation(TypeRecommendationsDTO dto) {
    	Recommendation recommendation = new Recommendation();
    	recommendation.setTypeDisease(dto.getTypeDisease());
    	recommendation.setDescription(dto.getDescription());
    	recommendation.setAgeReference(dto.getAgeReference());
        return recommendation;
    }
}