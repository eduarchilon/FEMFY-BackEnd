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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.service.IFileService;
import com.femfy.femfyapi.service.IQuestionsUserFamilyHistoryService;
import com.femfy.femfyapi.service.IUserService;
import com.femfy.femfyapi.service.RecommendationService;

import dto.FileDTO;
import dto.QuestionsUserFamilyHistoryDTO;
import dto.RecommendationsDTO;
import dto.TypeRecommendationsDTO;
import dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/recommendations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationsController {

	private RecommendationService recommendationService;

	@Autowired
	public RecommendationsController(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	@Autowired
	private IUserService iuserService;

	@Autowired
	IFileService iFileService;

	@Autowired
	IQuestionsUserFamilyHistoryService iquestionsUserFamilyHistoryService;

	@Operation(summary = "Obtener todas las recomendaciones")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Respuesta OK", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Error inesperado del sistema", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Recomendaciones no econtradas", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("/getRecommendations")
	public ResponseEntity<List<TypeRecommendationsDTO>> getRecommendation() {
		List<TypeRecommendationsDTO> recommendations = this.recommendationService.getRecommendatios();
		if (!recommendations.isEmpty()) {
			return new ResponseEntity<>(recommendations, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(recommendations, HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Crear una nueva recomendacion")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tipo de usuario creado", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Error inesperado del sistema", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping("/createRecommendation")
	public ResponseEntity<TypeRecommendationsDTO> saveTypeStudy(
			@RequestBody TypeRecommendationsDTO recommendationsDTO) {
		recommendationsDTO = this.recommendationService.saveRecommendation(recommendationsDTO);
		return new ResponseEntity<>(recommendationsDTO, HttpStatus.CREATED);
	}

	@Operation(summary = "Eliminar un tipo de usuario por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tipo de usuario eliminado", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Error inesperado del sistema", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado", content = {
					@Content(mediaType = "application/json") }) })
	@DeleteMapping("/delete/{recommendationId}")
	public ResponseEntity<String> deleteRecommentacion(@PathVariable("recommendationId") Long recommendationId) {
		String responseDelete = this.recommendationService.deleteRecommendation(recommendationId);
		if (responseDelete.equalsIgnoreCase("OK")) {
			return new ResponseEntity<>("Recommendation delete OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Recommendation not found", HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Recuperar recomendaciones para la usuaria")
	@ApiResponses(value = { // -
			@ApiResponse(responseCode = "200", description = "Respuesta OK", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Unexpected system error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "no existen recomendaciones", content = {
					@Content(mediaType = "application/json") })

	})

	// ***** swagger - openapi *****
	@GetMapping("/{userId}")
	public ResponseEntity<RecommendationsDTO> getRecommendationsById(@PathVariable("userId") Long userId) {

		RecommendationsDTO recommendationsDTO = new RecommendationsDTO();

		List<TypeRecommendationsDTO> recommedationsByDoc = new ArrayList<>();
		List<TypeRecommendationsDTO> recommedationsByFamilyHist = new ArrayList<>();
		List<TypeRecommendationsDTO> recommedationsByTypeUsr = new ArrayList<>();

		// Se recuperan los datos del usuraio
		UserDTO user = new UserDTO();
		user = iuserService.getUser(userId);

		recommedationsByDoc = RecomendationsByDocuments(userId);
		recommedationsByFamilyHist = RecomendationsByHistoryfamily(user);

		recommendationsDTO.setRecommendationsByDocuemts(recommedationsByDoc);
		recommendationsDTO.setRecommendationsByFamilyHistory(recommedationsByFamilyHist);
		recommendationsDTO.setRecommendationsByTypeUser(recommedationsByTypeUsr);

		return new ResponseEntity<>(recommendationsDTO, HttpStatus.OK);
	}

	public List<TypeRecommendationsDTO> RecomendationsByHistoryfamily(UserDTO user) {

		List<TypeRecommendationsDTO> recommendations = this.recommendationService.getRecommendatios();

		// Genero un objeto de tipo recomendacion
		List<TypeRecommendationsDTO> recommedationsByFamilyHist = new ArrayList<>();

		// Se recupera la lista de Historial familiar
		List<QuestionsUserFamilyHistoryDTO> ListFamilyHist = new ArrayList<>();
		ListFamilyHist = iquestionsUserFamilyHistoryService.getQuestionsUserFamilyHistoriesByUserId(user.getIdUser());

		int edad = calculateAge(user.getBirthdate());

		for (QuestionsUserFamilyHistoryDTO qUserFamilyHisDTO : ListFamilyHist) {

			if (qUserFamilyHisDTO.isBreastCancer()) {
				if (edad > 30) {
					for (TypeRecommendationsDTO recommendationsDTO : recommendations) {
						if (recommendationsDTO.getTypeDisease().equalsIgnoreCase("BreastCancer")
								&& recommendationsDTO.getAgeReference() == 30) {
							TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
							dto.setDescription("segun tu edad actual: " + edad + ", " + recommendationsDTO.getDescription());
							recommedationsByFamilyHist.add(dto);
						}
					}
				}
			} else {
				if (edad >= 50 && edad < 70) {
					for (TypeRecommendationsDTO recommendationsDTO : recommendations) {
						if (recommendationsDTO.getTypeDisease().equalsIgnoreCase("BreastCancer")
								&& recommendationsDTO.getAgeReference() == 50) {
							TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
							dto.setDescription("segun tu edad actual: " + edad + ", " + recommendationsDTO.getDescription());
							recommedationsByFamilyHist.add(dto);
						}
					}
				}
			}

			if (qUserFamilyHisDTO.isEarlyMenopause()) {
				if (edad < 45) {
					for (TypeRecommendationsDTO recommendationsDTO : recommendations) {
						if (recommendationsDTO.getTypeDisease().equalsIgnoreCase("EarlyMenopause")
								&& recommendationsDTO.getAgeReference() == 45) {
							TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
							dto.setDescription("segun tu edad actual: " + edad + ", " + recommendationsDTO.getDescription());
							recommedationsByFamilyHist.add(dto);
						}
					}

				}
			}

			if (qUserFamilyHisDTO.isEndometriosis()) {
				for (TypeRecommendationsDTO recommendationsDTO : recommendations) {
					if (recommendationsDTO.getTypeDisease().equalsIgnoreCase("Endometriosis")) {
						TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
						dto.setDescription(recommendationsDTO.getDescription());
						recommedationsByFamilyHist.add(dto);
					}
				}
			}

			if (qUserFamilyHisDTO.isOvarianCancer()) {
				for (TypeRecommendationsDTO recommendationsDTO : recommendations) {
					if (recommendationsDTO.getTypeDisease().equalsIgnoreCase("OvarianCancer")) {
						TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
						dto.setDescription(recommendationsDTO.getDescription());
						recommedationsByFamilyHist.add(dto);
					}
				}

			}

			if (qUserFamilyHisDTO.isSop()) {
				for (TypeRecommendationsDTO recommendationsDTO : recommendations) {
					if (recommendationsDTO.getTypeDisease().equalsIgnoreCase("Sop")) {
						TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
						dto.setDescription(recommendationsDTO.getDescription());
						recommedationsByFamilyHist.add(dto);
					}
				}

			}

			if (qUserFamilyHisDTO.isUterineFibroids()) {
				for (TypeRecommendationsDTO recommendationsDTO : recommendations) {
					if (recommendationsDTO.getTypeDisease().equalsIgnoreCase("UterineFibroids")) {
						TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
						dto.setDescription(recommendationsDTO.getDescription());
						recommedationsByFamilyHist.add(dto);
					}
				}

			}
		}

		return recommedationsByFamilyHist;
	}

	public List<TypeRecommendationsDTO> RecomendationsByDocuments(Long userId) {

		// Genero un objeto de tipo recomendacion
		List<TypeRecommendationsDTO> recommedationsByDoc = new ArrayList<>();

		// Se recupera la lista de los estudios existentes
		List<FileDTO> documents = new ArrayList<>();
		documents = iFileService.findDocumentsByIdUser(userId);

		for (FileDTO fileDto : documents) {
			int validDays = fileDto.getTypeStudy().getValidityOfStudy()
					- calculateDateDifference(fileDto.getStudyDate());
			if (validDays < 30) {
				TypeRecommendationsDTO dto = new TypeRecommendationsDTO();
				dto.setExpirationDays(validDays);
				dto.setDescription("El estudio: " + fileDto.getTypeStudy().getDescription()
						+ ", que fue realizado, el dìa: " + fileDto.getStudyDate() + " vence en: " + validDays
						+ " dias, se recomienda volver a realizarse el estudio.");
				recommedationsByDoc.add(dto);
			}

		}
		return recommedationsByDoc;
	}

	public int calculateAge(Date dateBorn) {

		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();

		Calendar fechaNac = Calendar.getInstance();
		fechaNac.setTime(dateBorn);

		// Calcular la diferencia en años
		int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

		// Comprobar si aún no se ha cumplido el cumpleaños de este año
		if (fechaActual.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH)
				|| (fechaActual.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH)
						&& fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH))) {
			edad--;
		}

		return edad;
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