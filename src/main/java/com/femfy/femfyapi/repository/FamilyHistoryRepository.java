package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;

@Repository
public interface FamilyHistoryRepository extends JpaRepository<QuestionsUserFamilyHistory, Long> {

}