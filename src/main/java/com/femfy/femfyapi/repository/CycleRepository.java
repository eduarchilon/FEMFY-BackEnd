package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.entity.FileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, Long> {

    @Transactional
    public Cycle findByIdUserAndDateBeging(Long idUser, Date dateBeging);

    @Transactional
    public List<Cycle> findAllByIdUser(Long idUser);

}
