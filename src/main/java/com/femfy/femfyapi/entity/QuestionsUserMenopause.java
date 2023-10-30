package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions_user_menopause")
public class QuestionsUserMenopause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "suffocation")
    private Integer suffocation;

    @Column(name = "changes_in_menstrual_cycle")
    private Integer changesInMenstrualCycle;

    @Column(name = "vaginal_dryness")
    private Integer vaginalDryness;

    @Column(name = "changes_in_skin_and_hair")
    private Integer changesInSkinAndHair;

    @Column(name = "mood_changes")
    private Integer moodChanges;

    @Column(name = "sleeping_difficulties")
    private Integer sleepingDifficulties;

    @Column(name = "weight_gain")
    private Integer weightGain;

    @Column(name = "loss_of_bone_density")
    private Integer lossOfBoneDensity;

    @Column(name = "changes_in_libido")
    private Integer changesInLibido;

    public QuestionsUserMenopause() {
    }
}