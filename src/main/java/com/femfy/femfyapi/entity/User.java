package com.femfy.femfyapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Builder
@Table(name="pepe")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String phone;
	@Column(name="mailAddress",unique = true, nullable = false)
	private String email;
	

	public User() {
		
	}
}
