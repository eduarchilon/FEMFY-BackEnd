package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.IForumTopicService;
import dto.ForumTopicDTO;
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
@RequestMapping("/api/v1/forumTopics")
public class ForumTopicController {

    private final IForumTopicService forumTopicService;

    @Autowired
    public ForumTopicController(IForumTopicService forumTopicService) {
        this.forumTopicService = forumTopicService;
    }

    @Operation(summary = "Obtener todos los temas del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tema del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllForumTopics")
    public ResponseEntity<List<ForumTopicDTO>> getAllForumTopics() {
        List<ForumTopicDTO> topics = forumTopicService.getAllForumTopics();
        return ResponseEntity.ok(topics);
    }

    @Operation(summary = "Obtener un tema del foro por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tema del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{topicId}")
    public ResponseEntity<ForumTopicDTO> getForumTopicById(@PathVariable("topicId") Long topicId) {
        Optional<ForumTopicDTO> topic = forumTopicService.getForumTopicById(topicId);
        return topic.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un tema del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tema del foro creado exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createForumTopic")
    public ResponseEntity<ForumTopicDTO> createForumTopic(@RequestBody ForumTopicDTO topicDTO) {
        ForumTopicDTO createdTopic = forumTopicService.saveForumTopic(topicDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
    }

    @Operation(summary = "Actualizar un tema del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tema del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateForumTopic")
    public ResponseEntity<ForumTopicDTO> updateForumTopic(@RequestBody ForumTopicDTO topicDTO) {
        if (topicDTO == null || topicDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        ForumTopicDTO updatedTopic = forumTopicService.updateForumTopic(topicDTO);

        if (updatedTopic == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedTopic);
    }

    @Operation(summary = "Eliminar un tema del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tema del foro eliminado exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tema del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{topicId}")
    public ResponseEntity<Void> deleteForumTopic(@PathVariable("topicId") Long topicId) {
        forumTopicService.deleteForumTopic(topicId);
        return ResponseEntity.noContent().build();
    }
}