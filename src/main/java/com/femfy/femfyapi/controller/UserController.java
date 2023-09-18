package com.femfy.femfyapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private  UserService userService;
	
	@Operation(summary = "Realizar la consulta por un suario en particular")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
				content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
						content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi *****
	@GetMapping("/{userId}") 
	public Optional<User> getUserById(@PathVariable("userId") Long userId) {
		return userService.getUser(userId);
	}
	
	@Operation(summary = "Realizar la consulta de todos los usuarios")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
				content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
						content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi ****
	@GetMapping
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@Operation(summary = "Realizar alta / actualizacion de los datos de un usuario ")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
				content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
						content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi ****
	@PostMapping 
	public User saveUpdate(@RequestBody User user) {
		userService.saveOrUpdate(user);
		return user;
	}

	@Operation(summary = "Eliminar un usuario mediante su Id")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
				content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
						content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi ****
	@DeleteMapping("/{userId}") 
	public void saveUpdate(@PathVariable("userId") Long userId) {
		userService.delete(userId);
	}
	

}
