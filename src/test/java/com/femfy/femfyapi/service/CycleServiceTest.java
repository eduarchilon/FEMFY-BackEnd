package com.femfy.femfyapi.service;

import com.femfy.femfyapi.domain.entity.Cycle;
import com.femfy.femfyapi.domain.exception.CustomException;
import com.femfy.femfyapi.domain.service.CycleService;
import com.femfy.femfyapi.domain.repository.CycleRepository;

import com.femfy.femfyapi.delivery.dto.CycleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CycleServiceTest {

    Cycle cycle;
    CycleDTO dto;
    @Mock
    CycleRepository cycleRepositoryMock;

    @InjectMocks
    CycleService cycleService;


    @BeforeEach
    void setUp() throws CustomException {
        cycle = new Cycle();
        cycle.setStatus("Cansada");
        cycle.setIdUser(1L);
        cycle.setId(1L);
        cycle.setDateEnd(Date.valueOf("2023-10-20"));
        cycle.setDateBeging(Date.valueOf("2023-10-29"));

        dto = new CycleDTO();
        dto.setStatus("Alegre");
        dto.setIdUser(7L);
        dto.setId(5L);
        dto.setDateEnd("2023-10-16");
        dto.setDateBeging("2023-10-16");
        dto.setDaysOfBleeding(4);
    }
    @Test
    void registerCycleStartTest() throws Exception {
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);

        Cycle resObtenida = cycleService.registerCycleStart(cycle);

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
    void registerCycleEndIDNullTest() throws Exception {
        Cycle cycleIdNull = new Cycle();

        assertThrows(IllegalArgumentException.class, () -> {
            cycleService.registerCycleEnd(cycleIdNull);
        });


    }

    @Test
    void registerCycleEndTest() throws Exception {
        when(cycleRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cycle));
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);

        Cycle resObtenida = cycleService.registerCycleEnd(cycle);

        assertNotNull(resObtenida);

    }

    @Test
    void registerCycleEndExceptionTest() throws Exception {
        Cycle cycleIdInvalido = new Cycle();
        cycleIdInvalido.setId(3L);
        assertThrows(CustomException.class, () -> {
            cycleService.registerCycleEnd(cycleIdInvalido);
        });

    }

    @Test
    void rgetCycleHistoryIDNullTest() throws Exception {
        Long idUser = null;

        assertThrows(IllegalArgumentException.class, () -> {
            cycleService.getCycleHistory(idUser);
        });
    }

    @Test
    void getCycleHistoryTest() throws Exception {
        List<Cycle> list = new ArrayList<>();
        list.add(cycle);
        when(cycleRepositoryMock.findAllByIdUser(anyLong())).thenReturn(list);
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);
        when(cycleRepositoryMock.findByIdUserAndDateBeging(anyLong(), any(Date.class))).thenReturn(cycle);


        List<Cycle> resObtenida = cycleService.getCycleHistory(1L);

        assertNotNull(resObtenida);
    }

    @Test
    void getCycleByIdUserAndDateBegingIDNullTest() throws Exception {
        Long idUser = null;
        String date = null;

        assertThrows(IllegalArgumentException.class, () -> {
            cycleService.getCycleByIdUserAndDateBeging(idUser, date);
        });
    }



    @Test
    void getCycleHistoryExceptionTest() throws Exception {
        when(cycleRepositoryMock.findAllByIdUser(anyLong())).thenThrow(new RuntimeException(new CustomException("")));

        assertThrows(CustomException.class, () -> {
            cycleService.getCycleHistory(1L);
        });

    }

    @Test
    void getCycleByIdUserAndDateBegingIDUserNullTest() throws Exception {
        Long idUser = null;
        String date = "2023-09-10";

        assertThrows(IllegalArgumentException.class, () -> {
            cycleService.getCycleByIdUserAndDateBeging(idUser, date);
        });
    }

    @Test
    void getCycleByIdUserAndDateBegingDateNullTest() throws Exception {
        Long idUser = 1L;
        String date = null;

        assertThrows(IllegalArgumentException.class, () -> {
            cycleService.getCycleByIdUserAndDateBeging(idUser, date);
        });
    }

    @Test
    void getCycleByIdUserAndDateBegingTest() throws Exception {
        when(cycleRepositoryMock.findByIdUserAndDateBeging(anyLong(), any(Date.class))).thenReturn(cycle);

        Cycle resObtenida = cycleService.getCycleByIdUserAndDateBeging(cycle.getIdUser(), "2023-10-06");

        assertNotNull(resObtenida);
        assertEquals(Long.valueOf(1L), resObtenida.getId());
    }

    @Test
    void getCycleByIdUserAndDateBegingExceptionTest() throws Exception {
        when(cycleRepositoryMock.findByIdUserAndDateBeging(anyLong(), any(Date.class))).thenThrow(new RuntimeException(new CustomException("")));

        assertThrows(CustomException.class, () -> {
            cycleService.getCycleByIdUserAndDateBeging(cycle.getIdUser(), "2023-10-06");
        });

    }

    @Test
    void deleteCycleIdNullTest(){
        Long id = null;

        assertThrows(IllegalArgumentException.class, () -> {
            cycleService.deleteCycle(id);
        });
    }

    @Test
    void deleteCycleTest() throws CustomException {
        Cycle cycle = new Cycle();
        cycle.setId(1L);

        Map<String, String> resObtenida = cycleService.deleteCycle(1L);

        assertNotNull(resObtenida);
        assertEquals("OK", resObtenida.get("Response"));
    }

    @Test
    void deleteCycleExceptionTest(){
        doThrow(new RuntimeException("")).when(cycleRepositoryMock).deleteById(anyLong());

        assertThrows(CustomException.class, () -> {
            cycleService.deleteCycle(8L);
        });
    }

    @Test
    void updateCycleTest(){
        when(cycleRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cycle));
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);

        Cycle resObtenida = cycleService.updateCycle(cycle);

        assertNotNull(resObtenida);
        assertEquals("Cansada", resObtenida.getStatus());
    }


    @Test
    void updateCycleMapToDtoIdUserNullTest(){
        Cycle cycle2 = new Cycle();

        when(cycleRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cycle));
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle2);

        Cycle resObtenida = cycleService.updateCycle(cycle);

        assertNotNull(resObtenida);
    }

    @Test
    void updateCycleCopuPropertiesNullTest(){
        when(cycleRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cycle));
        when(cycleRepositoryMock.save(any(Cycle.class))).thenReturn(cycle);

        Cycle resObtenida = cycleService.updateCycle(cycle);

        assertNotNull(resObtenida);
    }


}
