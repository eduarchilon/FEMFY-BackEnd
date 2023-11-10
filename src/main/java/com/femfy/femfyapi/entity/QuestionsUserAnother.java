package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions_user_another")
public class QuestionsUserAnother {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "congenital_causes_id")
    private QuestionsUserAnotherCongenitalCauses congenitalCauses;

    @OneToOne
    @JoinColumn(name = "hormonal_causes_id")
    private QuestionsUserAnotherHormonalCauses hormonalCauses;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "another")
    private Integer another;

    @Column(name = "another_description")
    private String anotherDescription;

    public QuestionsUserAnother() {
    }
}