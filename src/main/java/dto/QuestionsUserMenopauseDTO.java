package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserMenopauseDTO {
    @Schema(description = "ID de las preguntas de la usuaria menopáusica", example = "1")
    private Long id;

    @Schema(description = "ID de la usuaria relacionada a las preguntas", example = "1")
    private Long userId;

    @Schema(description = "Sofocos", example = "1")
    private Integer suffocation;

    @Schema(description = "Cambios en el ciclo menstrual", example = "1")
    private Integer changesInMenstrualCycle;

    @Schema(description = "Sequedad vaginal", example = "1")
    private Integer vaginalDryness;

    @Schema(description = "Cambios en la piel y el cabello", example = "1")
    private Integer changesInSkinAndHair;

    @Schema(description = "Cambios en el estado de ánimo", example = "1")
    private Integer moodChanges;

    @Schema(description = "Dificultades para dormir", example = "1")
    private Integer sleepingDifficulties;

    @Schema(description = "Aumento de peso", example = "1")
    private Integer weightGain;

    @Schema(description = "Pérdida de densidad ósea", example = "1")
    private Integer lossOfBoneDensity;

    @Schema(description = "Cambios en la libido", example = "1")
    private Integer changesInLibido;
}