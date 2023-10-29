package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserAnotherHormonalCausesDTO {
    @Schema(description = "ID de las causas hormonales por las cuales no menstrúa la usuaria", example = "1")
    private Long id;

    @Schema(description = "No menstrúa por síndrome de ovario poliquístico (SOP)", example = "true")
    private boolean polycysticOvarySyndrome;

    @Schema(description = "No menstrúa por hipotiroidismo", example = "true")
    private boolean hypothyroidism;

    @Schema(description = "No menstrúa por hiperprolactinemia", example = "true")
    private boolean hyperprolactinemia;

    @Schema(description = "No menstrúa por síndrome de Sheehan", example = "true")
    private boolean sheehanSyndrome;

    @Schema(description = "No menstrúa por insuficiencia ovárica prematura (IOP)", example = "true")
    private boolean prematureOvarianFailure;

    @Schema(description = "No menstrúa por trastornos de la función hipotalámica", example = "true")
    private boolean hypothalamicDisorders;

    @Schema(description = "No menstrúa por resistencia a la insulina", example = "true")
    private boolean insulinResistance;

    @Schema(description = "No menstrúa por otro motivo", example = "true")
    private boolean another;

    @Schema(description = "Detalle del motivo 'otro' por el cual no menstrúa", example = "No menstrúo por x motivo hormonal")
    private String anotherDescription;
}