package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.ForumTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ForumTopicRepositoryTest {

    @Autowired
    private ForumTopicRepository forumTopicRepository;

    @BeforeEach
    public void setUp() {
        ForumTopic forumTopic = new ForumTopic();
        forumTopic.setTitle("General");
        forumTopic.setImage("imagen.jpg");

        forumTopicRepository.save(forumTopic);
    }

    @Test
    public void testFindById() {
        // Inserta
        ForumTopic topic = new ForumTopic();
        topic.setTitle("Pareja");
        forumTopicRepository.save(topic);

        // Busca
        Optional<ForumTopic> foundTopic = forumTopicRepository.findById(topic.getId());

        // Verifica
        assertThat(foundTopic).isPresent();
        assertThat(foundTopic.get().getId()).isEqualTo(topic.getId());
    }

    @Test
    public void testFindAll() {
        // Obtiene
        List<ForumTopic> topics = forumTopicRepository.findAll();
        // Inserta
        ForumTopic topic1 = new ForumTopic();
        topic1.setTitle("Maternidad");
        forumTopicRepository.save(topic1);

        ForumTopic topic2 = new ForumTopic();
        topic2.setTitle("Nutrición");
        forumTopicRepository.save(topic2);

        // Obtiene
        List<ForumTopic> allTopics = forumTopicRepository.findAll();

        // Verifica
        assertThat(allTopics).hasSize(3);
    }

    @Test
    public void testSave() {
        // Crea
        ForumTopic topic = new ForumTopic();
        topic.setTitle("Variados");
        ForumTopic savedForumTopic = forumTopicRepository.save(topic);

        // Verifica
        assertThat(savedForumTopic.getId()).isNotNull();
        assertThat(savedForumTopic.getTitle()).isEqualTo("Variados");
    }

    @Test
    public void testDeleteById() {
        // Inserta
        ForumTopic forumTopic = new ForumTopic();
        forumTopic.setTitle("Psicología");
        forumTopicRepository.save(forumTopic);

        // Elimina
        forumTopicRepository.deleteById(forumTopic.getId());

        // Verifica
        Optional<ForumTopic> deletedTopic = forumTopicRepository.findById(forumTopic.getId());
        assertThat(deletedTopic).isNotPresent();
    }
}