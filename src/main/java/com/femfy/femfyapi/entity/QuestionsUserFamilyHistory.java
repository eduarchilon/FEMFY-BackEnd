package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="questions_user_family_history")
public class QuestionsUserFamilyHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "breast_cancer")
    private Integer breastCancer;

    @Column(name = "ovarian_cancer")
    private Integer ovarianCancer;

    @Column(name = "endometriosis")
    private Integer endometriosis;

    @Column(name = "uterine_fibroids")
    private Integer uterineFibroids;

    @Column(name = "sop")
    private Integer sop;

    @Column(name = "early_menopause")
    private Integer earlyMenopause;

    public QuestionsUserFamilyHistory() {
    }
}