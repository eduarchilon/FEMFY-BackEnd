package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions_user_another_congenital_causes")
public class QuestionsUserAnotherCongenitalCauses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "malformations_uterine")
    private Integer malformationsUterine;

    @Column(name = "turner_syndrome")
    private Integer turnerSyndrome;

    @Column(name = "another")
    private Integer another;

    @Column(name = "another_description")
    private String anotherDescription;

    public QuestionsUserAnotherCongenitalCauses() {
    }
}