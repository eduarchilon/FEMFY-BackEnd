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
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "polycystic_ovary_syndrome")
    private Integer polycysticOvarySyndrome;

    @Column(name = "hypothyroidism")
    private Integer hypothyroidism;

    @Column(name = "hyperprolactinemia")
    private Integer hyperprolactinemia;

    @Column(name = "sheehan_syndrome")
    private Integer sheehanSyndrome;

    @Column(name = "premature_ovarian_failure")
    private Integer prematureOvarianFailure;

    @Column(name = "hypothalamic_disorders")
    private Integer hypothalamicDisorders;

    @Column(name = "insulin_resistance")
    private Integer insulinResistance;

    @Column(name = "another")
    private Integer another;

    @Column(name = "another_description")
    private String anotherDescription;

    public QuestionsUserAnotherHormonalCauses() {
    }
}