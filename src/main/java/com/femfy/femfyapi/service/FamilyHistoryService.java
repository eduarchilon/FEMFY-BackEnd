package com.femfy.femfyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.FamilyHistory;
import com.femfy.femfyapi.repository.FamilyHistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyHistoryService implements IFamilyHistoryService {

    @Autowired
    private FamilyHistoryRepository familyHistoryRepository;

    @Override
    public List<FamilyHistory> getFamilyHistories() {
        return familyHistoryRepository.findAll();
    }

    @Override
    public Optional<FamilyHistory> getFamilyHistory(Long id) {
        return familyHistoryRepository.findById(id);
    }

    @Override
    public FamilyHistory saveOrUpdateFamilyHistory(FamilyHistory familyHistory) {
        familyHistoryRepository.save(familyHistory);
        return familyHistory;
    }

    @Override
    public void deleteFamilyHistory(Long id) {
        familyHistoryRepository.deleteById(id);
    }
}