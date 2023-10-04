package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuestionsUserFamilyHistoryRepositoryTest {

    @Autowired
    private FamilyHistoryRepository familyHistoryRepository;

    @BeforeEach
    public void setUp() {
        QuestionsUserFamilyHistory questionsUserFamilyHistory = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory.setBreastCancer(true);
        questionsUserFamilyHistory.setOvarianCancer(false);
        questionsUserFamilyHistory.setEndometriosis(true);
        questionsUserFamilyHistory.setUterineFibroids(false);
        questionsUserFamilyHistory.setSop(true);
        questionsUserFamilyHistory.setEarlyMenopause(false);

        familyHistoryRepository.save(questionsUserFamilyHistory);
    }

    @Test
    public void testFindById() {
        // Inserta
        QuestionsUserFamilyHistory history = new QuestionsUserFamilyHistory();
        history.setBreastCancer(true);
        familyHistoryRepository.save(history);

        // Busca
        Optional<QuestionsUserFamilyHistory> foundFamilyHistory = familyHistoryRepository.findById(history.getId());

        // Verifica
        assertThat(foundFamilyHistory).isPresent();
        assertThat(foundFamilyHistory.get().getId()).isEqualTo(history.getId());
    }

    @Test
    public void testFindAll() {
        // Inserta
        QuestionsUserFamilyHistory history1 = new QuestionsUserFamilyHistory();
        history1.setBreastCancer(true);
        familyHistoryRepository.save(history1);

        QuestionsUserFamilyHistory history2 = new QuestionsUserFamilyHistory();
        history2.setBreastCancer(false);
        familyHistoryRepository.save(history2);

        // Obtiene
        List<QuestionsUserFamilyHistory> allFamilyHistories = familyHistoryRepository.findAll();

        // Verifica
        assertThat(allFamilyHistories).hasSize(2);
    }

    @Test
    public void testSave() {
        // Crea
        QuestionsUserFamilyHistory history = new QuestionsUserFamilyHistory();
        history.setBreastCancer(true);
        QuestionsUserFamilyHistory savedQuestionsUserFamilyHistory = familyHistoryRepository.save(history);

        // Verifica
        assertThat(savedQuestionsUserFamilyHistory.getId()).isNotNull();
        assertThat(savedQuestionsUserFamilyHistory.isBreastCancer()).isEqualTo(true);
    }

    @Test
    public void testDeleteById() {
        // Inserta
        QuestionsUserFamilyHistory questionsUserFamilyHistory = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory.setBreastCancer(true);
        familyHistoryRepository.save(questionsUserFamilyHistory);

        // Elimina
        familyHistoryRepository.deleteById(questionsUserFamilyHistory.getId());

        // Verifica
        Optional<QuestionsUserFamilyHistory> deletedFamilyHistory = familyHistoryRepository.findById(questionsUserFamilyHistory.getId());
        assertThat(deletedFamilyHistory).isNotPresent();
    }
}
