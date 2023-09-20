package com.femfy.femfyapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.service.IFileService;
import com.femfy.femfyapi.service.IUploadFileService;

import dto.FileDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/v1/file", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {
	@Autowired
	IUploadFileService iUploadFileService;
	
	@Autowired
	IFileService iFileService;
	
	@Operation(summary = "Permite subir un docuemento dentro del repositorio de AZURE. todos los datos son requeridos")
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
	@PostMapping("/uploadFile")
	public ResponseEntity<FileDTO> uploadFile(  @RequestBody FileDTO fileDTO)
	{
		String uploadResult =  iUploadFileService.uploadFile(fileDTO);
		
		if(uploadResult.equalsIgnoreCase("OK")) {
			fileDTO	 = iFileService.insertFile(fileDTO);
				return new ResponseEntity<>(fileDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(fileDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@Operation(summary = "Permite descargar un docuemento del repositorio de AZURE. Solo se requiere el nombre del archivo que se quiere DESCARGAR")
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
	@PostMapping("/downloadFile")
	public ResponseEntity<FileDTO> downloadFile(@RequestBody FileDTO fileDTO)
	{
		fileDTO =  iUploadFileService.downloadFile(fileDTO);
		return new ResponseEntity<>(fileDTO, HttpStatus.OK);
	}
	
	@Operation(summary = "Permite eliminar un docuemento del repositorio de AZURE. Solo se requiere el nombre del archivo que se quiere ELIMINAR")
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
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(@RequestBody FileDTO fileDTO)
	{
		String deleteResult = iUploadFileService.deleteFile(fileDTO);
		
		if(deleteResult.equalsIgnoreCase("OK")) {
			String result = iFileService.deleteFile(fileDTO);
			
			if(result.equalsIgnoreCase("OK")){
				return new ResponseEntity<String>("Documento eliminado",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("No se pudo eliminar el Documento",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else {
			return new ResponseEntity<String>("No se pudo eliminar el Documento",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Realizar la consulta de todos los documentos de un usuario. Solo es necesaro enviar el ID del usuario, para recuperar todos los documentos")
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
	public ResponseEntity<List<FileDTO>> getUserById(@PathVariable("userId") Long userId) {
		List<FileDTO> documents = new ArrayList<>();
		documents = iFileService.findDocumentsByIsUser(userId);
		if(documents.size() >0){
			return new ResponseEntity<>(documents, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(documents, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
