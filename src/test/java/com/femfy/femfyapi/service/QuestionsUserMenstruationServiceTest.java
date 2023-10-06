package com.femfy.femfyapi.service;

import dto.QuestionsUserMenstruationDTO;
import com.femfy.femfyapi.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.repository.QuestionsUserMenstruationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class QuestionsUserMenstruationServiceTest {

    @Mock
    private QuestionsUserMenstruationRepository repository;

    private QuestionsUserMenstruationService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new QuestionsUserMenstruationService(repository);
    }

    @Test
    public void testGetQuestionsUserMenstruations() {
        List<QuestionsUserMenstruation> menstruationList = new ArrayList<>();
        when(repository.findAll()).thenReturn(menstruationList);

        List<QuestionsUserMenstruationDTO> result = service.getQuestionsUserMenstruations();

        assertThat(result).isEmpty();
    }

    @Test
    public void testGetQuestionsUserMenstruationsWithData() {
        List<QuestionsUserMenstruation> menstruationList = new ArrayList<>();
        menstruationList.add(new QuestionsUserMenstruation());
        when(repository.findAll()).thenReturn(menstruationList);

        List<QuestionsUserMenstruationDTO> result = service.getQuestionsUserMenstruations();

        assertThat(result).isNotEmpty();
    }

    @Test
    public void testGetQuestionsUserMenstruation() {
        Long id = 1L;
        QuestionsUserMenstruation menstruation = new QuestionsUserMenstruation();
        when(repository.findById(id)).thenReturn(Optional.of(menstruation));

        Optional<QuestionsUserMenstruationDTO> result = service.getQuestionsUserMenstruation(id);

        assertThat(result).isPresent();
    }

    @Test
    public void testSaveOrUpdateQuestionsUserMenstruation() {
        QuestionsUserMenstruationDTO dto = new QuestionsUserMenstruationDTO();
        QuestionsUserMenstruation menstruation = new QuestionsUserMenstruation();
        when(repository.save(menstruation)).thenReturn(menstruation);

        QuestionsUserMenstruationDTO result = service.saveOrUpdateQuestionsUserMenstruation(dto);

        assertThat(result).isNotNull();
    }

    @Test
    public void testDeleteQuestionsUserMenstruation() {
        Long id = 1L;

        service.deleteQuestionsUserMenstruation(id);

        verify(repository, times(1)).deleteById(id);
    }
}
