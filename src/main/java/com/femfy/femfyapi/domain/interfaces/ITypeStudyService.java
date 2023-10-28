package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

import com.femfy.femfyapi.domain.entity.TypeStudy;

public interface ITypeStudyService {
	
    public TypeStudy saveTypeStudy(TypeStudy typeStudy);

    public TypeStudy updateTypeStudy(TypeStudy typeStudy);

    public String deleteTypeStudy(Long idTypeStudy);

    public TypeStudy getTypeStudy(Long idTypeStudy);

    public List<TypeStudy> getTypeStudies();
}
