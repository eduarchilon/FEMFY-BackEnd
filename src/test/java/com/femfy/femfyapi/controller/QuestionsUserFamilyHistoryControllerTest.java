package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.service.IQuestionsUserFamilyHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(QuestionsUserFamilyHistoryController.class)
public class QuestionsUserFamilyHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuestionsUserFamilyHistoryService familyHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFamilyHistoryById() throws Exception {
        Long historialId = 1L;
        QuestionsUserFamilyHistory questionsUserFamilyHistory = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory.setId(historialId);
        when(familyHistoryService.getFamilyHistory(historialId)).thenReturn(Optional.of(questionsUserFamilyHistory));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questionsUserFamilyHistory/{historialId}", historialId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(historialId))
                .andDo(print());

        verify(familyHistoryService, times(1)).getFamilyHistory(historialId);
    }

    @Test
    public void testGetAllFamilyHistories() throws Exception {
        List<QuestionsUserFamilyHistory> familyHistories = new ArrayList<>();
        QuestionsUserFamilyHistory questionsUserFamilyHistory1 = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory1.setId(1L);
        familyHistories.add(questionsUserFamilyHistory1);
        QuestionsUserFamilyHistory questionsUserFamilyHistory2 = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory2.setId(2L);
        familyHistories.add(questionsUserFamilyHistory2);
        when(familyHistoryService.getFamilyHistories()).thenReturn(familyHistories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questionsUserFamilyHistory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andDo(print());

        verify(familyHistoryService, times(1)).getFamilyHistories();
    }

    @Test
    public void testSaveOrUpdateFamilyHistory() throws Exception {
        QuestionsUserFamilyHistory questionsUserFamilyHistory = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory.setId(1L);
        when(familyHistoryService.saveOrUpdateFamilyHistory(any(QuestionsUserFamilyHistory.class))).thenReturn(questionsUserFamilyHistory);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/questionsUserFamilyHistory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"otherField\": \"value\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andDo(print());

        verify(familyHistoryService, times(1)).saveOrUpdateFamilyHistory(any(QuestionsUserFamilyHistory.class));
    }

    @Test
    public void testDeleteFamilyHistory() throws Exception {
        Long historialId = 1L;
        doNothing().when(familyHistoryService).deleteFamilyHistory(historialId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/questionsUserFamilyHistory/delete/{historialId}", historialId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());

        verify(familyHistoryService, times(1)).deleteFamilyHistory(historialId);
    }
}
