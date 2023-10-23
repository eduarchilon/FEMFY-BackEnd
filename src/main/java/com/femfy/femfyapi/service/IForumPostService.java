package com.femfy.femfyapi.service;

import dto.ForumPostDTO;

import java.util.List;
import java.util.Optional;

public interface IForumPostService {

    List<ForumPostDTO> getAllForumPosts();

    Optional<ForumPostDTO> getForumPostById(Long id);

    List<ForumPostDTO> getForumPostsByUser(Long userId);

    List<ForumPostDTO> getForumPostsByTopic(Long topicId);

    ForumPostDTO saveForumPost(ForumPostDTO forumPostDTO);

    ForumPostDTO updateForumPost(ForumPostDTO updatedDTO);

    void deleteForumPost(Long id);
}