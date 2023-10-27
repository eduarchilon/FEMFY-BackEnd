package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "forum_topic")
public class ForumTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<ForumPost> forumPosts;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public ForumTopic() {
    }
}