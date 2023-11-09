package com.femfy.femfyapi.service;

import dto.QuestionsUserMenopauseDTO;
import com.femfy.femfyapi.entity.QuestionsUserMenopause;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.QuestionsUserMenopauseRepository;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionsUserMenopauseServiceTest {

    @Mock
    private QuestionsUserMenopauseRepository repository;

    @InjectMocks
    private QuestionsUserMenopauseService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetQuestionsUserMenopauseWithData() {
        QuestionsUserMenopause menopause = createQuestionsUserMenopause(1L, 1L);
        List<QuestionsUserMenopause> menopauseList = Collections.singletonList(menopause);
        when(repository.findAll()).thenReturn(menopauseList);

        List<QuestionsUserMenopauseDTO> result = service.getQuestionsUserMenopause();

        assertNotNull(result);
        assertEquals(1, result.size());

        QuestionsUserMenopauseDTO dto = result.get(0);
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getUserId());
    }

    @Test
    void testGetQuestionsUserMenopauseWithNoData() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<QuestionsUserMenopauseDTO> result = service.getQuestionsUserMenopause();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetQuestionsUserMenopauseFound() {
        Long id = 1L;
        QuestionsUserMenopause menopause = createQuestionsUserMenopause(id, 1L);
        when(repository.findById(id)).thenReturn(Optional.of(menopause));

        Optional<QuestionsUserMenopauseDTO> result = service.getQuestionsUserMenopause(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testGetQuestionsUserMenopauseNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<QuestionsUserMenopauseDTO> result = service.getQuestionsUserMenopause(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveQuestionsUserMenopause() {
        User user = createUser(1L);

        QuestionsUserMenopauseDTO dtoToSave = createQuestionsUserMenopauseDTO(user);
        QuestionsUserMenopause savedEntity = createQuestionsUserMenopause(1L, user);

        when(repository.save(any(QuestionsUserMenopause.class))).thenReturn(savedEntity);

        QuestionsUserMenopauseDTO result = service.saveQuestionsUserMenopause(dtoToSave);

        assertNotNull(result.getId());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateQuestionsUserMenopause() {
        Long idToUpdate = 1L;
        User user = createUser(1L);

        QuestionsUserMenopause existingEntity = createQuestionsUserMenopause(idToUpdate, user);

        QuestionsUserMenopauseDTO updatedDTO = createQuestionsUserMenopauseDTO(user);
        updatedDTO.setId(idToUpdate);

        when(repository.findById(idToUpdate)).thenReturn(Optional.of(existingEntity));
        when(repository.save(any(QuestionsUserMenopause.class))).thenReturn(existingEntity);

        QuestionsUserMenopauseDTO result = service.updateQuestionsUserMenopause(updatedDTO);

        assertEquals(idToUpdate, result.getId());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateQuestionsUserMenopauseNotFound() {
        Long idToUpdate = 1L;
        QuestionsUserMenopauseDTO updatedDTO = new QuestionsUserMenopauseDTO();
        updatedDTO.setId(idToUpdate);

        when(repository.findById(idToUpdate)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateQuestionsUserMenopause(updatedDTO));
    }

    @Test
    void testDeleteQuestionsUserMenopause() {
        Long idToDelete = 1L;

        doNothing().when(repository).deleteById(idToDelete);

        assertDoesNotThrow(() -> service.deleteQuestionsUserMenopause(idToDelete));

        verify(repository, times(1)).deleteById(idToDelete);
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    private QuestionsUserMenopauseDTO createQuestionsUserMenopauseDTO(User user) {
        QuestionsUserMenopauseDTO dto = new QuestionsUserMenopauseDTO();
        dto.setUserId(user.getId());
        dto.setSuffocation(0);
        dto.setChangesInMenstrualCycle(0);
        dto.setVaginalDryness(0);
        dto.setChangesInSkinAndHair(0);
        dto.setMoodChanges(0);
        dto.setSleepingDifficulties(0);
        dto.setWeightGain(0);
        dto.setLossOfBoneDensity(0);
        dto.setChangesInLibido(0);
        return dto;
    }

    private QuestionsUserMenopause createQuestionsUserMenopause(Long id, User user) {
        QuestionsUserMenopause menopause = new QuestionsUserMenopause();
        menopause.setId(id);
        menopause.setUser(user);
        menopause.setSuffocation(0);
        menopause.setChangesInMenstrualCycle(0);
        menopause.setVaginalDryness(0);
        menopause.setChangesInSkinAndHair(0);
        menopause.setMoodChanges(0);
        menopause.setSleepingDifficulties(0);
        menopause.setWeightGain(0);
        menopause.setLossOfBoneDensity(0);
        menopause.setChangesInLibido(0);
        return menopause;
    }

    private QuestionsUserMenopause createQuestionsUserMenopause(Long id, Long userId) {
        User user = new User();
        user.setId(userId);

        QuestionsUserMenopause menopause = new QuestionsUserMenopause();
        menopause.setId(id);
        menopause.setUser(user);
        menopause.setSuffocation(0);
        menopause.setChangesInMenstrualCycle(0);
        menopause.setVaginalDryness(0);
        menopause.setChangesInSkinAndHair(0);
        menopause.setMoodChanges(0);
        menopause.setSleepingDifficulties(0);
        menopause.setWeightGain(0);
        menopause.setLossOfBoneDensity(0);
        menopause.setChangesInLibido(0);

        return menopause;
    }

    private void copyProperties(QuestionsUserMenopauseDTO source, QuestionsUserMenopause target) {
        if (source.getSuffocation() != null) {
            target.setSuffocation(source.getSuffocation());
        }
        if (source.getChangesInMenstrualCycle() != null) {
            target.setChangesInMenstrualCycle(source.getChangesInMenstrualCycle());
        }
        if (source.getVaginalDryness() != null) {
            target.setVaginalDryness(source.getVaginalDryness());
        }
        if (source.getChangesInSkinAndHair() != null) {
            target.setChangesInSkinAndHair(source.getChangesInSkinAndHair());
        }
        if (source.getMoodChanges() != null) {
            target.setMoodChanges(source.getMoodChanges());
        }
        if (source.getSleepingDifficulties() != null) {
            target.setSleepingDifficulties(source.getSleepingDifficulties());
        }
        if(source.getWeightGain() != null){
            target.setWeightGain(source.getWeightGain());
        }
        if (source.getLossOfBoneDensity() != null) {
            target.setLossOfBoneDensity(source.getLossOfBoneDensity());
        }
        if (source.getChangesInLibido() != null) {
            target.setChangesInLibido(source.getChangesInLibido());
        }
    }
}