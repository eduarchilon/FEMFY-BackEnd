package com.femfy.femfyapi.domain.repository;


import com.femfy.femfyapi.domain.entity.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {
    List<ForumPost> findByUserId(Long userId);

    List<ForumPost> findByTopicId(Long topicId);
}