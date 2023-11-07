package com.femfy.femfyapi.domain.service;


import com.femfy.femfyapi.domain.entity.ForumPost;
import com.femfy.femfyapi.domain.entity.ForumReplay;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.IForumReplayService;
import com.femfy.femfyapi.domain.repository.ForumReplayRepository;
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
    public List<ForumReplay> getAllForumReplays() {
        return forumReplayRepository.findAll();
    }

    @Override
    public Optional<ForumReplay> getForumReplayById(Long id) {
        return forumReplayRepository.findById(id);
    }

    @Override
    public List<ForumReplay> getForumReplaysByUser(Long userId) {
        return forumReplayRepository.findByUserId(userId);
    }

    @Override
    public List<ForumReplay> getForumReplaysByPost(Long postId) {
        return forumReplayRepository.findByPostId(postId);
    }

    @Override
    public ForumReplay saveForumReplay(ForumReplay forumReplay) {
        return forumReplayRepository.save(forumReplay);
    }

    @Override
    public ForumReplay updateForumReplay(ForumReplay updated) {
        Long idToUpdate = updated.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        ForumReplay existingForumReplay = findForumReplayById(idToUpdate);

        updateForumReplayFields(existingForumReplay, updated);

        return forumReplayRepository.save(existingForumReplay);

    }

    @Override
    public void deleteForumReplay(Long id) {
        forumReplayRepository.deleteById(id);
    }



    private ForumReplay findForumReplayById(Long replayId) {
        return forumReplayRepository.findById(replayId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una respuesta de foro con el ID: " + replayId));
    }

    private void updateForumReplayFields(ForumReplay existingForumReplay, ForumReplay update) {
        if (update == null) {
            throw new IllegalArgumentException("El objeto ForumReplayDTO no puede ser nulo para la actualización.");
        }

        if (update.getContent() != null) {
            existingForumReplay.setContent(update.getContent());
        }

        if (update.getCreatedDate() != null) {
            existingForumReplay.setCreatedDate(update.getCreatedDate());
        }

        if (update.getUser().getId() != null) {
            User user = new User();
            user.setId(update.getUser().getId());
            existingForumReplay.setUser(user);
        }

        if (update.getUser().getId() != null) {
            ForumPost post = new ForumPost();
            post.setId(update.getUser().getId());
            existingForumReplay.setPost(post);
        }
    }
}