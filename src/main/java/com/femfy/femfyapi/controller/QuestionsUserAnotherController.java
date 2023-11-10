package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.IQuestionsUserAnotherService;
import dto.QuestionsUserAnotherDTO;
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
@RequestMapping("/api/v1/questionsUserAnother")
public class QuestionsUserAnotherController {

    private final IQuestionsUserAnotherService questionsUserAnotherService;

    @Autowired
    public QuestionsUserAnotherController(IQuestionsUserAnotherService questionsUserAnotherService) {
        this.questionsUserAnotherService = questionsUserAnotherService;
    }

    @Operation(summary = "Obtener todos los datos de QuestionsUserAnother")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Datos de QuestionsUserAnother no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllQuestionsUserAnother")
    public ResponseEntity<List<QuestionsUserAnotherDTO>> getAllQuestionsUserAnother() {
        List<QuestionsUserAnotherDTO> data = questionsUserAnotherService.getQuestionsUserAnother();
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Obtener datos de QuestionsUserAnother por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Datos de QuestionsUserAnother no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{dataId}")
    public ResponseEntity<QuestionsUserAnotherDTO> getQuestionsUserAnotherById(@PathVariable("dataId") Long dataId) {
        Optional<QuestionsUserAnotherDTO> data = questionsUserAnotherService.getQuestionsUserAnother(dataId);
        return data.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener datos de QuestionsUserAnother por ID de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Datos de QuestionsUserAnother no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getQuestionsUserAnotherByUser/{userId}")
    public ResponseEntity<?> getQuestionsUserAnotherByUser(@PathVariable("userId") Long userId) {
        List<QuestionsUserAnotherDTO> data = questionsUserAnotherService.getQuestionsUserAnotherByUser(userId);
        return buildResponse(data);
    }

    @Operation(summary = "Crear datos de QuestionsUserAnother")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Datos de QuestionsUserAnother creados exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createQuestionsUserAnother")
    public ResponseEntity<QuestionsUserAnotherDTO> createQuestionsUserAnother(@RequestBody QuestionsUserAnotherDTO dataDTO) {
        QuestionsUserAnotherDTO createdData = questionsUserAnotherService.saveQuestionsUserAnother(dataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdData);
    }

    @Operation(summary = "Actualizar datos de QuestionsUserAnother")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Datos de QuestionsUserAnother no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateQuestionsUserAnother")
    public ResponseEntity<QuestionsUserAnotherDTO> updateQuestionsUserAnother(@RequestBody QuestionsUserAnotherDTO dataDTO) {
        if (dataDTO == null || dataDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        QuestionsUserAnotherDTO updatedData = questionsUserAnotherService.updateQuestionsUserAnother(dataDTO);

        if (updatedData == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedData);
    }

    @Operation(summary = "Eliminar datos de QuestionsUserAnother")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Datos de QuestionsUserAnother eliminados exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Datos de QuestionsUserAnother no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/deleteQuestionsUserAnother/{dataId}")
    public ResponseEntity<Void> deleteQuestionsUserAnother(@PathVariable("dataId") Long dataId) {
        questionsUserAnotherService.deleteQuestionsUserAnother(dataId);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> buildResponse(List<QuestionsUserAnotherDTO> data) {
        if (!data.isEmpty()) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}