package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions_user_another_hormonal_causes")
public class QuestionsUserAnotherHormonalCauses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "polycystic_ovary_syndrome")
    private boolean polycysticOvarySyndrome;

    @Column(name = "hypothyroidism")
    private boolean hypothyroidism;

    @Column(name = "hyperprolactinemia")
    private boolean hyperprolactinemia;

    @Column(name = "sheehan_syndrome")
    private boolean sheehanSyndrome;

    @Column(name = "premature_ovarian_failure")
    private boolean prematureOvarianFailure;

    @Column(name = "hypothalamic_disorders")
    private boolean hypothalamicDisorders;

    @Column(name = "insulin_resistance")
    private boolean insulinResistance;

    @Column(name = "another")
    private boolean another;

    @Column(name = "another_description")
    private String anotherDescription;

    public QuestionsUserAnotherHormonalCauses() {
    }
}