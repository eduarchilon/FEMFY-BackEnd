package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.domain.entity.ForumPost;

import java.util.List;
import java.util.Optional;

public interface IForumPostService {

    List<ForumPost> getAllForumPosts();

    Optional<ForumPost> getForumPostById(Long id);

    List<ForumPost> getForumPostsByUser(Long userId);

    List<ForumPost> getForumPostsByTopic(Long topicId);

    ForumPost saveForumPost(ForumPost forumPost);

    ForumPost updateForumPost(ForumPost forumPost);

    void deleteForumPost(Long id);
}