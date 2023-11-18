package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {
	@Schema(description = "ID del USUARIO", example = "2")
	Long idUser;
	@Schema(description = "ID tipo USUARIO", example = "2")
	Long typeUserID;
	@Schema(description = "Nombre del USUARIO", example = "Maria")
	String firstName;
	@Schema(description = "Apellido del USUARIO", example = "Argento")
	String lastName;
	@Schema(description = "Nick / User Name", example = "MariaArgento23")
	String userName;
	@Schema(description = "Contraseña del Usuario", example = "pass1234")
	String password;
	@Schema(description = "Usuario es suscriptor", example = "1 = suscripto, 0 = no suscripto ")
	Integer isSuscriptor;
	@Schema(description = "Fecha de Nacimiento del Usuario", example = "2000-01-01")
	String birthdate;
	@Schema(description = "Telefono del Usuario", example = "1122334455")
	String phone;
	@Schema(description = "Email del Usuario", example = "femfy2023@gmail.com")
	String email;
	@Schema(description = "Localidad del Usuario", example = "La Matanza")
	String localidad;
	@Schema(description = "Emosión del Usuario", example = "Feliz/Angustiada")
	String emotion;
	@Schema(description = "Telefono de Amigo del Usuario", example = "1166778899")
	String friendsPhone;
	@Schema(description = "Nombre del Amigo del Usuario", example = "Mario")
	String friendsName;
	@Schema(description = "Email de Amistad del Usuario", example = "userfriends@gmail.com")
	String friendsEmail;
	@Schema(description = "Estado del Usuario", example = "Ovulando/Lutea/Menstruante")
	String state;
	
}
