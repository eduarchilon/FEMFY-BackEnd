package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.ForumPost;
import com.femfy.femfyapi.entity.ForumTopic;
import com.femfy.femfyapi.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ForumPostRepositoryTest {

    @Autowired
    private ForumPostRepository forumPostRepository;

    @Autowired
    private ForumTopicRepository forumTopicRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        ForumTopic topic = new ForumTopic();
        topic.setId(1L);
        topic.setTitle("Enfermedades");
        topic.setImage("imagen.jpg");
        topic.setCreatedDate(new Date());
        forumTopicRepository.save(topic);

        User user = new User();
        user.setId(1L);
        user.setEmail("juanperez@gmail.com");
        userRepository.save(user);

        ForumPost post = new ForumPost();
        post.setId(1L);
        post.setTopic(topic);
        post.setUser(user);
        post.setContent("Experiencias con cáncer de mama");
        post.setCreatedDate(new Date());

        forumPostRepository.save(post);
    }
/*
    @Test
    public void testFindById() {
        // Inserta
        ForumTopic topic1 = new ForumTopic();
        topic1.setId(2L);
        topic1.setTitle("Sexualidad");
        topic1.setImage("imagen.jpg");
        topic1.setCreatedDate(new Date());
        forumTopicRepository.save(topic1);

        User user1 = new User();
        user1.setId(2L);
        user1.setEmail("juanperez2@gmail.com");
        userRepository.save(user1);

        ForumPost post1 = new ForumPost();
        post1.setId(2L);
        post1.setTopic(topic1);
        post1.setUser(user1);
        post1.setContent("Experiencias con el sexo post parteo");
        post1.setCreatedDate(new Date());
        forumPostRepository.save(post1);

        // Busca
        Optional<ForumPost> foundPost1 = forumPostRepository.findById(post1.getId());

        // Verifica
        assertThat(foundPost1).isPresent();
        assertThat(foundPost1.get().getId()).isEqualTo(post1.getId());
    }*/
/*
    @Test
    public void testFindAll() {
        // Obtiene
        List<ForumPost> posts = forumPostRepository.findAll();

        // Inserta más posts
        ForumTopic topic2 = new ForumTopic();
        topic2.setId(2L);
        topic2.setTitle("Sexualidad");
        topic2.setImage("imagen.jpg");
        topic2.setCreatedDate(new Date());
        forumTopicRepository.save(topic2);

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("juanperez2@gmail.com");
        userRepository.save(user2);

        ForumPost post2 = new ForumPost();
        post2.setId(3L);
        post2.setTopic(topic2);
        post2.setUser(user2);
        post2.setContent("Contenido del post 3");
        post2.setCreatedDate(new Date());
        forumPostRepository.save(post2);

        ForumTopic topic3 = new ForumTopic();
        topic3.setId(3L);
        forumTopicRepository.save(topic3);

        User user3 = new User();
        user3.setId(3L);
        user3.setEmail("juanperez3@gmail.com");
        userRepository.save(user3);

        ForumPost post3 = new ForumPost();
        post3.setId(4L);
        post3.setTopic(topic2);
        post3.setUser(user2);
        post3.setContent("Contenido del post 4");
        post3.setCreatedDate(new Date());
        forumPostRepository.save(post3);

        // Obtiene
        List<ForumPost> allPosts = forumPostRepository.findAll();

        // Verifica
        assertThat(allPosts).hasSize(3); // Asegúrate de ajustar el tamaño según las inserciones
    }*/
/*
    @Test
    public void testSave() {
        // Crea
        ForumTopic topic2 = new ForumTopic();
        topic2.setId(4L);
        topic2.setTitle("Sexualidad");
        topic2.setImage("imagen.jpg");
        topic2.setCreatedDate(new Date());
        forumTopicRepository.save(topic2);

        User user2 = new User();
        user2.setId(4L);
        user2.setEmail("juanperez2@gmail.com");
        userRepository.save(user2);

        ForumPost post2 = new ForumPost();
        post2.setId(4L);
        post2.setTopic(topic2);
        post2.setUser(user2);
        post2.setContent("Contenido del post 5");
        post2.setCreatedDate(new Date());

        // Guarda el post en la base de datos
        ForumPost savedPost2 = forumPostRepository.save(post2);

        // Verifica
        assertThat(savedPost2.getId()).isNotNull();
        assertThat(savedPost2.getContent()).isEqualTo("Contenido del post 5");
    }

    @Test
    public void testDeleteById() {
        // Inserta
        ForumTopic topic1 = new ForumTopic();
        topic1.setId(2L);
        topic1.setTitle("Sexualidad");
        topic1.setImage("imagen.jpg");
        topic1.setCreatedDate(new Date());
        forumTopicRepository.save(topic1);

        User user1 = new User();
        user1.setId(2L);
        user1.setEmail("juanperez2@gmail.com");
        userRepository.save(user1);

        ForumPost post = new ForumPost();
        post.setTopic(topic1);
        post.setUser(user1);
        post.setContent("Contenido del post 6");
        post.setCreatedDate(new Date());
        forumPostRepository.save(post);

        // Elimina
        forumPostRepository.deleteById(post.getId());

        // Verifica
        Optional<ForumPost> deletedPost = forumPostRepository.findById(post.getId());
        assertThat(deletedPost).isNotPresent();
    }*/
/*
    @Test
    public void testFindByUserId() {
        ForumTopic topic1 = new ForumTopic();
        topic1.setId(2L);
        topic1.setTitle("Sexualidad");
        topic1.setImage("imagen.jpg");
        topic1.setCreatedDate(new Date());
        forumTopicRepository.save(topic1);

        User user1 = new User();
        user1.setId(2L);
        user1.setEmail("juanperez2@gmail.com");
        userRepository.save(user1);

        // Inserta algunos ForumPosts asociados al mismo User
        ForumPost post1 = new ForumPost();
        post1.setUser(user1);
        post1.setTopic(topic1);
        post1.setContent("Contenido del post 1");
        post1.setCreatedDate(new Date());
        forumPostRepository.save(post1);

        ForumPost post2 = new ForumPost();
        post2.setUser(user1);
        post2.setTopic(topic1);
        post2.setContent("Contenido del post 2");
        post2.setCreatedDate(new Date());
        forumPostRepository.save(post2);

        // Realiza la búsqueda
        List<ForumPost> postsByUserId = forumPostRepository.findByUserId(user1.getId());

        // Verifica
        assertThat(postsByUserId).isNotEmpty();
        assertThat(postsByUserId).hasSize(2);
        for (ForumPost post : postsByUserId) {
            assertThat(post.getUser().getId()).isEqualTo(user1.getId());
        }
    }

    @Test
    public void testFindByTopicId() {
        ForumTopic topic2 = new ForumTopic();
        topic2.setId(2L);
        topic2.setTitle("Sexualidad");
        topic2.setImage("imagen.jpg");
        topic2.setCreatedDate(new Date());
        forumTopicRepository.save(topic2);

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("juanperez2@gmail.com");
        userRepository.save(user2);

        // Inserta algunos ForumPosts asociados al mismo Topic
        ForumPost post2 = new ForumPost();
        post2.setUser(user2);
        post2.setTopic(topic2);
        post2.setContent("Contenido del post 1");
        post2.setCreatedDate(new Date());
        forumPostRepository.save(post2);

        ForumPost post3 = new ForumPost();
        post3.setUser(user2);
        post3.setTopic(topic2);
        post3.setContent("Contenido del post 2");
        post3.setCreatedDate(new Date());
        forumPostRepository.save(post3);

        // Realiza la búsqueda
        List<ForumPost> postsByTopicId = forumPostRepository.findByTopicId(topic2.getId());

        // Verifica
        assertThat(postsByTopicId).isNotEmpty();
        assertThat(postsByTopicId).hasSize(2); // Asegúrate de ajustar el tamaño según las inserciones
        for (ForumPost post : postsByTopicId) {
            assertThat(post.getTopic().getId()).isEqualTo(topic2.getId());
        }
    }*/
}