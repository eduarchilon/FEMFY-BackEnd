package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class ForumReplayDTO {
    @Schema(description = "ID de la respuesta en el foro", example = "1")
    private Long id;

    @Schema(description = "ID de la publicación a la que se responde", example = "1")
    private Long postId;

    @Schema(description = "ID del usuario que creó la respuesta", example = "1")
    private Long userId;

    @Schema(description = "Contenido de la respuesta en el foro", example = "Gracias por la información.")
    private String content;

    @Schema(description = "Fecha y hora de creación de la respuesta", example = "2023-10-21 16:30:00")
    private Date createdDate;

}