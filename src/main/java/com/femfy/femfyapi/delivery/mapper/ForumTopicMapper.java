package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.ForumPostDTO;
import com.femfy.femfyapi.delivery.dto.ForumReplayDTO;
import com.femfy.femfyapi.delivery.dto.ForumTopicDTO;
import com.femfy.femfyapi.domain.entity.ForumReplay;
import com.femfy.femfyapi.domain.entity.ForumTopic;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ForumTopicMapper {

    public static ForumTopicDTO mapToDTO(ForumTopic forumTopic) {
        if (forumTopic == null) {
            throw new EntityNotFoundException("Temática del foro no encontrada");
        }

        ForumTopicDTO dto = new ForumTopicDTO();
        dto.setId(forumTopic.getId());
        dto.setTitle(forumTopic.getTitle());
        dto.setImage(forumTopic.getImage());
        dto.setCreatedDate(forumTopic.getCreatedDate());

        List<ForumPostDTO> forumPostDTOs = forumTopic.getForumPosts().stream()
                .map(forumPost -> {
                    ForumPostDTO postDTO = new ForumPostDTO();
                    postDTO.setId(forumPost.getId());
                    postDTO.setTitle(forumPost.getTitle());
                    postDTO.setContent(forumPost.getContent());
                    postDTO.setCreatedDate(forumPost.getCreatedDate());
                    postDTO.setUserId(forumPost.getUser().getId());
                    postDTO.setTopicId(forumPost.getTopic().getId());
                    List<ForumReplayDTO> replyDTOs = forumPost.getReplies().stream()
                            .map(ForumTopicMapper::mapReplayToDTO)
                            .collect(Collectors.toList());
                    postDTO.setReplies(replyDTOs);
                    return postDTO;
                })
                .collect(Collectors.toList());

        dto.setForumPosts(forumPostDTOs);

        return dto;
    }

        public static ForumTopic mapToEntity(ForumTopicDTO dto) {
            ForumTopic forumTopic = new ForumTopic();
            forumTopic.setId(dto.getId());
            forumTopic.setTitle(dto.getTitle());
            forumTopic.setImage(dto.getImage());
            forumTopic.setCreatedDate(dto.getCreatedDate());

            return forumTopic;
        }


    public static ForumReplayDTO mapReplayToDTO(ForumReplay replay) {
        ForumReplayDTO replyDTO = new ForumReplayDTO();
        replyDTO.setId(replay.getId());
        replyDTO.setPostId(replay.getPost().getId());
        replyDTO.setCreatedDate(replay.getCreatedDate());
        replyDTO.setContent(replay.getContent());
        replyDTO.setUserId(replay.getUser().getId());
        return replyDTO;
    }
}
