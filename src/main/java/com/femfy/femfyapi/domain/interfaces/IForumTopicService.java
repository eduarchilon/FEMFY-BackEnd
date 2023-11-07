package com.femfy.femfyapi.domain.interfaces;




import com.femfy.femfyapi.domain.entity.ForumTopic;

import java.util.List;
import java.util.Optional;

public interface IForumTopicService {

    List<ForumTopic> getAllForumTopics();

    Optional<ForumTopic> getForumTopicById(Long id);

    ForumTopic saveForumTopic(ForumTopic forumTopic);

    ForumTopic updateForumTopic(ForumTopic forumTopic);

    void deleteForumTopic(Long id);
}