package Proyecto.Gestor.Controlador;

import Proyecto.Gestor.Excepciones.Personalizado.CredencialInicioSesionInvalida;
import Proyecto.Gestor.Excepciones.Personalizado.ErrorCreacionUsuario;
import Proyecto.Gestor.Modelo.DTO.UsuarioDTO;
import Proyecto.Gestor.Modelo.Usuario;
import Proyecto.Gestor.Servicio.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping(value = "/registro", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarUsuario(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioServicio.guardarUsuario(usuarioDTO);
            String mensaje = "Usuario registrado con éxito!";
            return ResponseEntity.ok(mensaje);
        } catch (ErrorCreacionUsuario e) {
            String mensaje = "Error al registrar usuario. Verifica los datos.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }
    }

    @PostMapping(value = "/iniciarSesion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> iniciarSesion(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO resultado = usuarioServicio.iniciarSesion(usuarioDTO.getUsuario(), usuarioDTO.getContrasenia());
            return ResponseEntity.ok(resultado);
        } catch (CredencialInicioSesionInvalida e) {
            String mensaje = "Usuario/Contraseña incorrecta. Por favor, verifica tus credenciales.";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mensaje);
        }
    }

    @GetMapping(value = "/listaUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        List<UsuarioDTO> usuarios = usuarioServicio.getListAllUsersIdDB();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "/obtener/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioServicio.obtenerUsuario(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
