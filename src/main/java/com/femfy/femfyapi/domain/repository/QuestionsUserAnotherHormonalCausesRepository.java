package com.femfy.femfyapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.femfy.femfyapi.domain.entity.QuestionsUserAnotherHormonalCauses;

@Repository
public interface QuestionsUserAnotherHormonalCausesRepository extends JpaRepository<QuestionsUserAnotherHormonalCauses, Long> {
	@Transactional
	public List<QuestionsUserAnotherHormonalCauses> findByUserId(Long idUser);
}