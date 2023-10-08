package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "calendar_event")
public class CalendarEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "date_event")
    private Date dateEvent;

    @Column(name = "hour_alert")
    private Date hourAlert;

    @Column(name = "description")
    private String description;

    public CalendarEvent() {
    }
}
