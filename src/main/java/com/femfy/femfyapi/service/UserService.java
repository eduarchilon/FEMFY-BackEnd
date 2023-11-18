package com.femfy.femfyapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.TypeUser;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.UserRepository;

import dto.UserDTO;



@Service
public class UserService implements IUserService{
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		try{
			TypeUser typeUser = new TypeUser();
			typeUser.setId(userDTO.getTypeUserID());
			User user = new User();
			user.setTypeUser(typeUser);
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setUserName(userDTO.getUserName());
			user.setPassword(userDTO.getPassword());
			user.setIsSuscriptor(userDTO.getIsSuscriptor());
			user.setBirthdate(userDTO.getBirthdate());
			user.setPhone(userDTO.getPhone());
			user.setEmail(userDTO.getEmail());
			user.setLocalidad(userDTO.getLocalidad());
			user.setEmotion(userDTO.getEmotion());
			user.setFriendsPhone(userDTO.getFriendsPhone());
			user.setFriendsName(userDTO.getFriendsName());
			user.setFriendsEmail(userDTO.getFriendsEmail());
			user.setState(userDTO.getState());
			userRepository.save(user);
			
			userDTO.setIdUser(user.getId());
			
		} catch (Exception e) {
			System.out.print("NO SE LOGRA HACER EL MAPEO");
		}
		return userDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {

			
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

			if(userDTO.getPhone()!= null){
				userDB.setPhone(userDTO.getPhone());
			}
			if(userDTO.getEmail()!= null){
				userDB.setEmail(userDTO.getEmail());
			}
			if(userDTO.getLocalidad()!= null){
				userDB.setLocalidad(userDTO.getLocalidad());
			}
			if(userDTO.getEmotion()!= null){
				userDB.setEmotion(userDTO.getEmotion());
			}
			if(userDTO.getFriendsPhone()!= null){
				userDB.setFriendsPhone(userDTO.getFriendsPhone());
			}
			if(userDTO.getFriendsName()!= null){
				userDB.setFriendsName(userDTO.getFriendsName());
			}
			if(userDTO.getFriendsEmail()!= null){
				userDB.setFriendsEmail(userDTO.getFriendsEmail());
			}
			if(userDTO.getState()!= null){
				userDB.setState(userDTO.getState());
			}
			
			User userUpdate = userRepository.save(userDB);	
			return mapToDTO(userUpdate);
			
	}
	
	private UserDTO mapToDTO(User user){
        if(user == null){
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        UserDTO dto = new UserDTO();

        if(user.getId() != null){
            dto.setIdUser(user.getId());
        }

        dto.setIdUser(user.getId());
        dto.setTypeUserID(user.getTypeUser().getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setBirthdate(user.getBirthdate().toString());
		dto.setUserName(user.getUserName());
		dto.setPassword(user.getPassword());
		dto.setIsSuscriptor(user.getIsSuscriptor());
		dto.setPhone(user.getPhone());
		dto.setEmail(user.getEmail());
		dto.setLocalidad(user.getLocalidad());
		dto.setEmotion(user.getEmotion());
		dto.setFriendsPhone(user.getFriendsPhone());
		dto.setFriendsName(user.getFriendsName());
		dto.setFriendsEmail(user.getFriendsEmail());
		dto.setState(user.getState());
        return dto;
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
	public UserDTO getUser(Long idUser) {
		User user = new User();
		UserDTO userDTO = new UserDTO();
		try {
			user = userRepository.findById(idUser).get();
			if(!user.getEmail().isEmpty()){
				userDTO.setIdUser(user.getId());
				userDTO.setTypeUserID(user.getTypeUser().getId());
				userDTO.setFirstName(user.getFirstName());
				userDTO.setLastName(user.getLastName());
				userDTO.setBirthdate(user.getBirthdate().toString());
				userDTO.setUserName(user.getUserName());
				userDTO.setPassword(user.getPassword());
				userDTO.setIsSuscriptor(user.getIsSuscriptor());
				userDTO.setPhone(user.getPhone());
				userDTO.setEmail(user.getEmail());
				userDTO.setLocalidad(user.getLocalidad());
				userDTO.setEmotion(user.getEmotion());
				userDTO.setFriendsPhone(user.getFriendsPhone());
				userDTO.setFriendsName(user.getFriendsName());
				userDTO.setFriendsEmail(user.getFriendsEmail());
				userDTO.setState(user.getState());
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
			dto.setTypeUserID(user.getTypeUser().getId());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setBirthdate(user.getBirthdate().toString());
			dto.setUserName(user.getUserName());
			dto.setPassword(user.getPassword());
			dto.setIsSuscriptor(user.getIsSuscriptor());
			dto.setPhone(user.getPhone());
			dto.setEmail(user.getEmail());
			dto.setLocalidad(user.getLocalidad());
			dto.setEmotion(user.getEmotion());
			dto.setFriendsPhone(user.getFriendsPhone());
			dto.setFriendsName(user.getFriendsName());
			dto.setFriendsEmail(user.getFriendsEmail());
			dto.setState(user.getState());
			userList.add(dto);
		}
		return userList;
	}

 
}
