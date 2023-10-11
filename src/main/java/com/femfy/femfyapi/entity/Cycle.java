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

    @Column(nullable = true)
    private Date dateBeging;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    @Column(nullable = true)
    private String status;

    @Column(nullable = true)
    private int daysOfBleeding;

    public Cycle() {
    }
}