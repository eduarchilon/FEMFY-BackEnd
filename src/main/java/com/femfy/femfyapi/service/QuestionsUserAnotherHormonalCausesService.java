package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserAnotherHormonalCauses;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.QuestionsUserAnotherHormonalCausesRepository;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import dto.QuestionsUserAnotherHormonalCausesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserAnotherHormonalCausesService implements IQuestionsUserAnotherHormonalCausesService {

    private final QuestionsUserAnotherHormonalCausesRepository questionsUserAnotherHormonalCausesRepository;

    @Autowired
    public QuestionsUserAnotherHormonalCausesService(QuestionsUserAnotherHormonalCausesRepository questionsUserAnotherHormonalCausesRepository) {
        this.questionsUserAnotherHormonalCausesRepository = questionsUserAnotherHormonalCausesRepository;
    }

    @Override
    public List<QuestionsUserAnotherHormonalCausesDTO> getAllQuestionsUserAnotherHormonalCauses() {
        List<QuestionsUserAnotherHormonalCauses> entities = questionsUserAnotherHormonalCausesRepository.findAll();
        return entities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserAnotherHormonalCausesDTO> getQuestionsUserAnotherHormonalCausesById(Long id) {
        Optional<QuestionsUserAnotherHormonalCauses> entity = questionsUserAnotherHormonalCausesRepository.findById(id);
        return entity.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserAnotherHormonalCausesDTO saveQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO dto) {
        QuestionsUserAnotherHormonalCauses entity = mapToEntity(dto);
        entity = questionsUserAnotherHormonalCausesRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public QuestionsUserAnotherHormonalCausesDTO updateQuestionsUserAnotherHormonalCauses(QuestionsUserAnotherHormonalCausesDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserAnotherHormonalCauses existingEntity = findQuestionsUserAnotherHormonalCausesById(idToUpdate);

        updateQuestionsUserAnotherHormonalCausesFields(existingEntity, updatedDTO);

        existingEntity = questionsUserAnotherHormonalCausesRepository.save(existingEntity);

        return mapToDTO(existingEntity);
    }

    @Override
    public void deleteQuestionsUserAnotherHormonalCauses(Long id) {
        questionsUserAnotherHormonalCausesRepository.deleteById(id);
    }

    private QuestionsUserAnotherHormonalCausesDTO mapToDTO(QuestionsUserAnotherHormonalCauses entity) {
        if (entity == null) {
            throw new EntityNotFoundException("Entidad no encontrada");
        }

        QuestionsUserAnotherHormonalCausesDTO dto = new QuestionsUserAnotherHormonalCausesDTO();
        dto.setId(entity.getId());

        dto.setPolycysticOvarySyndrome(entity.getPolycysticOvarySyndrome());
        dto.setHypothyroidism(entity.getHypothyroidism());
        dto.setHyperprolactinemia(entity.getHyperprolactinemia());
        dto.setSheehanSyndrome(entity.getSheehanSyndrome());
        dto.setPrematureOvarianFailure(entity.getPrematureOvarianFailure());
        dto.setHypothalamicDisorders(entity.getHypothalamicDisorders());
        dto.setInsulinResistance(entity.getInsulinResistance());
        dto.setAnother(entity.getAnother());
        dto.setAnotherDescription(entity.getAnotherDescription());
        dto.setUserId(entity.getUser().getId());

        return dto;
    }

	private QuestionsUserAnotherHormonalCauses mapToEntity(QuestionsUserAnotherHormonalCausesDTO dto) {
		QuestionsUserAnotherHormonalCauses entity = new QuestionsUserAnotherHormonalCauses();

		if (dto.getUserId() != null) {
			User user = new User();
			user.setId(dto.getUserId());
			entity.setUser(user);
		}

		entity.setId(dto.getId());

		if (dto.getPolycysticOvarySyndrome() != null) {
			entity.setPolycysticOvarySyndrome(dto.getPolycysticOvarySyndrome());
		}

		if (dto.getHypothyroidism() != null) {
			entity.setHypothyroidism(dto.getHypothyroidism());
		}

		if (dto.getHyperprolactinemia() != null) {
			entity.setHyperprolactinemia(dto.getHyperprolactinemia());
		}

		if (dto.getSheehanSyndrome() != null) {
			entity.setSheehanSyndrome(dto.getSheehanSyndrome());
		}

		if (dto.getPrematureOvarianFailure() != null) {
			entity.setPrematureOvarianFailure(dto.getPrematureOvarianFailure());
		}

		if (dto.getHypothalamicDisorders() != null) {
			entity.setHypothalamicDisorders(dto.getHypothalamicDisorders());
		}

		if (dto.getInsulinResistance() != null) {
			entity.setInsulinResistance(dto.getInsulinResistance());
		}

		if (dto.getAnother() != null) {
			entity.setAnother(dto.getAnother());
		}

		if (dto.getAnotherDescription() != null) {
			entity.setAnotherDescription(dto.getAnotherDescription());
		}

		return entity;
	}

    private QuestionsUserAnotherHormonalCauses findQuestionsUserAnotherHormonalCausesById(Long id) {
        return questionsUserAnotherHormonalCausesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
    }

    private void updateQuestionsUserAnotherHormonalCausesFields(QuestionsUserAnotherHormonalCauses existingEntity, QuestionsUserAnotherHormonalCausesDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo para la actualización.");
        }

        if (dto.getPolycysticOvarySyndrome() != null) {
            existingEntity.setPolycysticOvarySyndrome(dto.getPolycysticOvarySyndrome());
        }

        if (dto.getHypothyroidism() != null) {
            existingEntity.setHypothyroidism(dto.getHypothyroidism());
        }

        if (dto.getHyperprolactinemia() != null) {
            existingEntity.setHyperprolactinemia(dto.getHyperprolactinemia());
        }

        if (dto.getSheehanSyndrome() != null) {
            existingEntity.setSheehanSyndrome(dto.getSheehanSyndrome());
        }

        if (dto.getPrematureOvarianFailure() != null) {
            existingEntity.setPrematureOvarianFailure(dto.getPrematureOvarianFailure());
        }

        if (dto.getHypothalamicDisorders() != null) {
            existingEntity.setHypothalamicDisorders(dto.getHypothalamicDisorders());
        }

        if (dto.getInsulinResistance() != null) {
            existingEntity.setInsulinResistance(dto.getInsulinResistance());
        }

        if (dto.getAnother() != null) {
            existingEntity.setAnother(dto.getAnother());
        }

        if (dto.getAnotherDescription() != null) {
            existingEntity.setAnotherDescription(dto.getAnotherDescription());
        }
    }

	@Override
	public List<QuestionsUserAnotherHormonalCausesDTO> getAQuestionsUserAnotherHormonalCausesByUserId(Long id) {
        List<QuestionsUserAnotherHormonalCauses> entities = questionsUserAnotherHormonalCausesRepository.findByUserId(id);
        return entities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
	}
}