package com.femfy.femfyapi.domain.interfaces;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.femfy.femfyapi.domain.entity.Cycle;
import com.femfy.femfyapi.domain.exception.CustomException;

public interface ICycleService {

    public Cycle registerCycleStart(Cycle cycle) throws Exception;
    public Cycle registerCycleEnd(Cycle cycle) throws IOException, CustomException;
    public List<Cycle> getCycleHistory(Long idUser) throws CustomException;
    public Cycle getCycleByIdUserAndDateBeging(Long idUser, String dateBeging) throws IOException, CustomException;
    public Map<String, String> deleteCycle(Long id) throws CustomException;
    public Cycle updateCycle (Cycle cycle) throws IllegalArgumentException;

}
