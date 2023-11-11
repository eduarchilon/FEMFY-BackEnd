package com.femfy.femfyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.femfy.femfyapi.entity.QuestionsUserAnotherCongenitalCauses;

@Repository
public interface QuestionsUserAnotherCongenitalCausesRepository extends JpaRepository<QuestionsUserAnotherCongenitalCauses, Long> {
	@Transactional
	public List<QuestionsUserAnotherCongenitalCauses> findByUserId(Long idUser);
}