package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.femfy.femfyapi.entity.QuestionsUserMenopause;

import  java.util.List;

@Repository
public interface QuestionsUserMenopauseRepository extends JpaRepository<QuestionsUserMenopause, Long> {
    List<QuestionsUserMenopause> findByUserId(Long userId);
}