package com.femfy.femfyapi.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.femfy.femfyapi.delivery.controller.CycleController;
import com.femfy.femfyapi.domain.entity.Cycle;
import com.femfy.femfyapi.domain.exception.CustomException;
import com.femfy.femfyapi.delivery.dto.CycleDTO;
import com.femfy.femfyapi.domain.interfaces.ICycleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(CycleController.class)
@TestInstance(Lifecycle.PER_CLASS)
class CycleControllerTest {

    final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    private Cycle cycle;
    private CycleDTO dto;
    @MockBean
    ICycleService cycleServiceMock;


    @BeforeAll
    public void init() {
        dto = new CycleDTO();
        cycle = new Cycle();
        cycle.setId(1L);
        cycle.setStatus("Alegre");
        cycle.setIdUser(1L);
        cycle.setDateEnd(Date.valueOf("2023-10-20"));
        cycle.setDateBeging(Date.valueOf("2023-10-29"));
    }


    @Test
    void getCycleTest() throws Exception {
        when(cycleServiceMock.getCycleByIdUserAndDateBeging(anyLong(), anyString())).thenReturn(cycle);

        mockMvc.perform(get("/api/v1/cycle/getCycle/1")
                        .param("dateBeging", "2023-10-29")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value("2023-10-29"))
                .andExpect(jsonPath("$.dateEnd").value("2023-10-20"))
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
        List<Cycle> list = new ArrayList<>();
        list.add(cycle);
        list.add(cycle);
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

    @Test()
    void registerCycleEndTest() throws Exception {

        when(cycleServiceMock.registerCycleEnd(any(Cycle.class))).thenReturn(cycle);

        mockMvc.perform(put("/api/v1/cycle/registerCycleEnd")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value("2023-10-29"))
                .andExpect(jsonPath("$.dateEnd").value("2023-10-20"))
                .andExpect(jsonPath("$.status").value("Alegre"));
    }

    @Test()
    void registerCycleEndExceptionTest() throws Exception {

        when(cycleServiceMock.registerCycleEnd(any(Cycle.class))).thenThrow(new CustomException("error"));

        mockMvc.perform(put("/api/v1/cycle/registerCycleEnd")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("error"));
    }

    @Test()
    void registerCycleStartTest() throws Exception {

        when(cycleServiceMock.registerCycleStart(any(Cycle.class))).thenReturn(cycle);

        mockMvc.perform(post("/api/v1/cycle/registerCycleStart")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value("2023-10-29"))
                .andExpect(jsonPath("$.dateEnd").value("2023-10-20"))
                .andExpect(jsonPath("$.status").value("Alegre"));
    }

    @Test()
    void registerCycleStarExceptionTest() throws Exception {

        when(cycleServiceMock.registerCycleStart(any(Cycle.class))).thenThrow(new CustomException("error"));

        mockMvc.perform(post("/api/v1/cycle/registerCycleStart")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("error"));
    }

    @Test()
    void deleteCycleTest() throws Exception {
        Map<String, String> res = new HashMap<>();
        res.put("Response", "OK");
        when(cycleServiceMock.deleteCycle(anyLong())).thenReturn(res);

        mockMvc.perform(delete("/api/v1/cycle/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Response").value("OK"));
    }

    @Test()
    void deleteCycleExceptionTest() throws Exception {

        when(cycleServiceMock.deleteCycle(anyLong())).thenThrow(new CustomException("error"));

        mockMvc.perform(delete("/api/v1/cycle/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("error"));
    }

    @Test()
    void updateCycleTest() throws Exception {
        when(cycleServiceMock.updateCycle(any(Cycle.class))).thenReturn(cycle);

        mockMvc.perform(put("/api/v1/cycle/updateCycle")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value("2023-10-29"))
                .andExpect(jsonPath("$.dateEnd").value("2023-10-20"))
                .andExpect(jsonPath("$.status").value("Alegre"));
    }
    @Test()
    void updateCycleExceptionTest() throws Exception {

        when(cycleServiceMock.updateCycle(any(Cycle.class))).thenThrow(new IllegalArgumentException("error"));

        mockMvc.perform(put("/api/v1/cycle/updateCycle")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("error"));
    }
}
