package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserMenstruationDTO {
    @Schema(description = "ID de la respuesta básica sobre persona que menstrua", example = "1")
    private Long id;

    @Schema(description = "Fecha de la última vez que menstruó", example = "2023-10-04")
    private java.sql.Date lastTime;

    @Schema(description = "Duración del último ciclo", example = "5")
    private Integer lastCycleDuration;

    @Schema(description = "Regularidad", example = "true")
    private Boolean regular;

    @Schema(description = "Duración del ciclo regular", example = "28")
    private Integer regularCycleDuration;

    @Schema(description = "Duración general del sangrado menstrual", example = "4")
    private Integer bleedingDuration;
}
