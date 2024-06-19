package Proyecto.Gestor.Modelo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class UsuarioDTO {

    @NotEmpty(message = "El usuario no puede estar vacío")
    @JsonProperty("usuario")
    private String usuario;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @JsonProperty("contrasenia")
    private String contrasenia;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @JsonProperty("nombre")
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @JsonProperty("apellido")
    private String apellido;

    @NotEmpty(message = "La fecha de nacimiento no puede estar vacía")
    @JsonProperty("fecha_nacimiento")
    private String fechaNacimiento;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("estado_civil")
    private String estadoCivil;

    @JsonProperty("direccion")
    private String direccion;

    @Email(message = "Debe ser un correo electrónico válido")
    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @JsonProperty("gmail")
    private String gmail;
}