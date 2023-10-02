package com.femfy.femfyapi.service;

import java.util.ArrayList;
import java.util.List;
/*import java.util.Optional;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.UserRepository;

import dto.UserDTO;



@Service
public class UserService implements IUserService{
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		try{
			User user = new User();
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setBirthdate(userDTO.getBirthdate());
			user.setUserName(userDTO.getUserName());
			user.setPassword(userDTO.getPassword());
			user.setIsSuscriptor(userDTO.getIsSuscriptor());
			user.setBirthdate(userDTO.getBirthdate());
			user.setPhone(userDTO.getPhone());
			user.setEmail(userDTO.getEmail());
			userRepository.save(user);
		} catch (Exception e) {
			System.out.print("NO SE LOGRA HACER EL MAPEO");
			// TODO: handle exception
		}
		return userDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		try{
			User user = new User();
			user.setId(userDTO.getIdUser());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setBirthdate(userDTO.getBirthdate());
			user.setUserName(userDTO.getUserName());
			user.setPassword(userDTO.getPassword());
			user.setIsSuscriptor(userDTO.getIsSuscriptor());
			user.setBirthdate(userDTO.getBirthdate());
			user.setPhone(userDTO.getPhone());
			user.setEmail(userDTO.getEmail());
			userRepository.save(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userDTO;
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
			// TODO: handle exception
		}
		
		
	}

	@Override
	public UserDTO getUser(Long idUser) {
		User user = new User();
		UserDTO userDTO = new UserDTO();
		try {
			user = userRepository.findById(idUser).get();
			if(!user.getEmail().isEmpty()){
				userDTO.setIdUser(user.getId());
				userDTO.setFirstName(user.getFirstName());
				userDTO.setLastName(user.getLastName());
				userDTO.setBirthdate(user.getBirthdate());
				userDTO.setUserName(user.getUserName());
				userDTO.setPassword(user.getPassword());
				userDTO.setIsSuscriptor(user.getIsSuscriptor());
				userDTO.setBirthdate(user.getBirthdate());
				userDTO.setPhone(user.getPhone());
				userDTO.setEmail(user.getEmail());
				return userDTO;
			}		
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return userDTO;
	}

	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> userList = new ArrayList<>();
		List<User> userListdb = new ArrayList<>();
		userListdb = userRepository.findAll();
		
		for (User user : userListdb) {
			UserDTO dto = new UserDTO();
			dto.setIdUser(user.getId());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setBirthdate(user.getBirthdate());
			dto.setUserName(user.getUserName());
			dto.setPassword(user.getPassword());
			dto.setIsSuscriptor(user.getIsSuscriptor());
			dto.setBirthdate(user.getBirthdate());
			dto.setPhone(user.getPhone());
			dto.setEmail(user.getEmail());
			userList.add(dto);
		}
		return userList;
	}

 
}
