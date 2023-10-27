package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.IForumReplayService;
import dto.ForumReplayDTO;
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
@RequestMapping("/api/v1/forumReplies")
public class ForumReplayController {

    private final IForumReplayService forumReplayService;

    @Autowired
    public ForumReplayController(IForumReplayService forumReplayService) {
        this.forumReplayService = forumReplayService;
    }

    @Operation(summary = "Obtener todas las respuestas de foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Respuesta de foro no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllForumReplies")
    public ResponseEntity<List<ForumReplayDTO>> getAllForumReplies() {
        List<ForumReplayDTO> replies = forumReplayService.getAllForumReplays();
        return ResponseEntity.ok(replies);
    }

    @Operation(summary = "Obtener una respuesta de foro por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Respuesta de foro no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{replyId}")
    public ResponseEntity<ForumReplayDTO> getForumReplayById(@PathVariable("replyId") Long replyId) {
        Optional<ForumReplayDTO> reply = forumReplayService.getForumReplayById(replyId);
        return reply.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener respuesta(s) de foro por ID de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Respuesta(s) de foro no encontrada(s)",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getForumRepliesByUser/{userId}")
    public ResponseEntity<?> getForumRepliesByUser(@PathVariable("userId") Long userId) {
        List<ForumReplayDTO> replies = forumReplayService.getForumReplaysByUser(userId);
        return buildResponse(replies);
    }

    @Operation(summary = "Obtener respuesta(s) de foro por ID de mensaje de foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Respuesta(s) de foro no encontrada(s)",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getForumRepliesByPost/{postId}")
    public ResponseEntity<?> getForumRepliesByPost(@PathVariable("postId") Long postId) {
        List<ForumReplayDTO> replies = forumReplayService.getForumReplaysByPost(postId);
        return buildResponse(replies);
    }

    @Operation(summary = "Crear una respuesta de foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta de foro creada exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createForumReplay")
    public ResponseEntity<ForumReplayDTO> createForumReplay(@RequestBody ForumReplayDTO replyDTO) {
        ForumReplayDTO createdReply = forumReplayService.saveForumReplay(replyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReply);
    }

    @Operation(summary = "Actualizar una respuesta de foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Respuesta de foro no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateForumReplay")
    public ResponseEntity<ForumReplayDTO> updateForumReplay(@RequestBody ForumReplayDTO replyDTO) {
        if (replyDTO == null || replyDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        ForumReplayDTO updatedReply = forumReplayService.updateForumReplay(replyDTO);

        if (updatedReply == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedReply);
    }

    @Operation(summary = "Eliminar una respuesta de foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Respuesta de foro eliminada exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Respuesta de foro no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/deleteForumReplay/{replyId}")
    public ResponseEntity<Void> deleteForumReplay(@PathVariable("replyId") Long replyId) {
        forumReplayService.deleteForumReplay(replyId);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> buildResponse(List<ForumReplayDTO> replies) {
        if (!replies.isEmpty()) {
            return ResponseEntity.ok(replies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}