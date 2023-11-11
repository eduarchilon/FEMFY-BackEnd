package com.femfy.femfyapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.service.IQuestionsUserAnotherCongenitalCausesService;

import dto.QuestionsUserAnotherCongenitalCausesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/congenitalCauses")
public class QuestionsUserAnotherCongenitalCausesController {

    private final IQuestionsUserAnotherCongenitalCausesService congenitalCausesService;

    @Autowired
    public QuestionsUserAnotherCongenitalCausesController(IQuestionsUserAnotherCongenitalCausesService congenitalCausesService) {
        this.congenitalCausesService = congenitalCausesService;
    }

    @Operation(summary = "Obtener todas las causas congénitas de las usuarias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causas congénitas no encontradas",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllCongenitalCauses")
    public ResponseEntity<?> getAllCongenitalCauses() {
        List<QuestionsUserAnotherCongenitalCausesDTO> congenitalCauses = congenitalCausesService.getAllQuestionsUserAnotherCongenitalCauses();
        return buildResponse(congenitalCauses);
    }

    @Operation(summary = "Obtener una causa congénita por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causa congénita no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{causeId}")
    public ResponseEntity<?> getCongenitalCauseById(@PathVariable("causeId") Long causeId) {
        Optional<QuestionsUserAnotherCongenitalCausesDTO> congenitalCause = congenitalCausesService.getQuestionsUserAnotherCongenitalCausesById(causeId);
        return congenitalCause.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una causa congénita de la usuaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Causa congénita creada exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createCongenitalCause")
    public ResponseEntity<QuestionsUserAnotherCongenitalCausesDTO> createCongenitalCause(@RequestBody QuestionsUserAnotherCongenitalCausesDTO causeDTO) {
        QuestionsUserAnotherCongenitalCausesDTO createdCause = congenitalCausesService.saveQuestionsUserAnotherCongenitalCauses(causeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCause);
    }

    @Operation(summary = "Actualizar una causa congénita de la usuaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causa congénita no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateCongenitalCause")
    public ResponseEntity<QuestionsUserAnotherCongenitalCausesDTO> updateCongenitalCause(@RequestBody QuestionsUserAnotherCongenitalCausesDTO causeDTO) {
        if (causeDTO == null || causeDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        QuestionsUserAnotherCongenitalCausesDTO updatedCause = congenitalCausesService.updateQuestionsUserAnotherCongenitalCauses(causeDTO);

        if (updatedCause == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedCause);
    }

    @Operation(summary = "Eliminar una causa congénita de la usuaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Causa congénita eliminada exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Causa congénita no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/deleteCongenitalCause/{causeId}")
    public ResponseEntity<Void> deleteCongenitalCause(@PathVariable("causeId") Long causeId) {
        congenitalCausesService.deleteQuestionsUserAnotherCongenitalCauses(causeId);
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
    @GetMapping("/getAllCongenitalCausesByUser/{userId}")
    public ResponseEntity<?> getAllCongenitalCausesByUser(@PathVariable("userId") Long userId) {
        List<QuestionsUserAnotherCongenitalCausesDTO> congenitalCauses = congenitalCausesService.getAQuestionsUserAnotherCongenitalCausesByUserId(userId);
        return buildResponse(congenitalCauses);
    }
    
    private ResponseEntity<?> buildResponse(List<QuestionsUserAnotherCongenitalCausesDTO> causes) {
        if (!causes.isEmpty()) {
            return ResponseEntity.ok(causes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}