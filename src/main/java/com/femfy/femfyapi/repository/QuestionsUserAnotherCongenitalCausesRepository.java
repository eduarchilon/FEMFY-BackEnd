package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.femfy.femfyapi.entity.QuestionsUserAnotherCongenitalCauses;

@Repository
public interface QuestionsUserAnotherCongenitalCausesRepository extends JpaRepository<QuestionsUserAnotherCongenitalCauses, Long> {
}