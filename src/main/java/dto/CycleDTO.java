package dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CycleDTO {

    @Schema(description = "ID del USUARIO", example = "2")
    Long idUser;
    @Schema(description = "Fecha inicio del ciclo", example = "yyyy-MM-dd")
    String dateBeging;
    @Schema(description = "Fecha fin del ciclo", example = "yyyy-MM-dd")
    String dateEnd;
    @Schema(description = "Estado de animo", example = "Enojada - Alegre - Pensativa - Triste")
    String status;
}
