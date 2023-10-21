package com.femfy.femfyapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.domain.entity.QuestionsUserFamilyHistory;

@Repository
public interface QuestionsUserFamilyHistoryRepository extends JpaRepository<QuestionsUserFamilyHistory, Long> {

}