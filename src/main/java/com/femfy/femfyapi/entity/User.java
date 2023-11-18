package com.femfy.femfyapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

@Table(name="data_user")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne
    @JoinColumn(name = "type_user_id")
    private TypeUser typeUser;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CalendarEvent> calendarEvents;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ForumPost> forumPosts;

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private Integer isSuscriptor;
	private String birthdate;
	private String phone;
	@Column(name="mailAddress",unique = true, nullable = false)
	private String email;
	private String localidad;
	private String emotion;
	private String friendsPhone;
	private String friendsName;
	private String friendsEmail;
	private String state;

	public User() {
		
	}
}
