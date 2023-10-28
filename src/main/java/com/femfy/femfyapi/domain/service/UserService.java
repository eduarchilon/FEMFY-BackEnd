package com.femfy.femfyapi.domain.service;

import java.util.List;

import com.femfy.femfyapi.domain.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.repository.UserRepository;




@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		try{
			userRepository.save(user);
			
		} catch (Exception e) {
			System.out.print("NO SE LOGRA HACER EL MAPEO");
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		try{
			userRepository.save(user);
		} catch (Exception e) {
			System.out.print("NO SE LOGRA HACER EL MAPEO PARA ACTUALIZAR ");
		}
		return user;
	}

	@Override
	public String deleteUser(Long idUser) {
		String responseDelete = "";
		try {
			userRepository.deleteById(idUser);
			responseDelete = "OK";
			return responseDelete;
		} catch (Exception e) {
			System.out.print("Error" + e.getMessage() );
			responseDelete = "Error";
			return responseDelete;
		}
		
		
	}

	@Override
	public User getUser(Long idUser) {
		User user = new User();
		try {
			user = userRepository.findById(idUser).get();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

 
}
