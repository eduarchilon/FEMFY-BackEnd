package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.ForumTopic;
import com.femfy.femfyapi.entity.ForumPost;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.ForumPostRepository;
import dto.ForumPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumPostService implements IForumPostService {

    private final ForumPostRepository forumPostRepository;

    @Autowired
    public ForumPostService(ForumPostRepository forumPostRepository) {
        this.forumPostRepository = forumPostRepository;
    }

    @Override
    public List<ForumPostDTO> getAllForumPosts() {
        List<ForumPost> forumPosts = forumPostRepository.findAll();
        return forumPosts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ForumPostDTO> getForumPostById(Long id) {
        Optional<ForumPost> forumPost = forumPostRepository.findById(id);
        return forumPost.map(this::mapToDTO);
    }

    @Override
    public List<ForumPostDTO> getForumPostsByUser(Long userId) {
        List<ForumPost> forumPosts = forumPostRepository.findByUserId(userId);
        return forumPosts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ForumPostDTO> getForumPostsByTopic(Long topicId) {
        List<ForumPost> forumPosts = forumPostRepository.findByTopicId(topicId);
        return forumPosts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ForumPostDTO saveForumPost(ForumPostDTO forumPostDTO) {
        ForumPost forumPost = mapToEntity(forumPostDTO);
        forumPost = forumPostRepository.save(forumPost);
        return mapToDTO(forumPost);
    }

    @Override
    public ForumPostDTO updateForumPost(ForumPostDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        ForumPost existingForumPost = findForumPostById(idToUpdate);

        updateForumPostFields(existingForumPost, updatedDTO);

        existingForumPost = forumPostRepository.save(existingForumPost);

        return mapToDTO(existingForumPost);
    }

    @Override
    public void deleteForumPost(Long id) {
        forumPostRepository.deleteById(id);
    }

    private ForumPostDTO mapToDTO(ForumPost forumPost) {
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
        return dto;
    }

    private ForumPost mapToEntity(ForumPostDTO dto) {
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

    private ForumPost findForumPostById(Long postId) {
        return forumPostRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una publicación de foro con el ID: " + postId));
    }

    private void updateForumPostFields(ForumPost existingForumPost, ForumPostDTO forumPostDTO) {
        if (forumPostDTO.getTitle() != null) {
            existingForumPost.setTitle(forumPostDTO.getTitle());
        }

        if (forumPostDTO.getContent() != null) {
            existingForumPost.setContent(forumPostDTO.getContent());
        }
        if (forumPostDTO.getCreatedDate() != null) {
            existingForumPost.setCreatedDate(forumPostDTO.getCreatedDate());
        }
        if (forumPostDTO.getUserId() != null) {
            User user = new User();
            user.setId(forumPostDTO.getUserId());
            existingForumPost.setUser(user);
        }
        if (forumPostDTO.getTopicId() != null) {
            ForumTopic topic = new ForumTopic();
            topic.setId(forumPostDTO.getTopicId());
            existingForumPost.setTopic(topic);
        }
    }
}