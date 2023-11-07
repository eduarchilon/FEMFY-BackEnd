package com.femfy.femfyapi.domain.repository;


import com.femfy.femfyapi.domain.entity.ForumTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumTopicRepository extends JpaRepository<ForumTopic, Long> {

}