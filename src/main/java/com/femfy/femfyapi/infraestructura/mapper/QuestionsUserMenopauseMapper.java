package com.femfy.femfyapi.infraestructura.mapper;

import com.femfy.femfyapi.delivery.dto.QuestionsUserMenopauseDTO;
import com.femfy.femfyapi.domain.entity.QuestionsUserMenopause;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

public class QuestionsUserMenopauseMapper {

    public static QuestionsUserMenopauseDTO mapToDTO(QuestionsUserMenopause menopause) {
        if (menopause == null) {
            throw new EntityNotFoundException("Menopause not found");
        }

        QuestionsUserMenopauseDTO dto = new QuestionsUserMenopauseDTO();
        dto.setId(menopause.getId());
        dto.setUserId(menopause.getUser().getId());
        dto.setSuffocation(menopause.getSuffocation());
        dto.setChangesInMenstrualCycle(menopause.getChangesInMenstrualCycle());
        dto.setVaginalDryness(menopause.getVaginalDryness());
        dto.setChangesInSkinAndHair(menopause.getChangesInSkinAndHair());
        dto.setMoodChanges(menopause.getMoodChanges());
        dto.setSleepingDifficulties(menopause.getSleepingDifficulties());
        dto.setAumentoDePeso(menopause.getAumentoDePeso());
        dto.setLossOfBoneDensity(menopause.getLossOfBoneDensity());
        dto.setChangesInLibido(menopause.getChangesInLibido());

        return dto;
    }

    public static QuestionsUserMenopause mapToEntity(QuestionsUserMenopauseDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserMenopause menopause = new QuestionsUserMenopause();
        menopause.setId(dto.getId());
        menopause.setUser(user);
        menopause.setSuffocation(dto.isSuffocation());
        menopause.setChangesInMenstrualCycle(dto.isChangesInMenstrualCycle());
        menopause.setVaginalDryness(dto.isVaginalDryness());
        menopause.setChangesInSkinAndHair(dto.isChangesInSkinAndHair());
        menopause.setMoodChanges(dto.isMoodChanges());
        menopause.setSleepingDifficulties(dto.isSleepingDifficulties());
        menopause.setAumentoDePeso(dto.isAumentoDePeso());
        menopause.setLossOfBoneDensity(dto.isLossOfBoneDensity());
        menopause.setChangesInLibido(dto.isChangesInLibido());
        return menopause;
    }

}
