package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
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

public class QuestionsUserFamilyHistoryServiceTest {

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
        QuestionsUserFamilyHistory history1 = new QuestionsUserFamilyHistory();
        QuestionsUserFamilyHistory history2 = new QuestionsUserFamilyHistory();
        List<QuestionsUserFamilyHistory> expectedHistories = Arrays.asList(history1, history2);

        when(familyHistoryRepository.findAll()).thenReturn(expectedHistories);

        List<QuestionsUserFamilyHistory> actualHistories = familyHistoryService.getFamilyHistories();

        verify(familyHistoryRepository, times(1)).findAll();
        assertEquals(expectedHistories, actualHistories);
    }

    @Test
    public void testGetFamilyHistory() {
        Long id = 1L;

        QuestionsUserFamilyHistory expectedHistory = new QuestionsUserFamilyHistory();
        expectedHistory.setId(id);

        when(familyHistoryRepository.findById(id)).thenReturn(Optional.of(expectedHistory));

        Optional<QuestionsUserFamilyHistory> actualHistory = familyHistoryService.getFamilyHistory(id);

        verify(familyHistoryRepository, times(1)).findById(id);
        assertEquals(expectedHistory, actualHistory.orElse(null));
    }

    @Test
    public void testSaveOrUpdateFamilyHistory() {
        QuestionsUserFamilyHistory questionsUserFamilyHistory = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory.setBreastCancer(true);
        questionsUserFamilyHistory.setOvarianCancer(false);
        questionsUserFamilyHistory.setEndometriosis(true);

        when(familyHistoryRepository.save(questionsUserFamilyHistory)).thenReturn(questionsUserFamilyHistory);

        QuestionsUserFamilyHistory savedQuestionsUserFamilyHistory = familyHistoryService.saveOrUpdateFamilyHistory(questionsUserFamilyHistory);

        verify(familyHistoryRepository, times(1)).save(questionsUserFamilyHistory);
        assertEquals(questionsUserFamilyHistory, savedQuestionsUserFamilyHistory);
    }

    @Test
    public void testDeleteFamilyHistory() {
        Long id = 1L;

        familyHistoryService.deleteFamilyHistory(id);

        verify(familyHistoryRepository, times(1)).deleteById(id);
    }

}
