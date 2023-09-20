package com.femfy.femfyapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

@Table(name="fileuser")
public class FileUser {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private Long idUser;
	private String fileName;
	private String fileExt;


	public FileUser() {
		
	}
}
