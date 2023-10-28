package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class recommendationsDTO {
	
	@Schema(description = "ID del USUARIO", example = "2")
	Long idUser;
	
	@Schema(description = "descripcion del profesional", example = "el estudio: mamografia vence en:")
	String description;
	
	@Schema(description = "Dias restantes para relizarse nuevamente el estudio", example = "20")
	int expirationDays;
}
