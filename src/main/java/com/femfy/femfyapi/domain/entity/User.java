package com.femfy.femfyapi.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	private Boolean isSuscriptor;
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	private String phone;
	@Column(name="mailAddress",unique = true, nullable = false)
	private String email;
	private String localidad;


	public User() {
		
	}
}
