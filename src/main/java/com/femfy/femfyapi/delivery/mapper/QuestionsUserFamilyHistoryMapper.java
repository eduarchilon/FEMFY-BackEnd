package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.QuestionsUserFamilyHistoryDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class QuestionsUserFamilyHistoryMapper {

    public static QuestionsUserFamilyHistoryDTO mapToDTO(QuestionsUserFamilyHistory familyHistory) {
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

    public static QuestionsUserFamilyHistory mapToEntity(QuestionsUserFamilyHistoryDTO dto) {
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
