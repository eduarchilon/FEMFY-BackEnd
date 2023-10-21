package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

import com.femfy.femfyapi.delivery.dto.TypeStudyDTO;

public interface ITypeStudyService {
	
    public TypeStudyDTO saveTypeStudy(TypeStudyDTO typeStudyDTO);

    public TypeStudyDTO updateTypeStudy(TypeStudyDTO typeStudyDTO);

    public String deleteTypeStudy(Long idTypeStudy);

    public TypeStudyDTO getTypeStudy(Long idTypeStudy);

    public List<TypeStudyDTO> getTypeStudies();
}
