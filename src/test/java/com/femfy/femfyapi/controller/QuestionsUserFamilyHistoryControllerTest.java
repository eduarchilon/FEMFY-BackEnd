/*
package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.IQuestionsUserFamilyHistoryService;
import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionsUserFamilyHistoryControllerTest {

    @InjectMocks
    private QuestionsUserFamilyHistoryController controller;

    @Mock
    private IQuestionsUserFamilyHistoryService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetQuestionById() {
        Long historialId = 1L;
        QuestionsUserFamilyHistory mockQuestion = createMockQuestion(historialId);
        when(service.getQuestionsUserFamilyHistory(historialId)).thenReturn(Optional.of(mockQuestion));

        ResponseEntity<QuestionsUserFamilyHistory> response = controller.getQuestionById(historialId);

        assertResponse(HttpStatus.OK, mockQuestion, response);
    }

    @Test
    public void testGetQuestionByIdNotFound() {
        Long historialId = 1L;
        when(service.getQuestionsUserFamilyHistory(historialId)).thenReturn(Optional.empty());

        ResponseEntity<QuestionsUserFamilyHistory> response = controller.getQuestionById(historialId);

        assertResponse(HttpStatus.NOT_FOUND, null, response);
    }

    @Test
    public void testGetAllQuestions() {
        List<QuestionsUserFamilyHistory> mockQuestions = createMockQuestionList();
        when(service.getQuestionsUserFamilyHistories()).thenReturn(mockQuestions);

        ResponseEntity<List<QuestionsUserFamilyHistory>> response = controller.getAllQuestions();

        assertResponse(HttpStatus.OK, mockQuestions, response);
    }

    @Test
    public void testCreateQuestion() {
        QuestionsUserFamilyHistory questionDTO = createMockQuestion(1L);
        when(service.saveQuestionsUserFamilyHistory(any(QuestionsUserFamilyHistory.class))).thenReturn(questionDTO);

        ResponseEntity<QuestionsUserFamilyHistory> response = controller.createQuestion(questionDTO);

        assertResponse(HttpStatus.CREATED, questionDTO, response);
    }

    @Test
    public void testUpdateQuestion() {
        Long historialId = 1L;
        QuestionsUserFamilyHistory questionDTO = createMockQuestion(historialId);
        when(service.updateQuestionsUserFamilyHistory(any(QuestionsUserFamilyHistory.class))).thenReturn(questionDTO);

        ResponseEntity<QuestionsUserFamilyHistory> response = controller.updateQuestion(questionDTO);

        verify(service).updateQuestionsUserFamilyHistory(questionDTO);
        assertResponse(HttpStatus.OK, questionDTO, response);
    }

    @Test
    public void testUpdateQuestionNotFound() {
        Long historialId = 1L;
        QuestionsUserFamilyHistory questionDTO = createMockQuestion(historialId);
        QuestionsUserFamilyHistory updatedQuestion = createMockQuestion(historialId);
        when(service.updateQuestionsUserFamilyHistory(questionDTO)).thenReturn(updatedQuestion);

        ResponseEntity<QuestionsUserFamilyHistory> response = controller.updateQuestion(questionDTO);

        verify(service).updateQuestionsUserFamilyHistory(questionDTO);
        assertResponse(HttpStatus.OK, updatedQuestion, response);
    }

    @Test
    public void testUpdateQuestionInvalidId() {
        QuestionsUserFamilyHistory questionDTO = createMockQuestion(null);

        IQuestionsUserFamilyHistoryService serviceMock = org.mockito.Mockito.mock(IQuestionsUserFamilyHistoryService.class);

        QuestionsUserFamilyHistoryController controller = new QuestionsUserFamilyHistoryController(serviceMock);

        ResponseEntity<QuestionsUserFamilyHistory> response = controller.updateQuestion(questionDTO);

        verify(serviceMock, never()).updateQuestionsUserFamilyHistory(any());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteQuestion() {
        Long historialId = 1L;
        doNothing().when(service).deleteQuestionsUserFamilyHistory(historialId);

        ResponseEntity<Void> response = controller.deleteQuestion(historialId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private QuestionsUserFamilyHistory createMockQuestion(Long historialId) {
        QuestionsUserFamilyHistory mockQuestion = new QuestionsUserFamilyHistory();
        mockQuestion.setId(historialId);
        return mockQuestion;
    }

    private List<QuestionsUserFamilyHistory> createMockQuestionList() {
        List<QuestionsUserFamilyHistory> mockQuestions = new ArrayList<>();
        mockQuestions.add(createMockQuestion(1L));
        mockQuestions.add(createMockQuestion(2L));
        return mockQuestions;
    }

    private <T> void assertResponse(HttpStatus expectedStatus, T expectedBody, ResponseEntity<T> response) {
        assertAll(
                () -> assertEquals(expectedStatus, response.getStatusCode()),
                () -> assertEquals(expectedBody, response.getBody())
        );
    }
}*/