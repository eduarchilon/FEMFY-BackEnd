package com.femfy.femfyapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.ForumPost;
import com.femfy.femfyapi.domain.entity.ForumTopic;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.IForumPostService;
import com.femfy.femfyapi.domain.repository.ForumPostRepository;

@Service
public class ForumPostService implements IForumPostService {

    private final ForumPostRepository forumPostRepository;

    public ForumPostService(ForumPostRepository forumPostRepository) {
        this.forumPostRepository = forumPostRepository;
    }

    @Override
    public List<ForumPost> getAllForumPosts() {
        return forumPostRepository.findAll();
    }

    @Override
    public Optional<ForumPost> getForumPostById(Long id) {
        return forumPostRepository.findById(id);
    }

    @Override
    public List<ForumPost> getForumPostsByUser(Long userId) {
        return forumPostRepository.findByUserId(userId);
    }

    @Override
    public List<ForumPost> getForumPostsByTopic(Long topicId) {
        return forumPostRepository.findByTopicId(topicId);
    }

    @Override
    public ForumPost saveForumPost(ForumPost forumPost) {
        return forumPostRepository.save(forumPost);
    }

    @Override
    public ForumPost updateForumPost(ForumPost updated) {
        Long idToUpdate = updated.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        ForumPost existingForumPost = findForumPostById(idToUpdate);

        updateForumPostFields(existingForumPost, updated);

        return forumPostRepository.save(existingForumPost);
    }

    @Override
    public void deleteForumPost(Long id) {
        forumPostRepository.deleteById(id);
    }


    private ForumPost findForumPostById(Long postId) {
        return forumPostRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una publicación de foro con el ID: " + postId));
    }

    private void updateForumPostFields(ForumPost existingForumPost, ForumPost updated) {
        if (updated.getTitle() != null) {
            existingForumPost.setTitle(updated.getTitle());
        }

        if (updated.getContent() != null) {
            existingForumPost.setContent(updated.getContent());
        }
        if (updated.getCreatedDate() != null) {
            existingForumPost.setCreatedDate(updated.getCreatedDate());
        }
        if (updated.getUser().getId() != null) {
            User user = new User();
            user.setId(updated.getUser().getId());
            existingForumPost.setUser(user);
        }
        if (updated.getTopic().getId() != null) {
            ForumTopic topic = new ForumTopic();
            topic.setId(updated.getTopic().getId());
            existingForumPost.setTopic(topic);
        }
    }


}