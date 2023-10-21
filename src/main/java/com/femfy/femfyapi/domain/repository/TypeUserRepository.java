package com.femfy.femfyapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.domain.entity.TypeUser;

@Repository
public interface TypeUserRepository extends JpaRepository<TypeUser, Long> {

}
