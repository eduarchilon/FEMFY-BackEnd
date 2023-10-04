package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.service.IQuestionsUserMenstruationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/questionsUserMenstruation", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionsUserMenstruationController {

    @Autowired
    private IQuestionsUserMenstruationService menstruationService;

    @Operation(summary = "Obtener las respuestas básicas sobre menstruación de un usuario por su ID")
    @ApiResponse(responseCode = "200", description = "Respuesta OK", content = {@Content(mediaType = "application/json")})
    @GetMapping("/{id}")
    public ResponseEntity<QuestionsUserMenstruation> getQuestionsUserMenstruationById(@PathVariable("id") Long id) {
        Optional<QuestionsUserMenstruation> menstruation = menstruationService.getQuestionsUserMenstruation(id);
        return menstruation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las respuestas básicas sobre menstruación")
    @ApiResponse(responseCode = "200", description = "Respuesta OK", content = {@Content(mediaType = "application/json")})
    @GetMapping
    public ResponseEntity<List<QuestionsUserMenstruation>> getAllQuestionsUserMenstruations() {
        List<QuestionsUserMenstruation> menstruations = menstruationService.getQuestionsUserMenstruations();
        return menstruations.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(menstruations);
    }

    @Operation(summary = "Crear o actualizar las respuestas básicas sobre menstruación")
    @ApiResponse(responseCode = "200", description = "Respuesta OK", content = {@Content(mediaType = "application/json")})
    @PostMapping
    public ResponseEntity<QuestionsUserMenstruation> saveOrUpdateQuestionsUserMenstruation(@RequestBody QuestionsUserMenstruation menstruation) {
        QuestionsUserMenstruation savedMenstruation = menstruationService.saveOrUpdateQuestionsUserMenstruation(menstruation);
        return ResponseEntity.ok(savedMenstruation);
    }

    @Operation(summary = "Eliminar las respuestas básicas sobre menstruación por su ID")
    @ApiResponse(responseCode = "204", description = "Preguntas sobre menstruación eliminadas con éxito")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestionsUserMenstruation(@PathVariable("id") Long id) {
        menstruationService.deleteQuestionsUserMenstruation(id);
        return ResponseEntity.noContent().build();
    }
}