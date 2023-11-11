package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.IQuestionsUserAnotherHormonalCausesService;
import dto.QuestionsUserAnotherHormonalCausesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/hormonalCauses")
public class QuestionsUserAnotherHormonalCausesController {

    private final IQuestionsUserAnotherHormonalCausesService hormonalCausesService;

    @Autowired
    public QuestionsUserAnotherHormonalCausesController(IQuestionsUserAnotherHormonalCausesService hormonalCausesService) {
        this.hormonalCausesService = hormonalCausesService;
    }

    @Operation(summary = "Obtener todas las causas hormonales de las usuarias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causas hormonales no encontradas",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllHormonalCauses")
    public ResponseEntity<?> getAllHormonalCauses() {
        List<QuestionsUserAnotherHormonalCausesDTO> hormonalCauses = hormonalCausesService.getAllQuestionsUserAnotherHormonalCauses();
        return buildResponse(hormonalCauses);
    }

    @Operation(summary = "Obtener una causa hormonal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causa hormonal no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{causeId}")
    public ResponseEntity<?> getHormonalCauseById(@PathVariable("causeId") Long causeId) {
        Optional<QuestionsUserAnotherHormonalCausesDTO> hormonalCause = hormonalCausesService.getQuestionsUserAnotherHormonalCausesById(causeId);
        return hormonalCause.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una causa hormonal de la usuaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Causa hormonal creada exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createHormonalCause")
    public ResponseEntity<QuestionsUserAnotherHormonalCausesDTO> createHormonalCause(@RequestBody QuestionsUserAnotherHormonalCausesDTO causeDTO) {
        QuestionsUserAnotherHormonalCausesDTO createdCause = hormonalCausesService.saveQuestionsUserAnotherHormonalCauses(causeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCause);
    }

    @Operation(summary = "Actualizar una causa hormonal de la usuaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causa hormonal no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateHormonalCause")
    public ResponseEntity<QuestionsUserAnotherHormonalCausesDTO> updateHormonalCause(@RequestBody QuestionsUserAnotherHormonalCausesDTO causeDTO) {
        if (causeDTO == null || causeDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        QuestionsUserAnotherHormonalCausesDTO updatedCause = hormonalCausesService.updateQuestionsUserAnotherHormonalCauses(causeDTO);

        if (updatedCause == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedCause);
    }

    @Operation(summary = "Eliminar una causa hormonal de la usuaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Causa hormonal eliminada exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causa hormonal no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/deleteHormonalCause/{causeId}")
    public ResponseEntity<Void> deleteHormonalCause(@PathVariable("causeId") Long causeId) {
        hormonalCausesService.deleteQuestionsUserAnotherHormonalCauses(causeId);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Obtener todas las causas hormonales de una usuaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causas hormonales no encontradas",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllHormonalCausesByUser/{userId}")
    public ResponseEntity<?> getAllHormonalCausesByUser(@PathVariable("userId") Long userId) {
        List<QuestionsUserAnotherHormonalCausesDTO> hormonalCauses = hormonalCausesService.getAQuestionsUserAnotherHormonalCausesByUserId(userId);
        return buildResponse(hormonalCauses);
    }

    private ResponseEntity<?> buildResponse(List<QuestionsUserAnotherHormonalCausesDTO> causes) {
        if (!causes.isEmpty()) {
            return ResponseEntity.ok(causes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}