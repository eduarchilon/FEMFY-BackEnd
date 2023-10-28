package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.delivery.controller.QuestionsUserMenopauseController;
import com.femfy.femfyapi.delivery.dto.QuestionsUserMenopauseDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserMenopause;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserMenopauseService;
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

class QuestionsUserMenopauseControllerTest {

    @InjectMocks
    private QuestionsUserMenopauseController controller;

    @Mock
    private IQuestionsUserMenopauseService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetQuestionById() {
        Long questionId = 1L;
        QuestionsUserMenopause mockQuestion = new QuestionsUserMenopause();
        mockQuestion.setId(questionId);
        when(service.getQuestionsUserMenopause(questionId)).thenReturn(Optional.of(mockQuestion));

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.getQuestionById(questionId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestion, response.getBody())
        );
    }

    @Test
    void testGetQuestionByIdNotFound() {
        Long questionId = 1L;
        when(service.getQuestionsUserMenopause(questionId)).thenReturn(Optional.empty());

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.getQuestionById(questionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllQuestions() {
        List<QuestionsUserMenopause> mockQuestions = new ArrayList<>();
        when(service.getQuestionsUserMenopause()).thenReturn(mockQuestions);

        ResponseEntity<List<QuestionsUserMenopauseDTO>> response = controller.getAllQuestions();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestions, response.getBody())
        );
    }

    @Test
    void testCreateQuestion() {
        QuestionsUserMenopauseDTO questionDTO = new QuestionsUserMenopauseDTO();
        QuestionsUserMenopause question = new QuestionsUserMenopause();
        when(service.saveQuestionsUserMenopause(any(QuestionsUserMenopause.class))).thenReturn(question);

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.createQuestion(questionDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(questionDTO, response.getBody())
        );
    }

    @Test
    void testUpdateQuestion() {
        QuestionsUserMenopauseDTO questionDTO = new QuestionsUserMenopauseDTO();
        QuestionsUserMenopause question = new QuestionsUserMenopause();
        questionDTO.setId(1L);
        when(service.updateQuestionsUserMenopause(question)).thenReturn(question);

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.updateQuestion(questionDTO);

        verify(service, times(1)).updateQuestionsUserMenopause(question);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateQuestionNotFound() {
        Long questionId = 1L;
        QuestionsUserMenopauseDTO questionDTO = new QuestionsUserMenopauseDTO();
        QuestionsUserMenopause question = new QuestionsUserMenopause();
        questionDTO.setId(questionId);

        when(service.updateQuestionsUserMenopause(question)).thenReturn(null);

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.updateQuestion(questionDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;
        doNothing().when(service).deleteQuestionsUserMenopause(questionId);

        ResponseEntity<Void> response = controller.deleteQuestion(questionId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}