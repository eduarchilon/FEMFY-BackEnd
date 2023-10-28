package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.delivery.controller.QuestionsUserFamilyHistoryController;
import com.femfy.femfyapi.domain.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserFamilyHistoryService;
import com.femfy.femfyapi.delivery.dto.QuestionsUserFamilyHistoryDTO;
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

class QuestionsUserFamilyHistoryControllerTest {

    @InjectMocks
    private QuestionsUserFamilyHistoryController controller;

    @Mock
    private IQuestionsUserFamilyHistoryService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestionById() {
        User user = new User();
        user.setId(1L);
        Long questionId = 1L;
        QuestionsUserFamilyHistoryDTO dto = new QuestionsUserFamilyHistoryDTO();
        dto.setId(questionId);
        dto.setUserId(1L);
        QuestionsUserFamilyHistory mockQuestion = new QuestionsUserFamilyHistory();
        mockQuestion.setUser(user);
        mockQuestion.setId(questionId);
        when(service.getQuestionsUserFamilyHistory(questionId)).thenReturn(Optional.of(mockQuestion));

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.getQuestionById(questionId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(dto, response.getBody())
        );
    }

    @Test
    void testGetQuestionByIdNotFound() {
        Long questionId = 1L;
        when(service.getQuestionsUserFamilyHistory(questionId)).thenReturn(Optional.empty());

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.getQuestionById(questionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllQuestions() {
        List<QuestionsUserFamilyHistory> mockQuestions = new ArrayList<>();
        when(service.getQuestionsUserFamilyHistories()).thenReturn(mockQuestions);

        ResponseEntity<List<QuestionsUserFamilyHistoryDTO>> response = controller.getAllQuestions();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestions, response.getBody())
        );
    }

    @Test
    void testCreateQuestion() {
        User user = new User();
        user.setId(1L);
        QuestionsUserFamilyHistoryDTO questionDTO = new QuestionsUserFamilyHistoryDTO();
        QuestionsUserFamilyHistory question = new QuestionsUserFamilyHistory();
        question.setUser(user);
        questionDTO.setUserId(1L);
        when(service.saveQuestionsUserFamilyHistory(any(QuestionsUserFamilyHistory.class))).thenReturn(question);

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.createQuestion(questionDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(questionDTO, response.getBody())
        );
    }

    @Test
    void testUpdateQuestion() {
        User user = new User();
        user.setId(1L);
        QuestionsUserFamilyHistoryDTO questionDTO = new QuestionsUserFamilyHistoryDTO();
        QuestionsUserFamilyHistory question = new QuestionsUserFamilyHistory();
        questionDTO.setId(1L);
        questionDTO.setUserId(1L);
        question.setId(1L);
        question.setUser(user);
        when(service.updateQuestionsUserFamilyHistory(any(QuestionsUserFamilyHistory.class))).thenReturn(question);

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.updateQuestion(questionDTO);

        verify(service, times(1)).updateQuestionsUserFamilyHistory(question);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateQuestionNotFound() {
        Long questionId = 1L;
        QuestionsUserFamilyHistoryDTO questionDTO = new QuestionsUserFamilyHistoryDTO();
        QuestionsUserFamilyHistory question = new QuestionsUserFamilyHistory();

        when(service.updateQuestionsUserFamilyHistory(question)).thenReturn(null);

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.updateQuestion(questionDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;
        doNothing().when(service).deleteQuestionsUserFamilyHistory(questionId);

        ResponseEntity<Void> response = controller.deleteQuestion(questionId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}