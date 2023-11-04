package com.femfy.femfyapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.femfy.femfyapi.domain.entity.QuestionsUserFamilyHistory;

@Repository
public interface QuestionsUserFamilyHistoryRepository extends JpaRepository<QuestionsUserFamilyHistory, Long> {

	@Transactional
	public List<QuestionsUserFamilyHistory> findByUserId(Long idUser);
}