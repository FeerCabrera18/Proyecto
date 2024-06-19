package Proyecto.Gestor.Modelo;

import Proyecto.Gestor.Modelo.DTO.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @SequenceGenerator(
            name = "secuenciaUsuario",
            sequenceName = "secuenciaUsuario",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuenciaUsuario")
    @Column(name = "id")
    private int id;

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "gmail", nullable = false, unique = true)
    private String gmail;

    // Formatter for converting between LocalDate and String
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Usuario(UsuarioDTO usuarioDTO) {
        this.usuario = usuarioDTO.getUsuario();
        this.contrasenia = usuarioDTO.getContrasenia();
        this.nombre = usuarioDTO.getNombre();
        this.apellido = usuarioDTO.getApellido();
        this.fechaNacimiento = LocalDate.parse(usuarioDTO.getFechaNacimiento(), DATE_FORMATTER);
        this.genero = usuarioDTO.getGenero();
        this.estadoCivil = usuarioDTO.getEstadoCivil();
        this.direccion = usuarioDTO.getDireccion();
        this.gmail = usuarioDTO.getGmail();
    }

    public UsuarioDTO toDTO() {
        return UsuarioDTO.builder()
                .usuario(this.usuario)
                .contrasenia(this.contrasenia)
                .nombre(this.nombre)
                .apellido(this.apellido)
                .fechaNacimiento(this.fechaNacimiento.format(DATE_FORMATTER))
                .genero(this.genero)
                .estadoCivil(this.estadoCivil)
                .direccion(this.direccion)
                .gmail(this.gmail)
                .build();
    }
}