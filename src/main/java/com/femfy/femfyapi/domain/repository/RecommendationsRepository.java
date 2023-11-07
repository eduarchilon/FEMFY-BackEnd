package com.femfy.femfyapi.domain.repository;


import com.femfy.femfyapi.domain.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecommendationsRepository extends JpaRepository<Recommendation, Long> {

}
