package com.femfy.femfyapi.delivery.mapper;


import com.femfy.femfyapi.delivery.dto.UserDTO;
import com.femfy.femfyapi.domain.entity.TypeUser;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.infraestructura.Utils;

public class UserMapper {

    public static UserDTO mapToDTO(User user) {
        if (user == null) {
            throw new EntityNotFoundException("Menopause not found");
        }

        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getId());
        dto.setTypeUserID(user.getTypeUser().getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthdate(user.getBirthdate() != null ? user.getBirthdate().toString() : null);
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        dto.setIsSuscriptor(user.getIsSuscriptor());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setLocalidad(user.getLocalidad());
        dto.setEmotion(user.getEmotion());
        dto.setFriendsName(user.getFriendsName());
        dto.setFriendsPhone(user.getFriendsPhone());
        dto.setFriendsEmail(user.getFriendsEmail());
        dto.setState(user.getState());

        return dto;
    }

    public static User mapToEntity(UserDTO dto) {
        User user = new User();
        TypeUser typeUser = new TypeUser();
        typeUser.setId(dto.getTypeUserID());

        user.setId(dto.getIdUser());

        user.setId(dto.getIdUser());
        user.setTypeUser(typeUser);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthdate(Utils.parseDate(dto.getBirthdate()));
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setIsSuscriptor(dto.getIsSuscriptor());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setLocalidad(dto.getLocalidad());
        user.setEmotion(dto.getEmotion());
        user.setFriendsName(dto.getFriendsName());
        user.setFriendsPhone(dto.getFriendsPhone());
        user.setFriendsEmail(dto.getFriendsEmail());
        user.setState(dto.getState());

        return user;
    }
}
