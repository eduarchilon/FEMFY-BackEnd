package com.femfy.femfyapi.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.femfy.femfyapi.entity.QuestionsUserAnother;
import com.femfy.femfyapi.repository.QuestionsUserAnotherRepository;
import com.femfy.femfyapi.entity.QuestionsUserAnotherCongenitalCauses;
import com.femfy.femfyapi.entity.QuestionsUserAnotherHormonalCauses;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.entity.User;
import dto.QuestionsUserAnotherDTO;
import dto.QuestionsUserAnotherCongenitalCausesDTO;
import dto.QuestionsUserAnotherHormonalCausesDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class QuestionsUserAnotherService implements IQuestionsUserAnotherService {

    private final QuestionsUserAnotherRepository questionsUserAnotherRepository;

    @Autowired
    public QuestionsUserAnotherService(QuestionsUserAnotherRepository questionsUserAnotherRepository) {
        this.questionsUserAnotherRepository = questionsUserAnotherRepository;
    }

    @Override
    public List<QuestionsUserAnotherDTO> getQuestionsUserAnother() {
        List<QuestionsUserAnother> questionsUserAnotherList = questionsUserAnotherRepository.findAll();
        return questionsUserAnotherList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserAnotherDTO> getQuestionsUserAnother(Long id) {
        Optional<QuestionsUserAnother> questionsUserAnother = questionsUserAnotherRepository.findById(id);
        return questionsUserAnother.map(this::mapToDTO);
    }

    @Override
    public List<QuestionsUserAnotherDTO> getQuestionsUserAnotherByUser(Long userId) {
        List<QuestionsUserAnother> questionsUserAnotherList = questionsUserAnotherRepository.findByUserId(userId);
        return questionsUserAnotherList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionsUserAnotherDTO saveQuestionsUserAnother(QuestionsUserAnotherDTO questionsUserAnotherDTO) {
        QuestionsUserAnother questionsUserAnother = mapToEntity(questionsUserAnotherDTO);
        questionsUserAnother = questionsUserAnotherRepository.save(questionsUserAnother);
        return mapToDTO(questionsUserAnother);
    }

    @Override
    public QuestionsUserAnotherDTO updateQuestionsUserAnother(QuestionsUserAnotherDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserAnother existingQuestionsUserAnother = findQuestionsUserAnotherById(idToUpdate);

        updateQuestionsUserAnotherFields(existingQuestionsUserAnother, updatedDTO);

        existingQuestionsUserAnother = questionsUserAnotherRepository.save(existingQuestionsUserAnother);

        return mapToDTO(existingQuestionsUserAnother);
    }

    @Override
    public void deleteQuestionsUserAnother(Long id) {
        questionsUserAnotherRepository.deleteById(id);
    }

    private QuestionsUserAnotherDTO mapToDTO(QuestionsUserAnother questionsUserAnother) {
        if (questionsUserAnother == null) {
            throw new EntityNotFoundException("Entrada de QuestionsUserAnother no encontrada");
        }

        QuestionsUserAnotherDTO dto = new QuestionsUserAnotherDTO();
        dto.setId(questionsUserAnother.getId());

        if (questionsUserAnother.getUser() != null) {
            dto.setUserId(questionsUserAnother.getUser().getId());
        }

        if(questionsUserAnother.getCongenitalCauses() != null){
            QuestionsUserAnotherCongenitalCausesDTO congenitalCausesDTO = new QuestionsUserAnotherCongenitalCausesDTO();
            congenitalCausesDTO.setId(questionsUserAnother.getCongenitalCauses().getId());
            dto.setCongenitalCauses(congenitalCausesDTO);
        }

        if(questionsUserAnother.getHormonalCauses() != null){
            QuestionsUserAnotherHormonalCausesDTO hormonalCausesDTO = new QuestionsUserAnotherHormonalCausesDTO();
            hormonalCausesDTO.setId(questionsUserAnother.getHormonalCauses().getId());
            dto.setHormonalCauses(hormonalCausesDTO);
        }

        dto.setAnother(questionsUserAnother.isAnother());
        dto.setAnotherDescription(questionsUserAnother.getAnotherDescription());

        return dto;
    }

    private QuestionsUserAnother mapToEntity(QuestionsUserAnotherDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserAnother questionsUserAnother = new QuestionsUserAnother();
        questionsUserAnother.setId(dto.getId());
        questionsUserAnother.setUser(user);

        QuestionsUserAnotherCongenitalCauses congenitalCauses = new QuestionsUserAnotherCongenitalCauses();
        congenitalCauses.setId(dto.getCongenitalCauses().getId());
        questionsUserAnother.setCongenitalCauses(congenitalCauses);

        QuestionsUserAnotherHormonalCauses hormonalCauses = new QuestionsUserAnotherHormonalCauses();
        hormonalCauses.setId(dto.getHormonalCauses().getId());
        questionsUserAnother.setHormonalCauses(hormonalCauses);

        questionsUserAnother.setAnother(dto.isAnother());
        questionsUserAnother.setAnotherDescription(dto.getAnotherDescription());

        return questionsUserAnother;
    }

    private QuestionsUserAnother findQuestionsUserAnotherById(Long id) {
        return questionsUserAnotherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una entrada de QuestionsUserAnother con el ID: " + id));
    }

    private void updateQuestionsUserAnotherFields(QuestionsUserAnother existingQuestionsUserAnother, QuestionsUserAnotherDTO updatedDTO) {
        if (updatedDTO.getUserId() != null) {
            User user = new User();
            user.setId(updatedDTO.getUserId());
            existingQuestionsUserAnother.setUser(user);
        }

        if(updatedDTO.getHormonalCauses() != null){
            QuestionsUserAnotherHormonalCauses questionsUserAnotherHormonalCauses = new QuestionsUserAnotherHormonalCauses();
            questionsUserAnotherHormonalCauses.setId(updatedDTO.getId());
            existingQuestionsUserAnother.setHormonalCauses(questionsUserAnotherHormonalCauses);
        }

        if(updatedDTO.getCongenitalCauses() != null){
            QuestionsUserAnotherCongenitalCauses questionsUserAnotherCongenitalCauses = new QuestionsUserAnotherCongenitalCauses();
            questionsUserAnotherCongenitalCauses.setId(updatedDTO.getId());
            existingQuestionsUserAnother.setCongenitalCauses(questionsUserAnotherCongenitalCauses);
        }

        if (updatedDTO.isAnother()) {
            existingQuestionsUserAnother.setAnother(updatedDTO.isAnother());
        }

        if (updatedDTO.getAnotherDescription() != null) {
            existingQuestionsUserAnother.setAnotherDescription(updatedDTO.getAnotherDescription());
        }
    }
}