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

    @Column(name = "malformations_uterine")
    private boolean malformationsUterine;

    @Column(name = "turner_syndrome")
    private boolean turnerSyndrome;

    @Column(name = "another")
    private boolean another;

    @Column(name = "another_description")
    private String anotherDescription;

    public QuestionsUserAnotherCongenitalCauses() {
    }
}