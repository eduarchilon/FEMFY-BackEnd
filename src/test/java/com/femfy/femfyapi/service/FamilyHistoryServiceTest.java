package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.FamilyHistory;
import com.femfy.femfyapi.repository.FamilyHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FamilyHistoryServiceTest {

    @InjectMocks
    private FamilyHistoryService familyHistoryService;

    @Mock
    private FamilyHistoryRepository familyHistoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFamilyHistories() {
        FamilyHistory history1 = new FamilyHistory();
        FamilyHistory history2 = new FamilyHistory();
        List<FamilyHistory> expectedHistories = Arrays.asList(history1, history2);

        when(familyHistoryRepository.findAll()).thenReturn(expectedHistories);

        List<FamilyHistory> actualHistories = familyHistoryService.getFamilyHistories();

        verify(familyHistoryRepository, times(1)).findAll();
        assertEquals(expectedHistories, actualHistories);
    }

    @Test
    public void testGetFamilyHistory() {
        Long id = 1L;

        FamilyHistory expectedHistory = new FamilyHistory();
        expectedHistory.setId(id);

        when(familyHistoryRepository.findById(id)).thenReturn(Optional.of(expectedHistory));

        Optional<FamilyHistory> actualHistory = familyHistoryService.getFamilyHistory(id);

        verify(familyHistoryRepository, times(1)).findById(id);
        assertEquals(expectedHistory, actualHistory.orElse(null));
    }

    @Test
    public void testSaveOrUpdateFamilyHistory() {
        FamilyHistory familyHistory = new FamilyHistory();
        familyHistory.setCancer_mama(true);
        familyHistory.setCancer_ovario(false);
        familyHistory.setEndometriosis(true);

        when(familyHistoryRepository.save(familyHistory)).thenReturn(familyHistory);

        FamilyHistory savedFamilyHistory = familyHistoryService.saveOrUpdateFamilyHistory(familyHistory);

        verify(familyHistoryRepository, times(1)).save(familyHistory);
        assertEquals(familyHistory, savedFamilyHistory);
    }

    @Test
    public void testDeleteFamilyHistory() {
        Long id = 1L;

        familyHistoryService.deleteFamilyHistory(id);

        verify(familyHistoryRepository, times(1)).deleteById(id);
    }

}
