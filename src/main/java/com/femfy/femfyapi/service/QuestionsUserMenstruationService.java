package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserMenstruation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.femfy.femfyapi.repository.QuestionsUserMenstruationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsUserMenstruationService implements IQuestionsUserMenstruationService {

    @Autowired
    private QuestionsUserMenstruationRepository questionsUserMenstruationRepository;

    @Override
    public List<QuestionsUserMenstruation> getQuestionsUserMenstruations() {
        return questionsUserMenstruationRepository.findAll();
    }

    @Override
    public Optional<QuestionsUserMenstruation> getQuestionsUserMenstruation(Long id) {
        return questionsUserMenstruationRepository.findById(id);
    }

    @Override
    public QuestionsUserMenstruation saveOrUpdateQuestionsUserMenstruation(QuestionsUserMenstruation questionsUserMenstruation) {
        questionsUserMenstruationRepository.save(questionsUserMenstruation);
        return questionsUserMenstruation;
    }

    @Override
    public void deleteQuestionsUserMenstruation(Long id) {
        questionsUserMenstruationRepository.deleteById(id);
    }
}