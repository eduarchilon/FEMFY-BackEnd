package com.femfy.femfyapi.service;

import dto.ForumTopicDTO;

import java.util.List;
import java.util.Optional;

public interface IForumTopicService {

    List<ForumTopicDTO> getAllForumTopics();

    Optional<ForumTopicDTO> getForumTopicById(Long id);

    ForumTopicDTO saveForumTopic(ForumTopicDTO forumTopicDTO);

    ForumTopicDTO updateForumTopic(ForumTopicDTO forumTopicDTO);

    void deleteForumTopic(Long id);
}