package com.femfy.femfyapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.femfy.femfyapi.domain.entity.QuestionsUserMenstruation;

@Repository
public interface QuestionsUserMenstruationRepository extends JpaRepository<QuestionsUserMenstruation, Long> {

}