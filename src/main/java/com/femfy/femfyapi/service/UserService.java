package com.femfy.femfyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.UserRepository;



@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
 
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public Optional<User> getUser( Long id){
		return userRepository.findById(id);
	}
	
	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}
	
	public void delete( Long id){
		userRepository.deleteById(id);
	}
 
}
