package com.femfy.femfyapi.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.femfy.femfyapi.exception.CustomException;
import com.femfy.femfyapi.service.CycleService;
import com.femfy.femfyapi.service.ICycleService;
import dto.CycleDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CycleController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CycleControllerTest {

    final ObjectMapper mapper = new ObjectMapper();
    private MockMvc mockMvc;
    private CycleDTO dto;

    @MockBean
    private ICycleService cycleServiceMock;
    @InjectMocks
    private CycleController cycleController;

    @BeforeAll
    void init() {
        dto = new CycleDTO();
        dto.setId(1L);
        dto.setStatus("Alegre");
        dto.setIdUser(1L);
        dto.setDateBeging("2023-10-05");
        dto.setDateEnd("2023-10-09");

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cycleController)
                .build();
    }


    @Test
    void getCycleTest() throws Exception {
        when(cycleServiceMock.getCycleByIdUserAndDateBeging(anyLong(), eq("2023-10-05"))).thenReturn(dto);

        mockMvc.perform(get("/api/v1/cycle/getCycle/1")
                        .param("dateBeging", "2023-10-05")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value("2023-10-05"))
                .andExpect(jsonPath("$.dateEnd").value("2023-10-09"))
                .andExpect(jsonPath("$.status").value("Alegre"));
    }

    @Test
    public void getCycleExceptionTest() throws Exception {

        when(cycleServiceMock.getCycleByIdUserAndDateBeging(anyLong(), eq("2023-10-05"))).thenThrow(new CustomException("error"));

        mockMvc.perform(get("/api/v1/cycle/getCycle/1")
                        .param("dateBeging", "2023-10-05")
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("error"));
    }

    @Test()
    void getCycleHistoryTest() throws Exception {
        List<CycleDTO> list = new ArrayList<>();
        list.add(dto);
        list.add(dto);
        when(cycleServiceMock.getCycleHistory(anyLong())).thenReturn(list);

        mockMvc.perform(get("/api/v1/cycle/getCycleHistory/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getCycleHistoryExceptionTest() throws Exception {
        when(cycleServiceMock.getCycleHistory(anyLong())).thenThrow(new CustomException("error"));

        mockMvc.perform(get("/api/v1/cycle/getCycleHistory/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("error"));
    }
}
