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

    private boolean breastCancer;
    private boolean ovarianCancer;
    private boolean endometriosis;
    private boolean uterineFibroids;
    private boolean sop;
    private boolean earlyMenopause;

    public QuestionsUserFamilyHistory(){

    }


}
