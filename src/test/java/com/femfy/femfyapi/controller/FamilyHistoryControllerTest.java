package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.entity.FamilyHistory;
import com.femfy.femfyapi.service.IFamilyHistoryService;
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

@WebMvcTest(FamilyHistoryController.class)
public class FamilyHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFamilyHistoryService familyHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFamilyHistoryById() throws Exception {
        Long historialId = 1L;
        FamilyHistory familyHistory = new FamilyHistory();
        familyHistory.setId(historialId);
        when(familyHistoryService.getFamilyHistory(historialId)).thenReturn(Optional.of(familyHistory));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/familyHistory/{historialId}", historialId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(historialId))
                .andDo(print());

        verify(familyHistoryService, times(1)).getFamilyHistory(historialId);
    }

    @Test
    public void testGetAllFamilyHistories() throws Exception {
        List<FamilyHistory> familyHistories = new ArrayList<>();
        FamilyHistory familyHistory1 = new FamilyHistory();
        familyHistory1.setId(1L);
        familyHistories.add(familyHistory1);
        FamilyHistory familyHistory2 = new FamilyHistory();
        familyHistory2.setId(2L);
        familyHistories.add(familyHistory2);
        when(familyHistoryService.getFamilyHistories()).thenReturn(familyHistories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/familyHistory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andDo(print());

        verify(familyHistoryService, times(1)).getFamilyHistories();
    }

    @Test
    public void testSaveOrUpdateFamilyHistory() throws Exception {
        FamilyHistory familyHistory = new FamilyHistory();
        familyHistory.setId(1L);
        when(familyHistoryService.saveOrUpdateFamilyHistory(any(FamilyHistory.class))).thenReturn(familyHistory);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/familyHistory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"otherField\": \"value\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andDo(print());

        verify(familyHistoryService, times(1)).saveOrUpdateFamilyHistory(any(FamilyHistory.class));
    }

    @Test
    public void testDeleteFamilyHistory() throws Exception {
        Long historialId = 1L;
        doNothing().when(familyHistoryService).deleteFamilyHistory(historialId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/familyHistory/delete/{historialId}", historialId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());

        verify(familyHistoryService, times(1)).deleteFamilyHistory(historialId);
    }
}
