package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.delivery.controller.QuestionsUserMenstruationController;
import com.femfy.femfyapi.delivery.dto.QuestionsUserMenopauseDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserMenstruationService;
import com.femfy.femfyapi.delivery.dto.QuestionsUserMenstruationDTO;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        User user = new User();
        user.setId(1L);
        Long questionId = 1L;
        QuestionsUserMenstruationDTO dto = new QuestionsUserMenstruationDTO();
        dto.setUserId(1L);
        dto.setId(1L);
        QuestionsUserMenstruation mockQuestion = new QuestionsUserMenstruation();
        mockQuestion.setUser(user);
        mockQuestion.setId(questionId);
        when(service.getQuestionsUserMenstruation(questionId)).thenReturn(Optional.of(mockQuestion));

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.getQuestionById(questionId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(dto, response.getBody())
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
        List<QuestionsUserMenstruation> mockQuestions = new ArrayList<>();
        when(service.getQuestionsUserMenstruations()).thenReturn(mockQuestions);

        ResponseEntity<List<QuestionsUserMenstruationDTO>> response = controller.getAllQuestions();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestions, response.getBody())
        );
    }

    @Test
    public void testCreateQuestion() {
        User user = new User();
        user.setId(1L);
        QuestionsUserMenstruationDTO questionDTO = new QuestionsUserMenstruationDTO();
        questionDTO.setUserId(1L);
        QuestionsUserMenstruation question = new QuestionsUserMenstruation();
        question.setUser(user);
        when(service.saveQuestionsUserMenstruation(any(QuestionsUserMenstruation.class))).thenReturn(question);

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.createQuestion(questionDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(questionDTO, response.getBody())
        );
    }

    @Test
    public void testUpdateQuestion() {
        User user = new User();
        user.setId(1L);
        QuestionsUserMenstruationDTO questionDTO = new QuestionsUserMenstruationDTO();
        questionDTO.setId(1L);
        questionDTO.setUserId(1L);
        QuestionsUserMenstruation question = new QuestionsUserMenstruation();
        question.setId(1L);
        question.setUser(user);
        when(service.updateQuestionsUserMenstruation(any(QuestionsUserMenstruation.class))).thenReturn(question);

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.updateQuestion(questionDTO);

        verify(service, times(1)).updateQuestionsUserMenstruation(question);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateQuestionNotFound() {
        QuestionsUserMenstruationDTO questionDTO = new QuestionsUserMenstruationDTO();
        QuestionsUserMenstruation question = new QuestionsUserMenstruation();


        QuestionsUserMenstruationDTO updatedQuestion = new QuestionsUserMenstruationDTO();
        updatedQuestion.setId(1L);

        when(service.updateQuestionsUserMenstruation(question)).thenReturn(question);

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.updateQuestion(questionDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateQuestionInvalidId() {
        QuestionsUserMenstruationDTO questionDTO = new QuestionsUserMenstruationDTO();
        questionDTO.setId(null);

        IQuestionsUserMenstruationService serviceMock = org.mockito.Mockito.mock(IQuestionsUserMenstruationService.class);

        QuestionsUserMenstruationController controller = new QuestionsUserMenstruationController(serviceMock);

        ResponseEntity<QuestionsUserMenstruationDTO> response = controller.updateQuestion(questionDTO);

        verify(serviceMock, never()).updateQuestionsUserMenstruation(any());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteQuestion() {
        Long questionId = 1L;
        doNothing().when(service).deleteQuestionsUserMenstruation(questionId);

        ResponseEntity<Void> response = controller.deleteQuestion(questionId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
