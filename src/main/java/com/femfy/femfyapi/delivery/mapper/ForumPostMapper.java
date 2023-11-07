package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.ForumReplayDTO;
import com.femfy.femfyapi.domain.entity.ForumPost;
import com.femfy.femfyapi.domain.entity.ForumReplay;
import com.femfy.femfyapi.domain.entity.ForumTopic;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.delivery.dto.ForumPostDTO;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ForumPostMapper {


    public static ForumPostDTO mapToDTO(ForumPost forumPost) {
        if (forumPost == null) {
            throw new EntityNotFoundException("Publicación de foro no encontrada");
        }

        ForumPostDTO dto = new ForumPostDTO();
        dto.setId(forumPost.getId());

        if (forumPost.getTopic() != null) {
            dto.setTopicId(forumPost.getTopic().getId());
        }

        if (forumPost.getUser() != null) {
            dto.setUserId(forumPost.getUser().getId());
        }

        dto.setTitle(forumPost.getTitle());
        dto.setContent(forumPost.getContent());
        dto.setCreatedDate(forumPost.getCreatedDate());

        List<ForumReplayDTO> replyDTOs = forumPost.getReplies().stream()
                .map(ForumPostMapper::mapReplayToDTO)
                .collect(Collectors.toList());
        dto.setReplies(replyDTOs);

        return dto;
    }

    public static ForumPost mapToEntity(ForumPostDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        ForumTopic forumTopic = new ForumTopic();
        forumTopic.setId(dto.getTopicId());

        ForumPost forumPost = new ForumPost();
        forumPost.setId(dto.getId());
        forumPost.setTopic(forumTopic);
        forumPost.setUser(user);
        forumPost.setTitle(dto.getTitle());
        forumPost.setContent(dto.getContent());
        forumPost.setCreatedDate(dto.getCreatedDate());
        return forumPost;
    }

    public static ForumReplayDTO mapReplayToDTO(ForumReplay replay) {
        ForumReplayDTO replyDTO = new ForumReplayDTO();
        replyDTO.setId(replay.getId());
        replyDTO.setContent(replay.getContent());
        replyDTO.setUserId(replay.getUser().getId());
        replyDTO.setPostId(replay.getPost().getId());
        replyDTO.setCreatedDate(replay.getCreatedDate());
        return replyDTO;
    }
}
