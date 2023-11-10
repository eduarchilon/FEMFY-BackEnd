package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.QuestionsUserMenopause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuestionsUserMenopauseRepositoryTest {

    @Autowired
    private QuestionsUserMenopauseRepository questionsUserMenopauseRepository;

    @BeforeEach
    public void setUp() {
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(1);
        questionsUserMenopause.setChangesInMenstrualCycle(0);
        questionsUserMenopause.setVaginalDryness(1);
        questionsUserMenopause.setChangesInSkinAndHair(0);
        questionsUserMenopause.setMoodChanges(1);
        questionsUserMenopause.setSleepingDifficulties(0);
        questionsUserMenopause.setWeightGain(1);
        questionsUserMenopause.setLossOfBoneDensity(0);
        questionsUserMenopause.setChangesInLibido(0);

        questionsUserMenopauseRepository.save(questionsUserMenopause);
    }

    @Test
    public void testFindById() {
        // Inserta
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(1);
        questionsUserMenopauseRepository.save(questionsUserMenopause);

        // Busca
        Optional<QuestionsUserMenopause> foundMenopause = questionsUserMenopauseRepository.findById(questionsUserMenopause.getId());

        // Verifica
        assertThat(foundMenopause).isPresent();
        assertThat(foundMenopause.get().getId()).isEqualTo(questionsUserMenopause.getId());
    }

    @Test
    public void testFindAll() {
        // Obtiene
        List<QuestionsUserMenopause> menopauses = questionsUserMenopauseRepository.findAll();

        // Inserta
        QuestionsUserMenopause menopause1 = new QuestionsUserMenopause();
        menopause1.setSuffocation(1);
        questionsUserMenopauseRepository.save(menopause1);

        QuestionsUserMenopause menopause2 = new QuestionsUserMenopause();
        menopause2.setSuffocation(0);
        questionsUserMenopauseRepository.save(menopause2);

        // Obtiene
        List<QuestionsUserMenopause> allMenopauses = questionsUserMenopauseRepository.findAll();

        // Verifica
        assertThat(allMenopauses).hasSize(3);
    }

    @Test
    public void testSave() {
        // Crea
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(1);
        QuestionsUserMenopause savedMenopause = questionsUserMenopauseRepository.save(questionsUserMenopause);

        // Verifica
        assertThat(savedMenopause.getId()).isNotNull();
        assertThat(savedMenopause.getSuffocation()).isEqualTo(1);
    }

    @Test
    public void testDeleteById() {
        // Inserta
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(1);
        questionsUserMenopauseRepository.save(questionsUserMenopause);

        // Elimina
        questionsUserMenopauseRepository.deleteById(questionsUserMenopause.getId());

        // Verifica
        Optional<QuestionsUserMenopause> deletedMenopause = questionsUserMenopauseRepository.findById(questionsUserMenopause.getId());
        assertThat(deletedMenopause).isNotPresent();
    }
}