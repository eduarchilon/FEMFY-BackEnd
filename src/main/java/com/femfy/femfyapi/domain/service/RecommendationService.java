package com.femfy.femfyapi.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import com.femfy.femfyapi.domain.entity.Recommendation;
import com.femfy.femfyapi.domain.interfaces.IRecommendationService;
import com.femfy.femfyapi.domain.repository.RecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;



@Service
public class RecommendationService implements IRecommendationService {

    private final RecommendationsRepository recommendationsRepository;

    @Autowired
    public RecommendationService(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    @Override
    public Recommendation saveRecommendation(Recommendation recommendation) {
    	return this.recommendationsRepository.save(recommendation);

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
    public List<Recommendation> getRecommendatios() {
        return this.recommendationsRepository.findAll();
    }
    

}