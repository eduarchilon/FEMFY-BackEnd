package com.femfy.femfyapi.delivery.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.femfy.femfyapi.delivery.dto.QuestionsUserMenstruationDTO;
import com.femfy.femfyapi.delivery.mapper.QuestionsUserMenstruationMapper;
import com.femfy.femfyapi.domain.interfaces.IQuestionsUserMenstruationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/questionsUserMenstruation")
public class QuestionsUserMenstruationController {

    private final IQuestionsUserMenstruationService questionsUserMenstruationService;

    public QuestionsUserMenstruationController(IQuestionsUserMenstruationService questionsUserMenstruationService) {
        this.questionsUserMenstruationService = questionsUserMenstruationService;
    }

    @Operation(summary = "Obtener una respuesta básica sobre persona que menstrua por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionsUserMenstruationDTO> getQuestionById(@PathVariable("questionId") Long questionId) {
        Optional<QuestionsUserMenstruationDTO> question = questionsUserMenstruationService.getQuestionsUserMenstruation(questionId).map(QuestionsUserMenstruationMapper::mapToDTO);
        return question.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las respuestas básicas sobre personas que menstruan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<QuestionsUserMenstruationDTO>> getAllQuestions() {
        List<QuestionsUserMenstruationDTO> questions = questionsUserMenstruationService.getQuestionsUserMenstruations().stream().map(QuestionsUserMenstruationMapper::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(questions);
    }

    @Operation(summary = "Crear una pregunta sobre menstruación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pregunta sobre menstruación creada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("/createQuestion")
    public ResponseEntity<QuestionsUserMenstruationDTO> createQuestion(@RequestBody QuestionsUserMenstruationDTO questionDTO) {
        QuestionsUserMenstruationDTO savedQuestion = QuestionsUserMenstruationMapper.mapToDTO(questionsUserMenstruationService.saveQuestionsUserMenstruation(QuestionsUserMenstruationMapper.mapToEntity(questionDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }

    @Operation(summary = "Actualizar una pregunta sobre menstruación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pregunta sobre menstruación actualizada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PutMapping("/updateQuestion")
    public ResponseEntity<QuestionsUserMenstruationDTO> updateQuestion(@RequestBody QuestionsUserMenstruationDTO questionDTO) {
        if (questionDTO == null || questionDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        QuestionsUserMenstruationDTO updatedQuestion = QuestionsUserMenstruationMapper.mapToDTO(questionsUserMenstruationService.updateQuestionsUserMenstruation(QuestionsUserMenstruationMapper.mapToEntity(questionDTO)));
        return ResponseEntity.ok(updatedQuestion);
    }

    @Operation(summary = "Eliminar una respuesta básica sobre persona que menstrua por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pregunta sobre menstruación eliminada"),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionsUserMenstruationService.deleteQuestionsUserMenstruation(questionId);
        return ResponseEntity.noContent().build();
    }
}
