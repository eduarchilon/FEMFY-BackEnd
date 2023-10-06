package com.femfy.femfyapi.controller;

import dto.QuestionsUserMenstruationDTO;
import com.femfy.femfyapi.service.IQuestionsUserMenstruationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuestionsUserMenstruationControllerTest {

    @InjectMocks
    private QuestionsUserMenstruationController controller;

    @Mock
    private IQuestionsUserMenstruationService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetQuestionById() {
        Long questionId = 1L;
        QuestionsUserMenstruationDTO mockQuestion = new QuestionsUserMenstruationDTO();
        mockQuestion.setId(questionId);
        when(service.getQuestionsUserMenstruation(questionId)).thenReturn(Optional.of(mockQuestion));

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.getQuestionById(questionId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestion, response.getBody())
        );
    }

    @Test
    public void testGetQuestionByIdNotFound() {
        Long questionId = 1L;
        when(service.getQuestionsUserMenstruation(questionId)).thenReturn(Optional.empty());

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.getQuestionById(questionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllQuestions() {
        List<QuestionsUserMenstruationDTO> mockQuestions = new ArrayList<>();
        when(service.getQuestionsUserMenstruations()).thenReturn(mockQuestions);

        ResponseEntity<List<QuestionsUserMenstruationDTO>> response = controller.getAllQuestions();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestions, response.getBody())
        );
    }

    @Test
    public void testCreateOrUpdateQuestion() {
        QuestionsUserMenstruationDTO questionDTO = new QuestionsUserMenstruationDTO();
        when(service.saveOrUpdateQuestionsUserMenstruation(questionDTO)).thenReturn(questionDTO);

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.createOrUpdateQuestion(questionDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(questionDTO, response.getBody())
        );
    }

    @Test
    public void testDeleteQuestion() {
        Long questionId = 1L;
        doNothing().when(service).deleteQuestionsUserMenstruation(questionId);

        ResponseEntity<Void> response = controller.deleteQuestion(questionId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
