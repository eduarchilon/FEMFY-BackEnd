package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.entity.Recommendation;

@Repository
public interface RecommendationsRepository extends JpaRepository<Recommendation, Long> {

}
