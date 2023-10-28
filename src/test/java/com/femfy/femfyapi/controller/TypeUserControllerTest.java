package com.femfy.femfyapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.femfy.femfyapi.delivery.controller.TypeUserController;
import com.femfy.femfyapi.domain.entity.TypeUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.femfy.femfyapi.infraestructura.service.TypeUserService;

import com.femfy.femfyapi.delivery.dto.TypeUserDTO;

class TypeUserControllerTest {

    private TypeUserController controller;
    private TypeUserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(TypeUserService.class);
        controller = new TypeUserController(userService);
    }

    @Test
    void getTypeUserById_ReturnsOk() {
        TypeUserDTO dto = new TypeUserDTO();
        dto.setIdTypeUser(1L);
        Long typeUserId = 1L;
        TypeUser typeUser = new TypeUser();
        typeUser.setId(typeUserId);
        when(userService.getTypeUser(typeUserId)).thenReturn(typeUser);

        ResponseEntity<TypeUserDTO> response = controller.getTypeUserById(typeUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void getTypeUserById_ReturnsNotFound() {
        TypeUserDTO dto = new TypeUserDTO();
        Long typeUserId = 1L;
        TypeUser typeUser = new TypeUser();
        when(userService.getTypeUser(typeUserId)).thenReturn(typeUser);

        ResponseEntity<TypeUserDTO> response = controller.getTypeUserById(typeUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void getTypeUsers_ReturnsOk() {
        List<TypeUserDTO> typeUsersDTO = new ArrayList<>();
        typeUsersDTO.add(new TypeUserDTO());
        List<TypeUser> typeUsers = new ArrayList<>();
        typeUsers.add(new TypeUser());
        when(userService.getTypeUsers()).thenReturn(typeUsers);

        ResponseEntity<List<TypeUserDTO>> response = controller.getTypeUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(typeUsersDTO, response.getBody());
    }

    @Test
    void getTypeUsers_ReturnsNotFound() {
        List<TypeUser> typeUsers = new ArrayList<>();
        when(userService.getTypeUsers()).thenReturn(typeUsers);

        ResponseEntity<List<TypeUserDTO>> response = controller.getTypeUsers();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(typeUsers, response.getBody());
    }

    @Test
    void saveTypeUser_ReturnsCreated() {
        TypeUserDTO typeUserDTO = new TypeUserDTO();
        TypeUser typeUser = new TypeUser();
        when(userService.saveTypeUser(typeUser)).thenReturn(typeUser);

        ResponseEntity<TypeUserDTO> response = controller.saveTypeUser(typeUserDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(typeUserDTO, response.getBody());
    }

    @Test
    void updateTypeUser_ReturnsOk() {
        TypeUserDTO typeUserDTO = new TypeUserDTO();
        TypeUser typeUser = new TypeUser();
        when(userService.updateTypeUser(typeUser)).thenReturn(typeUser);

        ResponseEntity<TypeUserDTO> response = controller.updateTypeUser(typeUserDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(typeUserDTO, response.getBody());
    }

    @Test
    void deleteTypeUser_ReturnsOk() {
        Long typeUserId = 1L;
        when(userService.deleteTypeUser(typeUserId)).thenReturn("OK");

        ResponseEntity<String> response = controller.deleteTypeUser(typeUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TypeUser delete OK", response.getBody());
    }

    @Test
    void deleteTypeUser_ReturnsNotFound() {
        Long typeUserId = 1L;
        when(userService.deleteTypeUser(typeUserId)).thenReturn("NotFound");

        ResponseEntity<String> response = controller.deleteTypeUser(typeUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("TypeUser not found", response.getBody());
    }
}