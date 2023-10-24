package com.femfy.femfyapi.service;

import dto.ForumReplayDTO;

import java.util.List;
import java.util.Optional;

public interface IForumReplayService {

    List<ForumReplayDTO> getAllForumReplays();

    Optional<ForumReplayDTO> getForumReplayById(Long id);

    List<ForumReplayDTO> getForumReplaysByUser(Long userId);

    List<ForumReplayDTO> getForumReplaysByPost(Long postId);

    ForumReplayDTO saveForumReplay(ForumReplayDTO forumReplayDTO);

    ForumReplayDTO updateForumReplay(ForumReplayDTO updatedDTO);

    void deleteForumReplay(Long id);
}