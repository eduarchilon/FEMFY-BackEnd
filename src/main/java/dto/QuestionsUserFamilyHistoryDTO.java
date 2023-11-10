package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserFamilyHistoryDTO {
    @Schema(description = "ID de la historia familiar del usuario", example = "1")
    private Long id;

    @Schema(description = "ID del usuario relacionado", example = "1")
    private Long userId;

    @Schema(description = "Historial de cáncer de mama", example = "1")
    private Integer breastCancer;

    @Schema(description = "Historial de cáncer de ovario", example = "1")
    private Integer ovarianCancer;

    @Schema(description = "Historial de endometriosis", example = "1")
    private Integer endometriosis;

    @Schema(description = "Historial de fibromas uterinos", example = "1")
    private Integer uterineFibroids;

    @Schema(description = "Historial de síndrome de ovario poliquístico (SOP)", example = "1")
    private Integer sop;

    @Schema(description = "Historial de menopausia temprana", example = "1")
    private Integer earlyMenopause;
}