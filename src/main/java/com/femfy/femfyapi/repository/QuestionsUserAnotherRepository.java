package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.entity.QuestionsUserAnother;

import  java.util.List;

@Repository
public interface QuestionsUserAnotherRepository extends JpaRepository<QuestionsUserAnother, Long> {
    List<QuestionsUserAnother> findByUserId(Long userId);
    List<QuestionsUserAnother> findByHormonalCausesId(Long hormonalCausesId);
    List<QuestionsUserAnother> findByCongenitalCausesId(Long congenitalCausesId);
}