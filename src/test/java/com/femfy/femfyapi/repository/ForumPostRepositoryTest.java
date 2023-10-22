package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.ForumPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ForumPostRepositoryTest {

    @Autowired
    private ForumPostRepository forumPostRepository;

    @BeforeEach
    public void setUp() {
        ForumPost post = new ForumPost();
        post.setContent("Contenido");
        post.setCreatedDate(new Date());

        forumPostRepository.save(post);
    }

    @Test
    public void testFindById() {
        // Inserta
        ForumPost post = new ForumPost();
        post.setContent("Contenido 2");
        post.setCreatedDate(new Date());
        forumPostRepository.save(post);

        // Busca
        Optional<ForumPost> foundPost = forumPostRepository.findById(post.getId());

        // Verifica
        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getId()).isEqualTo(post.getId());
    }

    @Test
    public void testFindAll() {
        // Obtiene
        List<ForumPost> posts = forumPostRepository.findAll();

        // Inserta
        ForumPost post1 = new ForumPost();
        post1.setContent("Contenido 3");
        post1.setCreatedDate(new Date());
        forumPostRepository.save(post1);

        ForumPost post2 = new ForumPost();
        post2.setContent("Contenido 4");
        post2.setCreatedDate(new Date());
        forumPostRepository.save(post2);

        // Obtiene
        List<ForumPost> allPosts = forumPostRepository.findAll();

        // Verifica
        assertThat(allPosts).hasSize(3);
    }

    @Test
    public void testSave() {
        // Crea
        ForumPost post = new ForumPost();
        post.setContent("Contenido 5");
        post.setCreatedDate(new Date());
        ForumPost savedPost = forumPostRepository.save(post);

        // Verifica
        assertThat(savedPost.getId()).isNotNull();
        assertThat(savedPost.getContent()).isEqualTo("Contenido 5");
    }

    @Test
    public void testDeleteById() {
        // Inserta
        ForumPost post = new ForumPost();
        post.setContent("Contenido 6");
        post.setCreatedDate(new Date());
        forumPostRepository.save(post);

        // Elimina
        forumPostRepository.deleteById(post.getId());

        // Verifica
        Optional<ForumPost> deletedPost = forumPostRepository.findById(post.getId());
        assertThat(deletedPost).isNotPresent();
    }
}