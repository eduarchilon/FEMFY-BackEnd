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
			User userDB = new User();
			
			userDB = userRepository.findById(userDTO.getIdUser()).get();
			if (userDB.getId() == null) {
	            throw new IllegalArgumentException("El ID del usuario no puede ser nulo para la actualización");
	        }
			
			if(userDTO.getTypeUserID()!= null) {
				TypeUser typeUser = new TypeUser();
				typeUser.setId(userDTO.getTypeUserID());
				userDB.setTypeUser(typeUser);
			}

			if(userDTO.getFirstName() != null){
				userDB.setFirstName(userDTO.getFirstName());
			}
			if(userDTO.getLastName()!= null){
				userDB.setLastName(userDTO.getLastName());
			}	
			if(userDTO.getBirthdate()!= null){
				userDB.setBirthdate(userDTO.getBirthdate());
			}
			if(userDTO.getUserName()!= null){
				userDB.setUserName(userDTO.getUserName());
			}
			if(userDTO.getPassword()!= null){
				userDB.setPassword(userDTO.getPassword());
			}
			if(userDTO.getIsSuscriptor()!= null){
				userDB.setIsSuscriptor(userDTO.getIsSuscriptor());
			}
			if(userDTO.getBirthdate()!= null){
				userDB.setBirthdate(userDTO.getBirthdate());
			}
			if(userDTO.getPhone()!= null){
				userDB.setPhone(userDTO.getPhone());
			}
			if(userDTO.getEmail()!= null){
				userDB.setEmail(userDTO.getEmail());
			}
			if(userDTO.getLocalidad()!= null){
				userDB.setLocalidad(userDTO.getLocalidad());
			}
			
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
