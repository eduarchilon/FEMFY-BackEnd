package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

@Table(name="historial_familiar")
public class FamilyHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_User")
    private User user;

    private boolean cancer_mama;
    private boolean cancer_ovario;
    private boolean endometriosis;
    private boolean miomas_uterinos;
    private boolean sop;
    private boolean menopausia_temprana;

    public FamilyHistory (){
        
    }


}
