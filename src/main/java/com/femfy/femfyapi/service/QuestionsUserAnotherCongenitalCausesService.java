package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserAnotherCongenitalCauses;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.QuestionsUserAnotherCongenitalCausesRepository;
import dto.QuestionsUserAnotherCongenitalCausesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserAnotherCongenitalCausesService implements IQuestionsUserAnotherCongenitalCausesService {

    private final QuestionsUserAnotherCongenitalCausesRepository congenitalCausesRepository;

    @Autowired
    public QuestionsUserAnotherCongenitalCausesService(QuestionsUserAnotherCongenitalCausesRepository congenitalCausesRepository) {
        this.congenitalCausesRepository = congenitalCausesRepository;
    }

    @Override
    public List<QuestionsUserAnotherCongenitalCausesDTO> getAllQuestionsUserAnotherCongenitalCauses() {
        List<QuestionsUserAnotherCongenitalCauses> congenitalCausesList = congenitalCausesRepository.findAll();
        return congenitalCausesList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserAnotherCongenitalCausesDTO> getQuestionsUserAnotherCongenitalCausesById(Long id) {
        Optional<QuestionsUserAnotherCongenitalCauses> congenitalCauses = congenitalCausesRepository.findById(id);
        return congenitalCauses.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserAnotherCongenitalCausesDTO saveQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCausesDTO congenitalCausesDTO) {
        QuestionsUserAnotherCongenitalCauses congenitalCauses = mapToEntity(congenitalCausesDTO);
        congenitalCauses = congenitalCausesRepository.save(congenitalCauses);
        return mapToDTO(congenitalCauses);
    }

    @Override
    public QuestionsUserAnotherCongenitalCausesDTO updateQuestionsUserAnotherCongenitalCauses(QuestionsUserAnotherCongenitalCausesDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserAnotherCongenitalCauses existingCongenitalCauses = findCongenitalCausesById(idToUpdate);

        updateCongenitalCausesFields(existingCongenitalCauses, updatedDTO);

        existingCongenitalCauses = congenitalCausesRepository.save(existingCongenitalCauses);

        return mapToDTO(existingCongenitalCauses);
    }

    @Override
    public void deleteQuestionsUserAnotherCongenitalCauses(Long id) {
        congenitalCausesRepository.deleteById(id);
    }

    private QuestionsUserAnotherCongenitalCausesDTO mapToDTO(QuestionsUserAnotherCongenitalCauses congenitalCauses) {
        if (congenitalCauses == null) {
            throw new EntityNotFoundException("Causas congénitas de la usuaria no encontradas");
        }

        QuestionsUserAnotherCongenitalCausesDTO dto = new QuestionsUserAnotherCongenitalCausesDTO();
        dto.setId(congenitalCauses.getId());

        if (congenitalCauses.getMalformationsUterine() != null) {
            dto.setMalformationsUterine(congenitalCauses.getMalformationsUterine());
        }

        if (congenitalCauses.getTurnerSyndrome() != null) {
            dto.setTurnerSyndrome(congenitalCauses.getTurnerSyndrome());
        }

        if (congenitalCauses.getAnother() != null) {
            dto.setAnother(congenitalCauses.getAnother());
        }

        if (congenitalCauses.getAnotherDescription() != null) {
            dto.setAnotherDescription(congenitalCauses.getAnotherDescription());
        }

        if(congenitalCauses.getUser()!=null && congenitalCauses.getUser().getId() !=null) {
        	dto.setUserId(congenitalCauses.getUser().getId());
        }
        return dto;
    }

    private QuestionsUserAnotherCongenitalCauses mapToEntity(QuestionsUserAnotherCongenitalCausesDTO dto) {
        QuestionsUserAnotherCongenitalCauses congenitalCauses = new QuestionsUserAnotherCongenitalCauses();
        
    	if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            congenitalCauses.setUser(user);
        }
        congenitalCauses.setId(dto.getId());

        congenitalCauses.setMalformationsUterine(dto.getMalformationsUterine());
        congenitalCauses.setTurnerSyndrome(dto.getTurnerSyndrome());
        congenitalCauses.setAnother(dto.getAnother());
        congenitalCauses.setAnotherDescription(dto.getAnotherDescription());

        return congenitalCauses;
    }

    private QuestionsUserAnotherCongenitalCauses findCongenitalCausesById(Long id) {
        return congenitalCausesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Causas congénitas de la usuaria no encontradas con el ID: " + id));
    }

    private void updateCongenitalCausesFields(QuestionsUserAnotherCongenitalCauses existingCongenitalCauses, QuestionsUserAnotherCongenitalCausesDTO updatedDTO) {
        if (updatedDTO.getMalformationsUterine() != null) {
            existingCongenitalCauses.setMalformationsUterine(updatedDTO.getMalformationsUterine());
        }

        if (updatedDTO.getTurnerSyndrome() != null) {
            existingCongenitalCauses.setTurnerSyndrome(updatedDTO.getTurnerSyndrome());
        }

        if (updatedDTO.getAnother() != null) {
            existingCongenitalCauses.setAnother(updatedDTO.getAnother());
        }

        if (updatedDTO.getAnotherDescription() != null) {
            existingCongenitalCauses.setAnotherDescription(updatedDTO.getAnotherDescription());
        }
    }

	@Override
	public List<QuestionsUserAnotherCongenitalCausesDTO> getAQuestionsUserAnotherCongenitalCausesByUserId(Long id) {
        List<QuestionsUserAnotherCongenitalCauses> congenitalCausesList = congenitalCausesRepository.findByUserId(id);
        return congenitalCausesList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
	}
}