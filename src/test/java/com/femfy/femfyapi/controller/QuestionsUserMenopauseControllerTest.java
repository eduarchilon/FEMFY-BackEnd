package com.femfy.femfyapi.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.femfy.femfyapi.delivery.controller.QuestionsUserMenopauseController;
import com.femfy.femfyapi.delivery.dto.QuestionsUserMenopauseDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserMenopause;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserMenopauseService;

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
        User user = new User();
        user.setId(1L);
        Long questionId = 1L;
        QuestionsUserMenopauseDTO dto = new QuestionsUserMenopauseDTO();
        dto.setId(1L);
        dto.setUserId(1L);
        dto.setSuffocation(0);
        dto.setChangesInMenstrualCycle(0);
        dto.setVaginalDryness(0);
        dto.setChangesInSkinAndHair(0);
        dto.setMoodChanges(0);
        dto.setSleepingDifficulties(0);
        dto.setWeightGain(0);;
        dto.setLossOfBoneDensity(0);
        dto.setChangesInLibido(0);
        
        QuestionsUserMenopause mockQuestion = new QuestionsUserMenopause();
        mockQuestion.setId(questionId);
        mockQuestion.setUser(user);
        mockQuestion.setSuffocation(0);
        mockQuestion.setChangesInMenstrualCycle(0);
        mockQuestion.setVaginalDryness(0);
        mockQuestion.setChangesInSkinAndHair(0);
        mockQuestion.setMoodChanges(0);
        mockQuestion.setSleepingDifficulties(0);
        mockQuestion.setWeightGain(0);;
        mockQuestion.setLossOfBoneDensity(0);
        mockQuestion.setChangesInLibido(0);

        when(service.getQuestionsUserMenopause(questionId)).thenReturn(Optional.of(mockQuestion));

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.getQuestionById(questionId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(dto, response.getBody())
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
        User user = new User();
        user.setId(1L);
        QuestionsUserMenopauseDTO questionDTO = new QuestionsUserMenopauseDTO();
        questionDTO.setId(1L);
        questionDTO.setUserId(1L);
        questionDTO.setSuffocation(0);
        questionDTO.setChangesInMenstrualCycle(0);
        questionDTO.setVaginalDryness(0);
        questionDTO.setChangesInSkinAndHair(0);
        questionDTO.setMoodChanges(0);
        questionDTO.setSleepingDifficulties(0);
        questionDTO.setWeightGain(0);
        questionDTO.setLossOfBoneDensity(0);
        questionDTO.setChangesInLibido(0);
        QuestionsUserMenopause mockQuestion = new QuestionsUserMenopause();
        mockQuestion.setId(1L);
        mockQuestion.setUser(user);
        mockQuestion.setSuffocation(0);
        mockQuestion.setChangesInMenstrualCycle(0);
        mockQuestion.setVaginalDryness(0);
        mockQuestion.setChangesInSkinAndHair(0);
        mockQuestion.setMoodChanges(0);
        mockQuestion.setSleepingDifficulties(0);
        mockQuestion.setWeightGain(0);
        mockQuestion.setLossOfBoneDensity(0);
        mockQuestion.setChangesInLibido(0);
        when(service.saveQuestionsUserMenopause(any(QuestionsUserMenopause.class))).thenReturn(mockQuestion);

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.createQuestion(questionDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(questionDTO, response.getBody())
        );
    }

    @Test
    void testUpdateQuestion() {
        User user = new User();
        user.setId(1L);
        QuestionsUserMenopauseDTO questionDTO = new QuestionsUserMenopauseDTO();
        questionDTO.setId(1L);
        questionDTO.setUserId(1L);
        questionDTO.setUserId(1L);
        questionDTO.setSuffocation(0);
        questionDTO.setChangesInMenstrualCycle(0);
        questionDTO.setVaginalDryness(0);
        questionDTO.setChangesInSkinAndHair(0);
        questionDTO.setMoodChanges(0);
        questionDTO.setSleepingDifficulties(0);
        questionDTO.setWeightGain(0);
        questionDTO.setLossOfBoneDensity(0);
        questionDTO.setChangesInLibido(0);
        QuestionsUserMenopause mockQuestion = new QuestionsUserMenopause();
        mockQuestion.setId(1L);
        mockQuestion.setUser(user);
        mockQuestion.setSuffocation(0);
        mockQuestion.setChangesInMenstrualCycle(0);
        mockQuestion.setVaginalDryness(0);
        mockQuestion.setChangesInSkinAndHair(0);
        mockQuestion.setMoodChanges(0);
        mockQuestion.setSleepingDifficulties(0);
        mockQuestion.setWeightGain(0);
        mockQuestion.setLossOfBoneDensity(0);
        mockQuestion.setChangesInLibido(0);

        when(service.updateQuestionsUserMenopause(any(QuestionsUserMenopause.class))).thenReturn(mockQuestion);

        ResponseEntity<QuestionsUserMenopauseDTO> response = controller.updateQuestion(questionDTO);

        verify(service, times(1)).updateQuestionsUserMenopause(mockQuestion);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateQuestionNotFound() {
        QuestionsUserMenopauseDTO questionDTO = new QuestionsUserMenopauseDTO();
        QuestionsUserMenopause question = new QuestionsUserMenopause();

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