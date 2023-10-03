package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.FamilyHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FamilyHistoryRepositoryTest {

    @Autowired
    private FamilyHistoryRepository familyHistoryRepository;

    @BeforeEach
    public void setUp() {
        FamilyHistory familyHistory = new FamilyHistory();
        familyHistory.setCancer_mama(true);
        familyHistory.setCancer_ovario(false);
        familyHistory.setEndometriosis(true);
        familyHistory.setMiomas_uterinos(false);
        familyHistory.setSop(true);
        familyHistory.setMenopausia_temprana(false);

        familyHistoryRepository.save(familyHistory);
    }

    @Test
    public void testFindById() {
        // Inserta
        FamilyHistory history = new FamilyHistory();
        history.setCancer_mama(true);
        familyHistoryRepository.save(history);

        // Busca
        Optional<FamilyHistory> foundFamilyHistory = familyHistoryRepository.findById(history.getId());

        // Verifica
        assertThat(foundFamilyHistory).isPresent();
        assertThat(foundFamilyHistory.get().getId()).isEqualTo(history.getId());
    }

    @Test
    public void testFindAll() {
        // Inserta
        FamilyHistory history1 = new FamilyHistory();
        history1.setCancer_mama(true);
        familyHistoryRepository.save(history1);

        FamilyHistory history2 = new FamilyHistory();
        history2.setCancer_mama(false);
        familyHistoryRepository.save(history2);

        // Obtiene
        List<FamilyHistory> allFamilyHistories = familyHistoryRepository.findAll();

        // Verifica
        assertThat(allFamilyHistories).hasSize(2);
    }

    @Test
    public void testSave() {
        // Crea
        FamilyHistory history = new FamilyHistory();
        history.setCancer_mama(true);
        FamilyHistory savedFamilyHistory = familyHistoryRepository.save(history);

        // Verifica
        assertThat(savedFamilyHistory.getId()).isNotNull();
        assertThat(savedFamilyHistory.isCancer_mama()).isEqualTo(true);
    }

    @Test
    public void testDeleteById() {
        // Inserta
        FamilyHistory familyHistory = new FamilyHistory();
        familyHistory.setCancer_mama(true);
        familyHistoryRepository.save(familyHistory);

        // Elimina
        familyHistoryRepository.deleteById(familyHistory.getId());

        // Verifica
        Optional<FamilyHistory> deletedFamilyHistory = familyHistoryRepository.findById(familyHistory.getId());
        assertThat(deletedFamilyHistory).isNotPresent();
    }
}
