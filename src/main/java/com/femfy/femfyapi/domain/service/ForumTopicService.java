package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.ForumTopic;
import com.femfy.femfyapi.entity.ForumReplay;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.ForumTopicRepository;
import dto.ForumReplayDTO;
import dto.ForumTopicDTO;
import dto.ForumPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumTopicService implements IForumTopicService {

    private final ForumTopicRepository forumTopicRepository;

    @Autowired
    public ForumTopicService(ForumTopicRepository forumTopicRepository) {
        this.forumTopicRepository = forumTopicRepository;
    }

    @Override
    public List<ForumTopicDTO> getAllForumTopics() {
        List<ForumTopic> forumTopics = forumTopicRepository.findAll();
        return forumTopics.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ForumTopicDTO> getForumTopicById(Long id) {
        Optional<ForumTopic> forumTopic = forumTopicRepository.findById(id);
        return forumTopic.map(this::mapToDTO);
    }

    @Override
    public ForumTopicDTO saveForumTopic(ForumTopicDTO forumTopicDTO) {
        ForumTopic forumTopic = mapToEntity(forumTopicDTO);
        forumTopic = forumTopicRepository.save(forumTopic);
        return mapToDTO(forumTopic);
    }

    @Override
    public ForumTopicDTO updateForumTopic(ForumTopicDTO forumTopicDTO) {
        Long idToUpdate = forumTopicDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        ForumTopic existingTopic = findForumTopicById(idToUpdate);

        updateForumTopicFields(existingTopic, forumTopicDTO);

        existingTopic = forumTopicRepository.save(existingTopic);

        return mapToDTO(existingTopic);
    }

    @Override
    public void deleteForumTopic(Long id) {
        forumTopicRepository.deleteById(id);
    }

    private ForumTopicDTO mapToDTO(ForumTopic forumTopic) {
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
                            .map(this::mapReplayToDTO)
                            .collect(Collectors.toList());
                    postDTO.setReplies(replyDTOs);
                    return postDTO;
                })
                .collect(Collectors.toList());

        dto.setForumPosts(forumPostDTOs);

        return dto;
    }

    private ForumReplayDTO mapReplayToDTO(ForumReplay replay) {
        ForumReplayDTO replyDTO = new ForumReplayDTO();
        replyDTO.setId(replay.getId());
        replyDTO.setPostId(replay.getPost().getId());
        replyDTO.setCreatedDate(replay.getCreatedDate());
        replyDTO.setContent(replay.getContent());
        replyDTO.setUserId(replay.getUser().getId());
        return replyDTO;
    }

    private ForumTopic mapToEntity(ForumTopicDTO dto) {
        ForumTopic forumTopic = new ForumTopic();
        forumTopic.setId(dto.getId());
        forumTopic.setTitle(dto.getTitle());
        forumTopic.setImage(dto.getImage());
        forumTopic.setCreatedDate(dto.getCreatedDate());

        return forumTopic;
    }

    private ForumTopic findForumTopicById(Long topicId) {
        return forumTopicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la temática del foro con el ID: " + topicId));
    }

    private void updateForumTopicFields(ForumTopic existingTopic, ForumTopicDTO forumTopicDTO) {
        if (forumTopicDTO.getTitle() != null) {
            existingTopic.setTitle(forumTopicDTO.getTitle());
        }
        if (forumTopicDTO.getImage() != null) {
            existingTopic.setImage(forumTopicDTO.getImage());
        }
        if (forumTopicDTO.getCreatedDate() != null) {
            existingTopic.setCreatedDate(forumTopicDTO.getCreatedDate());
        }
    }
}