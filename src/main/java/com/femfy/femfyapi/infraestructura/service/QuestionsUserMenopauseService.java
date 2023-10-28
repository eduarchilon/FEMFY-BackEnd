package com.femfy.femfyapi.infraestructura.service;

import com.femfy.femfyapi.domain.entity.QuestionsUserMenopause;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserMenopauseService;
import com.femfy.femfyapi.domain.repository.QuestionsUserMenopauseRepository;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserMenopauseService implements IQuestionsUserMenopauseService {

    private final QuestionsUserMenopauseRepository questionsUserMenopauseRepository;

    @Autowired
    public QuestionsUserMenopauseService(QuestionsUserMenopauseRepository questionsUserMenopauseRepository) {
        this.questionsUserMenopauseRepository = questionsUserMenopauseRepository;
    }

    @Override
    public List<QuestionsUserMenopause> getQuestionsUserMenopause() {
        return questionsUserMenopauseRepository.findAll();
    }

    @Override
    public Optional<QuestionsUserMenopause> getQuestionsUserMenopause(Long id) {
        return questionsUserMenopauseRepository.findById(id);
    }

    @Override
    public QuestionsUserMenopause saveQuestionsUserMenopause(QuestionsUserMenopause questionsUserMenopause) {
        return questionsUserMenopauseRepository.save(questionsUserMenopause);
    }

    @Override
    public QuestionsUserMenopause updateQuestionsUserMenopause(QuestionsUserMenopause updated) {
        Long idToUpdate = updated.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserMenopause existingMenopause = questionsUserMenopauseRepository.findById(idToUpdate)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el objeto para actualizar"));

        copyProperties(updated, existingMenopause);

        return questionsUserMenopauseRepository.save(existingMenopause);
    }

    @Override
    public void deleteQuestionsUserMenopause(Long id) {
        questionsUserMenopauseRepository.deleteById(id);
    }

    private void copyProperties(QuestionsUserMenopause source, QuestionsUserMenopause target) {
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
        if(source.getAumentoDePeso() != null){
            target.setAumentoDePeso(source.getAumentoDePeso());
        }
        if (source.getLossOfBoneDensity() != null) {
            target.setLossOfBoneDensity(source.getLossOfBoneDensity());
        }
        if (source.getChangesInLibido() != null) {
            target.setChangesInLibido(source.getChangesInLibido());
        }

    }




}
