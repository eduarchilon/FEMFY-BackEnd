package com.femfy.femfyapi.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.exception.CustomException;
import com.femfy.femfyapi.repository.CycleRepository;

import dto.CycleDTO;

@SpringBootTest
public class CycleServiceTest {

    Cycle cycle;
    @Mock
    CycleRepository cycleRepositoryMock;

    @InjectMocks
    CycleService cycleService;


    @BeforeEach
    void setUp() throws CustomException {
        cycle = new Cycle();
        cycle.setStatus("Alegre");
        cycle.setIdUser(7L);
        cycle.setId(5L);
        cycle.setDateEnd("2023-10-06");
        cycle.setDateBeging("2023-10-06");
        cycle.setDaysOfBleeding(4);
    }
    @Test
    void registerCycleStartTest() throws Exception {
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);

        CycleDTO resObtenida = cycleService.registerCycleStart(cycle);

        assertNotNull(resObtenida);

    }

    @Test

    void registerCycleStartExceptionTest(){
        when(cycleRepositoryMock.save(any(Cycle.class))).thenThrow(new RuntimeException(new CustomException("")));

        assertThrows(CustomException.class, () -> {
            cycleService.registerCycleStart(cycle);
        });

    }

    @Test
    void registerCycleEndTest() throws Exception {
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);
        when(cycleRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cycle));

        CycleDTO resObtenida = cycleService.registerCycleEnd(cycle);

        assertNotNull(resObtenida);

    }

    @Test
    void registerCycleEndExceptionTest() throws Exception {
        when(cycleRepositoryMock.findByIdUserAndDateBeging(anyLong(), anyString())).thenThrow(new RuntimeException(new CustomException("")));

        assertThrows(CustomException.class, () -> {
            cycleService.registerCycleEnd(cycle);
        });

    }

    @Test
    void getCycleHistoryTest() throws Exception {
        List<Cycle> list = new ArrayList<>();
        list.add(cycle);
        when(cycleRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cycle));
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);
        when(cycleRepositoryMock.findAllByIdUser(anyLong())).thenReturn(list);
        when(cycleRepositoryMock.findByIdUserAndDateBeging(anyLong(), anyString())).thenReturn(cycle);


        CycleDTO resObtenida = cycleService.registerCycleEnd(cycle);

        assertNotNull(resObtenida);

    }

    @Test
    void getCycleHistoryExceptionTest() throws Exception {
        when(cycleRepositoryMock.findAllByIdUser(anyLong())).thenThrow(new RuntimeException(new CustomException("")));

        assertThrows(CustomException.class, () -> {
            cycleService.getCycleHistory(1L);
        });

    }

    @Test
    void getCycleByIdUserAndDateBegingTest() throws Exception {
        when(cycleRepositoryMock.findByIdUserAndDateBeging(anyLong(), anyString())).thenReturn(cycle);

        CycleDTO resObtenida = cycleService.getCycleByIdUserAndDateBeging(cycle.getIdUser(), "2023-10-06");

        assertNotNull(resObtenida);
    }

    @Test
    void getCycleByIdUserAndDateBegingExceptionTest() throws Exception {
        when(cycleRepositoryMock.findByIdUserAndDateBeging(anyLong(), anyString())).thenThrow(new RuntimeException(new CustomException("")));

        assertThrows(CustomException.class, () -> {
            cycleService.getCycleByIdUserAndDateBeging(cycle.getIdUser(), "2023-10-06");
        });

    }


}
