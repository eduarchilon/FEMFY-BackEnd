package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.entity.FamilyHistory;

@Repository
public interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, Long> {

}