package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserAnotherHormonalCausesDTO {
    @Schema(description = "ID de las causas hormonales por las cuales no menstrúa la usuaria", example = "1")
    private Long id;

    @Schema(description = "No menstrúa por síndrome de ovario poliquístico (SOP)", example = "0")
    private Integer polycysticOvarySyndrome;

    @Schema(description = "No menstrúa por hipotiroidismo", example = "1")
    private Integer hypothyroidism;

    @Schema(description = "No menstrúa por hiperprolactinemia", example = "0")
    private Integer hyperprolactinemia;

    @Schema(description = "No menstrúa por síndrome de Sheehan", example = "1")
    private Integer sheehanSyndrome;

    @Schema(description = "No menstrúa por insuficiencia ovárica prematura (IOP)", example = "0")
    private Integer prematureOvarianFailure;

    @Schema(description = "No menstrúa por trastornos de la función hipotalámica", example = "1")
    private Integer hypothalamicDisorders;

    @Schema(description = "No menstrúa por resistencia a la insulina", example = "1")
    private Integer insulinResistance;

    @Schema(description = "No menstrúa por otro motivo", example = "1")
    private Integer another;

    @Schema(description = "Detalle del motivo 'otro' por el cual no menstrúa", example = "No menstrúo por x motivo hormonal")
    private String anotherDescription;
    
    @Schema(description = "ID de la usuaria", example = "1")
    private Long userId;
}