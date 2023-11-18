package com.femfy.femfyapi.delivery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.delivery.dto.TypeUserDTO;
import com.femfy.femfyapi.delivery.mapper.TypeUserMapper;
import com.femfy.femfyapi.domain.interfaces.ITypeUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.femfy.femfyapi.delivery.mapper.TypeUserMapper.mapToDTO;
import static com.femfy.femfyapi.delivery.mapper.TypeUserMapper.mapToEntity;
import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/typeuser", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeUserController {
    private final ITypeUserService typeUserService;

    public TypeUserController(ITypeUserService typeUserService) {
        this.typeUserService = typeUserService;
    }

    @Operation(summary = "Obtener un tipo de usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{typeUserId}")
    public ResponseEntity<TypeUserDTO> getTypeUserById(@PathVariable("typeUserId") Long typeUserId) {
        TypeUserDTO typeUser = mapToDTO(this.typeUserService.getTypeUser(typeUserId));
        if (typeUser.getIdTypeUser() != null) {
            return new ResponseEntity<>(typeUser, OK);
        } else {
            return new ResponseEntity<>(typeUser, NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener todos los tipos de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipos de usuario no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getTypeUsers")
    public ResponseEntity<List<TypeUserDTO>> getTypeUsers() {
        List<TypeUserDTO> typeUsers = this.typeUserService.getTypeUsers().stream().map(TypeUserMapper::mapToDTO).collect(Collectors.toList());
        if (!typeUsers.isEmpty()) {
            return new ResponseEntity<>(typeUsers, OK);
        } else {
            return new ResponseEntity<>(typeUsers, NOT_FOUND);
        }
    }

    @Operation(summary = "Crear un nuevo tipo de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de usuario creado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createTypeUser")
    public ResponseEntity<TypeUserDTO> saveTypeUser(@RequestBody TypeUserDTO typeUserDTO) {
        typeUserDTO = mapToDTO(this.typeUserService.saveTypeUser(mapToEntity(typeUserDTO)));
        return new ResponseEntity<>(typeUserDTO, CREATED);
    }

    @Operation(summary = "Actualizar un tipo de usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/updateTypeUser")
    public ResponseEntity<TypeUserDTO> updateTypeUser(@RequestBody TypeUserDTO typeUserDTO) {
        typeUserDTO = mapToDTO(this.typeUserService.updateTypeUser(mapToEntity(typeUserDTO)));
        return new ResponseEntity<>(typeUserDTO, OK);
    }

    @Operation(summary = "Eliminar un tipo de usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuario eliminado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{typeUserId}")
    public ResponseEntity<String> deleteTypeUser(@PathVariable("typeUserId") Long typeUserId) {
       try{
           this.typeUserService.deleteTypeUser(typeUserId);
           return new ResponseEntity<>("TypeUser delete OK", OK);
       }catch(Exception e){
           return new ResponseEntity<>("TypeUser not found", NOT_FOUND);
       }
    }
}
