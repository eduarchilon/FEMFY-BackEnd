package com.femfy.femfyapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.service.IFileService;
import com.femfy.femfyapi.service.IUserService;

import dto.FileDTO;
import dto.UserDTO;
import dto.recommendationsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/recommendations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationsController {
	
	@Autowired
	private  IUserService userService;
	
	@Autowired
	IFileService iFileService;
	
	@Operation(summary = "Recuperar recomendaciones para la usuaria")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "no existen recomendaciones",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi *****
	@GetMapping("/{userId}") 
	public ResponseEntity<List<recommendationsDTO>> getRecommendationsById(@PathVariable("userId") Long userId) {
		
		//Se recuperan los datos del usuraio
		UserDTO user = new UserDTO();
		user = userService.getUser(userId);
		
		//Se recupera la lista de los estudios existentes
		List<FileDTO> documents = new ArrayList<>();
		documents = iFileService.findDocumentsByIdUser(userId);
				
		//Genero un objeto de tipo recomendacion
		List<recommendationsDTO> recomendations = new ArrayList<>();
		
		for (FileDTO fileDto : documents) {
			int validDays = fileDto.getTypeStudy().getValidityOfStudy() - calculateDateDifference(fileDto.getStudyDate());
			if( validDays < 30) {
				recommendationsDTO dto = new recommendationsDTO();
				dto.setExpirationDays(validDays);
				dto.setDescription("El estudio: " + fileDto.getTypeStudy().getDescription() + ",  que fue realizado, el dìa: "+ fileDto.getStudyDate() + " vence en: " + validDays + " dias, se recomienda volver a realizarse el estudio.");
				recomendations.add(dto);
			}
			
		}
		
			return new ResponseEntity<>(recomendations, HttpStatus.OK);	
	}
	
	public int calculateDateDifference(Date dateOfStudy) {
	    Date currentDate = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    try {
	        currentDate = dateFormat.parse(dateFormat.format(currentDate));
	    } catch (ParseException e) {
	        System.err.println("Error al convertir la fecha formateada en Date: " + e.getMessage());
	        return 0;
	    }

	    long timeDifference = currentDate.getTime() - dateOfStudy.getTime();
	    int daysDifference = (int) TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);
	    
	    return daysDifference;
	}
}
