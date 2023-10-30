package com.femfy.femfyapi.service;

import dto.QuestionsUserFamilyHistoryDTO;
import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.QuestionsUserFamilyHistoryRepository;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserFamilyHistoryService implements IQuestionsUserFamilyHistoryService {

    private final QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository;

    @Autowired
    public QuestionsUserFamilyHistoryService(QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository) {
        this.questionsUserFamilyHistoryRepository = questionsUserFamilyHistoryRepository;
    }

    @Override
    public List<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistories() {
        List<QuestionsUserFamilyHistory> familyHistoryList = questionsUserFamilyHistoryRepository.findAll();
        return familyHistoryList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistory(Long id) {
        Optional<QuestionsUserFamilyHistory> familyHistory = questionsUserFamilyHistoryRepository.findById(id);
        return familyHistory.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserFamilyHistoryDTO saveQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO questionsUserFamilyHistoryDTO) {
        QuestionsUserFamilyHistory familyHistory = mapToEntity(questionsUserFamilyHistoryDTO);
        familyHistory = questionsUserFamilyHistoryRepository.save(familyHistory);
        return mapToDTO(familyHistory);
    }

    @Override
    public QuestionsUserFamilyHistoryDTO updateQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserFamilyHistory existingFamilyHistory = questionsUserFamilyHistoryRepository.findById(idToUpdate)
                .orElseThrow(() -> new RuntimeException("No se encontró el objeto para actualizar"));

        copyProperties(updatedDTO, existingFamilyHistory);

        existingFamilyHistory = questionsUserFamilyHistoryRepository.save(existingFamilyHistory);

        return mapToDTO(existingFamilyHistory);
    }
    
    
    @Override
    public List<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistoriesByUserId(Long id) {
        List<QuestionsUserFamilyHistory> familyHistoryListByUserId = questionsUserFamilyHistoryRepository.findByUserId(id) ;
        return familyHistoryListByUserId.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private void copyProperties(QuestionsUserFamilyHistoryDTO source, QuestionsUserFamilyHistory target) {
        if(source.getBreastCancer() != null){
            target.setBreastCancer(source.getBreastCancer());
        }
        if(source.getOvarianCancer() != null){
            target.setOvarianCancer(source.getOvarianCancer());
        }
        if(source.getEndometriosis() != null){
            target.setEndometriosis(source.getEndometriosis());
        }
        if(source.getUterineFibroids() != null){
            target.setUterineFibroids(source.getUterineFibroids());
        }
        if(source.getSop() != null){
            target.setSop(source.getSop());
        }
        if(source.getEarlyMenopause() != null){
            target.setEarlyMenopause(source.getEarlyMenopause());
        }
    }

    @Override
    public void deleteQuestionsUserFamilyHistory(Long id) {
        questionsUserFamilyHistoryRepository.deleteById(id);
    }

    private QuestionsUserFamilyHistoryDTO mapToDTO(QuestionsUserFamilyHistory familyHistory) {
        if (familyHistory == null) {
            throw new EntityNotFoundException("Family history not found");
        }

        QuestionsUserFamilyHistoryDTO dto = new QuestionsUserFamilyHistoryDTO();
        dto.setId(familyHistory.getId());
        dto.setUserId(familyHistory.getUser().getId());
        dto.setBreastCancer(familyHistory.getBreastCancer());
        dto.setOvarianCancer(familyHistory.getOvarianCancer());
        dto.setEndometriosis(familyHistory.getEndometriosis());
        dto.setUterineFibroids(familyHistory.getUterineFibroids());
        dto.setSop(familyHistory.getSop());
        dto.setEarlyMenopause(familyHistory.getEarlyMenopause());

        return dto;
    }

    private QuestionsUserFamilyHistory mapToEntity(QuestionsUserFamilyHistoryDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserFamilyHistory familyHistory = new QuestionsUserFamilyHistory();
        familyHistory.setId(dto.getId());
        familyHistory.setUser(user);
        familyHistory.setBreastCancer(dto.getBreastCancer());
        familyHistory.setOvarianCancer(dto.getOvarianCancer());
        familyHistory.setEndometriosis(dto.getEndometriosis());
        familyHistory.setUterineFibroids(dto.getUterineFibroids());
        familyHistory.setSop(dto.getSop());
        familyHistory.setEarlyMenopause(dto.getEarlyMenopause());
        return familyHistory;
    }
}