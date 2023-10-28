package com.femfy.femfyapi.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.exception.CustomException;
import com.femfy.femfyapi.service.ICycleService;

import dto.CycleDTO;

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
        
        try {
            String fechaInicio = "2023-10-25";
            String fechaFin = "2023-10-09";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			dto.setDateBeging(formato.parse(fechaInicio));
			dto.setDateEnd(formato.parse(fechaFin));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cycleController)
                .build();
    }


    @Test
    void getCycleTest() throws Exception {
    	
    	Date fechaIni = null;
    	Date fechaTerm = null;
    	
        try {
            String fechaInicio = "2023-10-25";
            String fechaFin = "2023-10-09";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			fechaIni = formato.parse(fechaInicio);
			fechaTerm = formato.parse(fechaFin);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        when(cycleServiceMock.getCycleByIdUserAndDateBeging(anyLong(), eq("2023-10-05"))).thenReturn(dto);

        mockMvc.perform(get("/api/v1/cycle/getCycle/1")
                        .param("dateBeging", "2023-10-05")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value(fechaIni))
                .andExpect(jsonPath("$.dateEnd").value(fechaTerm))
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

    @Test()
    void registerCycleEndTest() throws Exception {
    	
    	Date fechaIni = null;
    	Date fechaTerm = null;
    	
        try {
            String fechaInicio = "2023-10-25";
            String fechaFin = "2023-10-09";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			fechaIni = formato.parse(fechaInicio);
			fechaTerm = formato.parse(fechaFin);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        when(cycleServiceMock.registerCycleEnd(any(Cycle.class))).thenReturn(dto);

        mockMvc.perform(put("/api/v1/cycle/registerCycleEnd")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value(fechaIni))
                .andExpect(jsonPath("$.dateEnd").value(fechaTerm))
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
    	
    	Date fechaIni = null;
    	Date fechaTerm = null;
    	
        try {
            String fechaInicio = "2023-10-25";
            String fechaFin = "2023-10-09";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			fechaIni = formato.parse(fechaInicio);
			fechaTerm = formato.parse(fechaFin);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        when(cycleServiceMock.registerCycleStart(any(Cycle.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/cycle/registerCycleStart")
                        .content( this.mapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.dateBeging").value(fechaIni))
                .andExpect(jsonPath("$.dateEnd").value(fechaTerm))
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
}
