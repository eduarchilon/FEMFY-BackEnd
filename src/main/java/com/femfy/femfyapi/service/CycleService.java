package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.exception.CustomException;
import com.femfy.femfyapi.repository.CycleRepository;
import dto.CycleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CycleService implements ICycleService{

    @Autowired
    CycleRepository cycleRepository;

    @Override
    public CycleDTO registerCycleStart(Cycle cycle) throws Exception {
        try{
            CycleDTO dto = new CycleDTO();
            dto.setStatus(cycle.getStatus());
            dto.setIdUser(cycle.getIdUser());
            dto.setDateBeging(String.valueOf(parseDate(String.valueOf(cycle.getDateBeging()))));
            cycleRepository.save(cycle);
            return dto;
        }catch (Exception e){
            throw new CustomException("Error al registrar inicio del ciclo: " + e.getMessage());
        }
    }

    @Override
    public CycleDTO registerCycleEnd(Cycle cycle) throws IOException, CustomException {
        try{
            CycleDTO cycleDTO = this.getCycleByIdUserAndDateBeging(cycle.getIdUser(), String.valueOf(cycle.getDateBeging()));
            cycle.setDateBeging(parseDate(cycleDTO.getDateBeging()));
            cycleDTO.setDateEnd(String.valueOf(parseDate(String.valueOf(cycle.getDateEnd()))));
            cycle.setId(cycleDTO.getId());
            cycleRepository.save(cycle);
            return cycleDTO;
        }catch (Exception e){
            throw new CustomException("Error al registrar fin del ciclo: " + e.getMessage());
        }
    }

    @Override
    public List<CycleDTO> getCycleHistory(Long idUser) {
        List<CycleDTO> dtoList = new ArrayList<>();
        List<Cycle> cycleList = new ArrayList<>();
        try {
            cycleList = cycleRepository.findAllByIdUser(idUser);

            for(Cycle cycle : cycleList){
                CycleDTO dto = new CycleDTO();
                dto.setStatus(cycle.getStatus());
                dto.setDateBeging(String.valueOf(cycle.getDateBeging()));
                dto.setIdUser(cycle.getIdUser());
                dto.setDateEnd(String.valueOf(cycle.getDateEnd()));

                dtoList.add(dto);
            }
        }catch (Exception e) {

        }
        return dtoList;
    }

    @Override
    public CycleDTO getCycleByIdUserAndDateBeging(Long idUser, String dateBeging) throws CustomException {
        CycleDTO cycleDTO = new CycleDTO();

        try{
            System.out.println("Fecha String que llega" + dateBeging);
            Date dateSql = this.parseDate(dateBeging);
            System.out.println("Fecha sql: "+dateSql);
            Cycle cycle =  cycleRepository.findByIdUserAndDateBeging(idUser, dateSql);
            cycleDTO.setDateBeging(String.valueOf(cycle.getDateBeging()));
            cycleDTO.setStatus(cycle.getStatus());
            cycleDTO.setIdUser(cycle.getIdUser());
            cycleDTO.setId(cycle.getId());

        }catch (Exception e){
            throw new CustomException("Error al obtener el ciclo del usuario: " + e.getMessage());
        }
        return cycleDTO;
    }


    private Date parseDate(String date) throws CustomException {
        java.sql.Date  dateSql = null;
        if(date != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                java.util.Date parsedDate = format.parse(date);
                dateSql = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException | java.text.ParseException e) {
                throw new CustomException("Error al parsear la fecha");
            }
        }
        return dateSql;
    }

}
