package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.ForumReplay;
import com.femfy.femfyapi.entity.ForumPost;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.ForumReplayRepository;
import dto.ForumReplayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumReplayService implements IForumReplayService {

    private final ForumReplayRepository forumReplayRepository;

    @Autowired
    public ForumReplayService(ForumReplayRepository forumReplayRepository) {
        this.forumReplayRepository = forumReplayRepository;
    }

    @Override
    public List<ForumReplayDTO> getAllForumReplays() {
        List<ForumReplay> forumReplays = forumReplayRepository.findAll();
        return forumReplays.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ForumReplayDTO> getForumReplayById(Long id) {
        Optional<ForumReplay> forumReplay = forumReplayRepository.findById(id);
        return forumReplay.map(this::mapToDTO);
    }

    @Override
    public List<ForumReplayDTO> getForumReplaysByUser(Long userId) {
        List<ForumReplay> forumReplays = forumReplayRepository.findByUserId(userId);
        return forumReplays.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ForumReplayDTO> getForumReplaysByPost(Long postId) {
        List<ForumReplay> forumReplays = forumReplayRepository.findByPostId(postId);
        return forumReplays.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ForumReplayDTO saveForumReplay(ForumReplayDTO forumReplayDTO) {
        ForumReplay forumReplay = mapToEntity(forumReplayDTO);
        forumReplay = forumReplayRepository.save(forumReplay);
        return mapToDTO(forumReplay);
    }

    @Override
    public ForumReplayDTO updateForumReplay(ForumReplayDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        ForumReplay existingForumReplay = findForumReplayById(idToUpdate);

        updateForumReplayFields(existingForumReplay, updatedDTO);

        existingForumReplay = forumReplayRepository.save(existingForumReplay);

        return mapToDTO(existingForumReplay);
    }

    @Override
    public void deleteForumReplay(Long id) {
        forumReplayRepository.deleteById(id);
    }

    private ForumReplayDTO mapToDTO(ForumReplay forumReplay) {
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

    private ForumReplay mapToEntity(ForumReplayDTO dto) {
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

    private ForumReplay findForumReplayById(Long replayId) {
        return forumReplayRepository.findById(replayId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una respuesta de foro con el ID: " + replayId));
    }

    private void updateForumReplayFields(ForumReplay existingForumReplay, ForumReplayDTO forumReplayDTO) {
        if (forumReplayDTO == null) {
            throw new IllegalArgumentException("El objeto ForumReplayDTO no puede ser nulo para la actualización.");
        }

        if (forumReplayDTO.getContent() != null) {
            existingForumReplay.setContent(forumReplayDTO.getContent());
        }

        if (forumReplayDTO.getCreatedDate() != null) {
            existingForumReplay.setCreatedDate(forumReplayDTO.getCreatedDate());
        }

        if (forumReplayDTO.getUserId() != null) {
            User user = new User();
            user.setId(forumReplayDTO.getUserId());
            existingForumReplay.setUser(user);
        }

        if (forumReplayDTO.getPostId() != null) {
            ForumPost post = new ForumPost();
            post.setId(forumReplayDTO.getPostId());
            existingForumReplay.setPost(post);
        }
    }
}