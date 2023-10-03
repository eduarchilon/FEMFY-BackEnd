package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.FamilyHistory;

import java.util.List;
import java.util.Optional;

public interface IFamilyHistoryService {

    List<FamilyHistory> getFamilyHistories();

    Optional<FamilyHistory> getFamilyHistory(Long id);

    FamilyHistory saveOrUpdateFamilyHistory(FamilyHistory familyHistory);

    void deleteFamilyHistory(Long id);
}
