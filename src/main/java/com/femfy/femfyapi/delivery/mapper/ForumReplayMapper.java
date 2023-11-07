package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.ForumReplayDTO;
import com.femfy.femfyapi.domain.entity.ForumPost;
import com.femfy.femfyapi.domain.entity.ForumReplay;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class ForumReplayMapper {

    public static ForumReplayDTO mapToDTO(ForumReplay forumReplay) {
        if (forumReplay == null) {
            throw new EntityNotFoundException("Respuesta de foro no encontrada");
        }

        ForumReplayDTO dto = new ForumReplayDTO();
        dto.setId(forumReplay.getId());

        if (forumReplay.getPost() != null) {
            dto.setPostId(forumReplay.getPost().getId());
        }

        if (forumReplay.getUser() != null) {
            dto.setUserId(forumReplay.getUser().getId());
        }

        if (forumReplay.getContent() != null) {
            dto.setContent(forumReplay.getContent());
        }

        if (forumReplay.getCreatedDate() != null) {
            dto.setCreatedDate(forumReplay.getCreatedDate());
        }

        return dto;
    }

    public static ForumReplay mapToEntity(ForumReplayDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        ForumPost forumPost = new ForumPost();
        forumPost.setId(dto.getPostId());

        ForumReplay forumReplay = new ForumReplay();
        forumReplay.setId(dto.getId());
        forumReplay.setPost(forumPost);
        forumReplay.setUser(user);
        forumReplay.setContent(dto.getContent());
        forumReplay.setCreatedDate(dto.getCreatedDate());
        return forumReplay;
    }
}
