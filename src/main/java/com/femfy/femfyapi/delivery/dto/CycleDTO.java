package com.femfy.femfyapi.delivery.dto;


import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CycleDTO {

    Long id;
    @Schema(description = "ID del USUARIO", example = "2")
    Long idUser;

    @Schema(description = "Fecha inicio del ciclo", example = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String dateBeging;

    @Schema(description = "Fecha fin del ciclo", example = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String dateEnd;

    @Schema(description = "Estado de animo", example = "Enojada - Alegre - Pensativa - Triste")
    String status;

    @Schema(description = "Dias de sangrado", example = "Ej. 5")
    Integer daysOfBleeding;

}
