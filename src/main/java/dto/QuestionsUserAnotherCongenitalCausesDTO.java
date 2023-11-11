package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserAnotherCongenitalCausesDTO {
    @Schema(description = "ID de las causa congénita por la cual no menstrúa la usuaria", example = "1")
    private Long id;

    @Schema(description = "No menstrua por malformaciones uterinas", example = "1")
    private Integer malformationsUterine;

    @Schema(description = "No menstrua por síndrome de Turner", example = "0")
    private Integer turnerSyndrome;

    @Schema(description = "No menstrua por otro motivo", example = "1")
    private Integer another;

    @Schema(description = "Detalle del motivo 'otro' por el cual no menstrúa", example = "No menstrúo por x motivo congénito")
    private String anotherDescription;
    
    @Schema(description = "ID de la usuaria", example = "1")
    private Long userId;

}