package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ForumTopicDTO {
    @Schema(description = "ID del tema del foro", example = "1")
    private Long id;

    @Schema(description = "Título del tema del foro", example = "Maternidad")
    private String title;

    @Schema(description = "URL de la imagen relacionada con el tema", example = "https://ejemplo.com/imagen.jpg")
    private String image;

    @Schema(description = "Fecha y hora de creación del tema del foro", example = "2023-10-21 15:00:00")
    private Date createdDate;

    @Schema(description = "Lista de publicaciones relacionadas con el tema")
    private List<ForumPostDTO> forumPosts;

}