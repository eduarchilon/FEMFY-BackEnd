package com.femfy.femfyapi.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class CalendarEventDTO {
    @Schema(description = "ID del evento del calendario", example = "1")
    private Long id;

    @Schema(description = "ID del usuario relacionado", example = "1")
    private Long userId;

    @Schema(description = "Título del evento", example = "Reunión importante")
    private String title;

    @Schema(description = "Fecha del evento", example = "2023-10-08")
    private String dateEvent;

    @Schema(description = "Hora de alerta del evento", example = "09:00:00")
    private String hourAlert;

    @Schema(description = "Descripción del evento", example = "Reunión con el equipo de trabajo")
    private String description;
}