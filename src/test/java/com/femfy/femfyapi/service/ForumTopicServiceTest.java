package com.femfy.femfyapi.service;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.femfy.femfyapi.domain.entity.ForumTopic;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.repository.ForumTopicRepository;
import com.femfy.femfyapi.domain.service.ForumTopicService;

class ForumTopicServiceTest {

    @Mock
    private ForumTopicRepository repository;

    @InjectMocks
    private ForumTopicService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllForumTopicsWithData() {
        ForumTopic topic = new ForumTopic();
        topic.setId(1L);
        topic.setTitle("Enfermedades");

        List<ForumTopic> topics = Collections.singletonList(topic);
        when(repository.findAll()).thenReturn(topics);

        List<ForumTopic> result = service.getAllForumTopics();

        assertNotNull(result);
        assertEquals(1, result.size());

        ForumTopic dto = result.get(0);
        assertEquals(1L, dto.getId());
        assertEquals("Enfermedades", dto.getTitle());
    }

    @Test
    void testGetAllForumTopicsWithNoData() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<ForumTopic> result = service.getAllForumTopics();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetForumTopicFound() {
        Long id = 1L;

        ForumTopic topic = new ForumTopic();
        topic.setId(id);
        topic.setTitle("Maternidad");

        when(repository.findById(id)).thenReturn(Optional.of(topic));

        Optional<ForumTopic> result = service.getForumTopicById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("Maternidad", result.get().getTitle());
    }

    @Test
    void testGetForumTopicNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<ForumTopic> result = service.getForumTopicById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveForumTopic() {
        ForumTopic dtoToSave = new ForumTopic();
        dtoToSave.setTitle("Sexualidad");

        ForumTopic savedEntity = new ForumTopic();
        savedEntity.setId(1L);
        savedEntity.setTitle("Sexualidad");

        when(repository.save(any(ForumTopic.class))).thenReturn(savedEntity);

        ForumTopic result = service.saveForumTopic(dtoToSave);

        assertNotNull(result.getId());
        assertEquals("Sexualidad", result.getTitle());
    }

    @Test
    void testUpdateForumTopic() {
        Long idToUpdate = 1L;

        ForumTopic existingEntity = new ForumTopic();
        existingEntity.setId(idToUpdate);
        existingEntity.setTitle("Nutri");

        ForumTopic updatedDTO = new ForumTopic();
        updatedDTO.setId(idToUpdate);
        updatedDTO.setTitle("Nutrición");

        when(repository.findById(idToUpdate)).thenReturn(Optional.of(existingEntity));
        when(repository.save(existingEntity)).thenReturn(existingEntity);

        ForumTopic result = service.updateForumTopic(updatedDTO);

        assertEquals(idToUpdate, result.getId());
        assertEquals("Nutrición", result.getTitle());
    }

    @Test
    void testUpdateForumTopicNotFound() {
        Long idToUpdate = 1L;
        ForumTopic updated = new ForumTopic();
        updated.setId(idToUpdate);

        when(repository.findById(idToUpdate)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateForumTopic(updated));
    }

    @Test
    void testDeleteForumTopic() {
        Long idToDelete = 1L;
        doNothing().when(repository).deleteById(idToDelete);

        assertDoesNotThrow(() -> service.deleteForumTopic(idToDelete));

        verify(repository, times(1)).deleteById(idToDelete);
    }
}