package com.femfy.femfyapi.domain.repository;

import com.femfy.femfyapi.domain.entity.ForumReplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumReplayRepository extends JpaRepository<ForumReplay, Long> {
    List<ForumReplay> findByUserId(Long userId);
    List<ForumReplay> findByPostId(Long postId);
}
