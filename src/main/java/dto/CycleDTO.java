package dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CycleDTO {

    Long id;
    @Schema(description = "ID del USUARIO", example = "2")
    Long idUser;
    @Schema(description = "Fecha inicio del ciclo", example = "yyyy-MM-dd")
    java.util.Date dateBeging;
    @Schema(description = "Fecha fin del ciclo", example = "yyyy-MM-dd")
    java.util.Date dateEnd;
    @Schema(description = "Estado de animo", example = "Enojada - Alegre - Pensativa - Triste")
    String status;
    @Schema(description = "Dias de sangrado", example = "Ej. 5")
    int daysOfBleeding;

}
