package com.femfy.femfyapi.domain.service;

import com.femfy.femfyapi.domain.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserMenstruationService;
import com.femfy.femfyapi.domain.repository.QuestionsUserMenstruationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuestionsUserMenstruationService implements IQuestionsUserMenstruationService {

    private final QuestionsUserMenstruationRepository questionsUserMenstruationRepository;

    @Autowired
    public QuestionsUserMenstruationService(QuestionsUserMenstruationRepository questionsUserMenstruationRepository) {
        this.questionsUserMenstruationRepository = questionsUserMenstruationRepository;
    }

    @Override
    public List<QuestionsUserMenstruation> getQuestionsUserMenstruations() {
        return questionsUserMenstruationRepository.findAll();
    }

    @Override
    public Optional<QuestionsUserMenstruation> getQuestionsUserMenstruation(Long id) {
        return questionsUserMenstruationRepository.findById(id);
    }

    @Override
    public QuestionsUserMenstruation saveQuestionsUserMenstruation(QuestionsUserMenstruation questionsUserMenstruation) {
        return questionsUserMenstruationRepository.save(questionsUserMenstruation);
    }

    @Override
    public QuestionsUserMenstruation updateQuestionsUserMenstruation(QuestionsUserMenstruation updated) {
        Long idToUpdate = updated.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserMenstruation existingMenstruation = questionsUserMenstruationRepository.findById(idToUpdate)
                .orElseThrow(() -> new RuntimeException("No se encontró el objeto para actualizar"));

        copyProperties(updated, existingMenstruation);

        return questionsUserMenstruationRepository.save(existingMenstruation);
    }

    private void copyProperties(QuestionsUserMenstruation source, QuestionsUserMenstruation target) {
        if (source.getLastTime() != null) {
            target.setLastTime(source.getLastTime());
        }

        if (source.getLastCycleDuration() != null) {
            target.setLastCycleDuration(source.getLastCycleDuration());
        }

        if (source.getRegular() != null) {
            target.setRegular(source.getRegular());
        }

        if (source.getRegularCycleDuration() != null) {
            target.setRegularCycleDuration(source.getRegularCycleDuration());
        }

        if (source.getBleedingDuration() != null) {
            target.setBleedingDuration(source.getBleedingDuration());
        }
    }

    @Override
    public void deleteQuestionsUserMenstruation(Long id) {
        questionsUserMenstruationRepository.deleteById(id);
    }


}