package com.femfy.femfyapi.domain.interfaces;

import java.util.List;

/*import dto.FileDTO;*/
import com.femfy.femfyapi.delivery.dto.UserDTO;

public interface IUserService {
	public UserDTO saveUser(UserDTO userDTO);
	public UserDTO updateUser(UserDTO userDTO);
	public String deleteUser(Long idUser);
	public UserDTO getUser(Long idUser);
	public List<UserDTO>getUsers();
}
