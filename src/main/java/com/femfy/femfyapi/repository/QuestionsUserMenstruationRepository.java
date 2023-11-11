package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.femfy.femfyapi.entity.QuestionsUserMenstruation;

import java.util.List;

@Repository
public interface QuestionsUserMenstruationRepository extends JpaRepository<QuestionsUserMenstruation, Long> {
    List<QuestionsUserMenstruation> findByUserId(Long userId);
}