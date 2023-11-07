package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.domain.entity.ForumReplay;

import java.util.List;
import java.util.Optional;

public interface IForumReplayService {

    List<ForumReplay> getAllForumReplays();

    Optional<ForumReplay> getForumReplayById(Long id);

    List<ForumReplay> getForumReplaysByUser(Long userId);

    List<ForumReplay> getForumReplaysByPost(Long postId);

    ForumReplay saveForumReplay(ForumReplay forumReplay);

    ForumReplay updateForumReplay(ForumReplay updated);

    void deleteForumReplay(Long id);
}