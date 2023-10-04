package com.femfy.femfyapi.controller;

import java.util.List;
import java.util.Optional;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.femfy.femfyapi.service.IFamilyHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "api/v1/questionsUserFamilyHistory", produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyHistoryController {

    @Autowired
    private IFamilyHistoryService familyHistoryService;

    @Operation(summary = "Obtener el historial familiar de un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Historial familiar no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })

    @GetMapping("/{historialId}")
    public ResponseEntity<QuestionsUserFamilyHistory> getFamilyHistoryById(@PathVariable("historialId") Long historialId) {
        Optional<QuestionsUserFamilyHistory> historialFamiliar = familyHistoryService.getFamilyHistory(historialId);
        return historialFamiliar.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los historiales familiares de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Historiales familiares no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })

    @GetMapping
    public ResponseEntity<List<QuestionsUserFamilyHistory>> getAllFamilyHistories() {
        List<QuestionsUserFamilyHistory> historialesFamiliares = familyHistoryService.getFamilyHistories();
        if (!historialesFamiliares.isEmpty()) {
            return ResponseEntity.ok(historialesFamiliares);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear o actualizar el historial familiar de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })

    @PostMapping
    public ResponseEntity<QuestionsUserFamilyHistory> saveOrUpdateFamilyHistory(@RequestBody QuestionsUserFamilyHistory historialFamiliar) {
        QuestionsUserFamilyHistory savedHistorialFamiliar = familyHistoryService.saveOrUpdateFamilyHistory(historialFamiliar);
        return ResponseEntity.ok(savedHistorialFamiliar);
    }

    @Operation(summary = "Eliminar el historial familiar de un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Historial familiar eliminado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Historial familiar no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })

    @DeleteMapping("/delete/{historialId}")
    public ResponseEntity<Void> deleteFamilyHistory(@PathVariable("historialId") Long historialId) {
        familyHistoryService.deleteFamilyHistory(historialId);
        return ResponseEntity.noContent().build();
    }
}