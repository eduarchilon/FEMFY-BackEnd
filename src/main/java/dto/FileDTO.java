package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FileDTO {
	@Schema(description = "ID del USUARIO", example = "2")
	Long idUser;
	@Schema(description = "Nombre del USUARIO", example = "archivos de prueba")
	String fileName;
	@Schema(description = "Archivo que se quiere trabajar, en formato base64", example = "UHJ1ZWJhIC0gSE9MQSBNVU5ETw0K")
	String fileBase64;
	@Schema(description = "Extension del archivos que se esta trabajando", example = "txt")
	String fileExt;
}
