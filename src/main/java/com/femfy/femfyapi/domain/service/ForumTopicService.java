package com.femfy.femfyapi.domain.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.ForumTopic;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.IForumTopicService;
import com.femfy.femfyapi.domain.repository.ForumTopicRepository;


@Service
public class ForumTopicService implements IForumTopicService {

    private final ForumTopicRepository forumTopicRepository;

    public ForumTopicService(ForumTopicRepository forumTopicRepository) {
        this.forumTopicRepository = forumTopicRepository;
    }

    @Override
    public List<ForumTopic> getAllForumTopics() {
        return forumTopicRepository.findAll();
    }

    @Override
    public Optional<ForumTopic> getForumTopicById(Long id) {
        return forumTopicRepository.findById(id);
    }

    @Override
    public ForumTopic saveForumTopic(ForumTopic forumTopic) {
        return forumTopicRepository.save(forumTopic);
    }

    @Override
    public ForumTopic updateForumTopic(ForumTopic forumTopic) {
        Long idToUpdate = forumTopic.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        ForumTopic existingTopic = findForumTopicById(idToUpdate);

        updateForumTopicFields(existingTopic, forumTopic);

        return forumTopicRepository.save(existingTopic);
    }

    @Override
    public void deleteForumTopic(Long id) {
        forumTopicRepository.deleteById(id);
    }


    private ForumTopic findForumTopicById(Long topicId) {
        return forumTopicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la temática del foro con el ID: " + topicId));
    }

    private void updateForumTopicFields(ForumTopic existingTopic, ForumTopic update) {
        if (update.getTitle() != null) {
            existingTopic.setTitle(update.getTitle());
        }
        if (update.getImage() != null) {
            existingTopic.setImage(update.getImage());
        }
        if (update.getCreatedDate() != null) {
            existingTopic.setCreatedDate(update.getCreatedDate());
        }
    }
}