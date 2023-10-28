package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ForumPostDTO {
    @Schema(description = "ID de la publicación en el foro", example = "1")
    private Long id;

    @Schema(description = "ID del tema relacionado", example = "1")
    private Long topicId;

    @Schema(description = "ID del usuario que creó la publicación", example = "1")
    private Long userId;

    @Schema(description = "Título de la publicación en el foro", example = "Hola, soy nueva.")
    private String title;

    @Schema(description = "Contenido de la publicación en el foro", example = "Este es un mensaje en el foro.")
    private String content;

    @Schema(description = "Fecha y hora de creación de la publicación", example = "2023-10-21 14:30:00")
    private Date createdDate;

    @Schema(description = "Lista de respuestas a la publicación en el foro")
    private List<ForumReplayDTO> replies;

}