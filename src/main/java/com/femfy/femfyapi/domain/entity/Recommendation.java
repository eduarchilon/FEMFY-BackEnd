package com.femfy.femfyapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "recommendation")
public class Recommendation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String typeDisease;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private int ageReference;

    public Recommendation() {
    }
}
