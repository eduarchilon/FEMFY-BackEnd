package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cycle")
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUser;

    @Temporal(TemporalType.DATE)
    private Date dateBeging;

    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    private String status;

    private Integer daysOfBleeding;

    public Cycle() {
    }
}