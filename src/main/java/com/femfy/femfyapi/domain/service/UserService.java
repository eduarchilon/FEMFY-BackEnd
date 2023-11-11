package com.femfy.femfyapi.domain.service;

import java.util.List;

import com.femfy.femfyapi.domain.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.domain.entity.TypeUser;
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
			
			userDB = userRepository.findById(user.getId()).get();
			if (userDB.getId() == null) {
	            throw new IllegalArgumentException("El ID del usuario no puede ser nulo para la actualizaci�n");
	        }
			
			if(user.getTypeUser()!= null) {
				TypeUser typeUser = new TypeUser();
				typeUser.setId(user.getTypeUser().getId());
				userDB.setTypeUser(typeUser);
			}

			if(user.getFirstName() != null){
				userDB.setFirstName(user.getFirstName());
			}
			if(user.getLastName()!= null){
				userDB.setLastName(user.getLastName());
			}	
			if(user.getBirthdate()!= null){
				userDB.setBirthdate(user.getBirthdate());
			}
			if(user.getUserName()!= null){
				userDB.setUserName(user.getUserName());
			}
			if(user.getPassword()!= null){
				userDB.setPassword(user.getPassword());
			}
			if(user.getIsSuscriptor()!= null){
				userDB.setIsSuscriptor(user.getIsSuscriptor());
			}
			if(user.getBirthdate()!= null){
				userDB.setBirthdate(user.getBirthdate());
			}
			if(user.getPhone()!= null){
				userDB.setPhone(user.getPhone());
			}
			if(user.getEmail()!= null){
				userDB.setEmail(user.getEmail());
			}
			if(user.getLocalidad()!= null){
				userDB.setLocalidad(user.getLocalidad());
			}
			if (user.getEmotion()!= null){
				userDB.setEmotion(user.getEmotion());
			}
			if(user.getFriendsName()!= null){
				userDB.setFriendsName(user.getFriendsName());
			}
			if(user.getFriendsPhone()!= null){
				userDB.setFriendsPhone(user.getFriendsPhone());
			}
			if(user.getFriendsEmail()!= null){
				userDB.setFriendsEmail(user.getFriendsEmail());
			}
			if(user.getState()!= null){
				userDB.setState(user.getState());
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
