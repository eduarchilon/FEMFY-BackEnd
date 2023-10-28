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
        dto.setBreastCancer(familyHistory.isBreastCancer());
        dto.setOvarianCancer(familyHistory.isOvarianCancer());
        dto.setEndometriosis(familyHistory.isEndometriosis());
        dto.setUterineFibroids(familyHistory.isUterineFibroids());
        dto.setSop(familyHistory.isSop());
        dto.setEarlyMenopause(familyHistory.isEarlyMenopause());

        return dto;
    }

    public static QuestionsUserFamilyHistory mapToEntity(QuestionsUserFamilyHistoryDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserFamilyHistory familyHistory = new QuestionsUserFamilyHistory();
        familyHistory.setId(dto.getId());
        familyHistory.setUser(user);
        familyHistory.setBreastCancer(dto.isBreastCancer());
        familyHistory.setOvarianCancer(dto.isOvarianCancer());
        familyHistory.setEndometriosis(dto.isEndometriosis());
        familyHistory.setUterineFibroids(dto.isUterineFibroids());
        familyHistory.setSop(dto.isSop());
        familyHistory.setEarlyMenopause(dto.isEarlyMenopause());
        return familyHistory;
    }
}
