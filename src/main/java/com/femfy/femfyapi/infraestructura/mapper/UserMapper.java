package com.femfy.femfyapi.infraestructura.mapper;


import com.femfy.femfyapi.delivery.dto.UserDTO;
import com.femfy.femfyapi.domain.entity.TypeUser;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

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
        dto.setBirthdate(user.getBirthdate());
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        dto.setIsSuscriptor(user.getIsSuscriptor());
        dto.setBirthdate(user.getBirthdate());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setEmotion(user.getEmotion());
        dto.setLocalidad(user.getLocalidad());
        dto.setAvatar(user.getAvatar());

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
        user.setBirthdate(dto.getBirthdate());
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setIsSuscriptor(dto.getIsSuscriptor());
        user.setBirthdate(dto.getBirthdate());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setEmotion(dto.getEmotion());
        user.setLocalidad(dto.getLocalidad());
        user.setAvatar(dto.getAvatar());

        return user;
    }
}
