package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

import com.femfy.femfyapi.domain.entity.User;

public interface IUserService {
	public User saveUser(User user);
	public User updateUser(User user);
	public String deleteUser(Long idUser);
	public User getUser(Long idUser);
	public List<User>getUsers();
}
