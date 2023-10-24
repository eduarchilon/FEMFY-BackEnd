package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.IForumPostService;
import dto.ForumPostDTO;
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
@RequestMapping("/api/v1/forumPosts")
public class ForumPostController {

    private final IForumPostService forumPostService;

    @Autowired
    public ForumPostController(IForumPostService forumPostService) {
        this.forumPostService = forumPostService;
    }

    @Operation(summary = "Obtener todos los mensajes del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Mensaje del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllForumPosts")
    public ResponseEntity<List<ForumPostDTO>> getAllForumPosts() {
        List<ForumPostDTO> posts = forumPostService.getAllForumPosts();
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Obtener un mensaje del foro por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Mensaje del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{postId}")
    public ResponseEntity<ForumPostDTO> getForumPostById(@PathVariable("postId") Long postId) {
        Optional<ForumPostDTO> post = forumPostService.getForumPostById(postId);
        return post.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener mensaje(s) de foro por ID de tema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Mensaje(s) de foro no encontrado(s)",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getForumPostsByTopic/{topicId}")
    public ResponseEntity<?> getForumPostsByTopic(@PathVariable("topicId") Long topicId) {
        List<ForumPostDTO> posts = forumPostService.getForumPostsByTopic(topicId);
        return buildResponse(posts);
    }

    @Operation(summary = "Obtener mensaje(s) de foro por ID de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Mensaje(s) de foro no encontrado(s)",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getForumPostsByUser/{userId}")
    public ResponseEntity<?> getForumPostsByUser(@PathVariable("userId") Long userId) {
        List<ForumPostDTO> posts = forumPostService.getForumPostsByUser(userId);
        return buildResponse(posts);
    }

    @Operation(summary = "Crear un mensaje del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mensaje del foro creado exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createForumPost")
    public ResponseEntity<ForumPostDTO> createForumPost(@RequestBody ForumPostDTO postDTO) {
        ForumPostDTO createdPost = forumPostService.saveForumPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @Operation(summary = "Actualizar un mensaje del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Mensaje del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateForumPost")
    public ResponseEntity<ForumPostDTO> updateForumPost(@RequestBody ForumPostDTO postDTO) {
        if (postDTO == null || postDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        ForumPostDTO updatedPost = forumPostService.updateForumPost(postDTO);

        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @Operation(summary = "Eliminar un mensaje del foro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mensaje del foro eliminado exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Mensaje del foro no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/deleteForumPost/{postId}")
    public ResponseEntity<Void> deleteForumPost(@PathVariable("postId") Long postId) {
        forumPostService.deleteForumPost(postId);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> buildResponse(List<ForumPostDTO> posts) {
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}