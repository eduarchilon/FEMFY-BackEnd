package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.ForumReplay;
import com.femfy.femfyapi.entity.ForumPost;
import com.femfy.femfyapi.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ForumReplayRepositoryTest {

    @Autowired
    private ForumReplayRepository forumReplayRepository;

    @Autowired
    private ForumPostRepository forumPostRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private ForumPost forumPost;

    @BeforeEach
    public void setUp() {
        user = createAndSaveUser("Juan", "Pérez", "juanperez@example.com");
        forumPost = createAndSaveForumPost("Cuántos días se considera normal menstruar?", user);
        createAndSaveForumReplay(user, forumPost, "Lo normal son 5 días aproximadamente");
    }

    @Test
    public void testFindByUserId() {
        createAndSaveForumReplay(user, forumPost, "En mi caso serían 3 días");
        List<ForumReplay> foundReplies = forumReplayRepository.findByUserId(user.getId());

        assertEquals(2, foundReplies.size());
        assertTrue(foundReplies.get(0).getUser().equals(user));
    }

    @Test
    public void testFindByPostId() {
        createAndSaveForumReplay(user, forumPost, "A mí me viene durante 7 días");
        List<ForumReplay> foundReplies = forumReplayRepository.findByPostId(forumPost.getId());

        assertEquals(2, foundReplies.size());
        assertTrue(foundReplies.get(0).getPost().equals(forumPost));
    }

    @Test
    public void testFindAll() {
        List<ForumReplay> replies = forumReplayRepository.findAll();

        createAndSaveForumReplay(null, null, "En mi caso son 4 días");
        createAndSaveForumReplay(null, null, "5 días");

        List<ForumReplay> allReplies = forumReplayRepository.findAll();

        assertEquals(3, allReplies.size());
    }

    @Test
    public void testSave() {
        ForumReplay replay = createAndSaveForumReplay(null, null, "Pueden ser de 3 a 7 días");

        assertNotNull(replay.getId());
        assertEquals("Pueden ser de 3 a 7 días", replay.getContent());
    }

    @Test
    public void testUpdateForumReplay() {
        ForumReplay replay = createAndSaveForumReplay(user, forumPost, "5 días");

        replay.setContent("Respuesta actualizada");
        ForumReplay updatedReplay = forumReplayRepository.save(replay);

        Optional<ForumReplay> foundReplay = forumReplayRepository.findById(updatedReplay.getId());

        assertTrue(foundReplay.isPresent());
        assertEquals("Respuesta actualizada", foundReplay.get().getContent());
    }

    @Test
    public void testDeleteById() {
        ForumReplay replay = createAndSaveForumReplay(null, null, "5 días");
        forumReplayRepository.deleteById(replay.getId());

        Optional<ForumReplay> deletedReplay = forumReplayRepository.findById(replay.getId());
        assertTrue(deletedReplay.isEmpty());
    }

    private User createAndSaveUser(String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return userRepository.save(user);
    }

    private ForumPost createAndSaveForumPost(String content, User user) {
        ForumPost forumPost = new ForumPost();
        forumPost.setContent(content);
        forumPost.setUser(user);
        return forumPostRepository.save(forumPost);
    }

    private ForumReplay createAndSaveForumReplay(User user, ForumPost post, String content) {
        ForumReplay replay = new ForumReplay();
        replay.setUser(user);
        replay.setPost(post);
        replay.setContent(content);
        return forumReplayRepository.save(replay);
    }
}