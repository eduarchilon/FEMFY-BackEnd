package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserAnotherDTO {
    @Schema(description = "ID de las causas por las cuales no menstrúa la usuaria", example = "1")
    private Long id;

    @Schema(description = "ID de la usuaria relacionada", example = "1")
    private Long userId;

    @Schema(description = "No menstrúa por causas congénitas", example = "true")
    private QuestionsUserAnotherCongenitalCausesDTO congenitalCauses;

    @Schema(description = "No menstrúa por causas hormonales", example = "true")
    private QuestionsUserAnotherHormonalCausesDTO hormonalCauses;

    @Schema(description = "No menstrúa por otro motivo", example = "true")
    private boolean another;

    @Schema(description = "Detalle del motivo 'otro' por el cual no menstrúa", example = "No menstrúo por x motivo")
    private String anotherDescription;
}