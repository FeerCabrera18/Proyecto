package Proyecto.Gestor.Servicio;

import Proyecto.Gestor.Excepciones.Personalizado.CredencialInicioSesionInvalida;
import Proyecto.Gestor.Excepciones.Personalizado.ErrorCreacionUsuario;
import Proyecto.Gestor.Modelo.DTO.UsuarioDTO;
import Proyecto.Gestor.Modelo.Usuario;
import Proyecto.Gestor.Repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    // Método para guardar usuarios
    public Usuario guardarUsuario(UsuarioDTO usuarioDTO) {
        if (comprobarDTO(usuarioDTO)) {
            return this.usuarioRepositorio.save(new Usuario(usuarioDTO));
        }
        throw new ErrorCreacionUsuario("Error al guardar usuario!");
    }

    // Método para obtener usuarios por id
    public Usuario obtenerUsuario(Integer id){
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(id);
        return optionalUsuario.orElse(null);
    }

    // Método para iniciar sesión
    public UsuarioDTO iniciarSesion(String usuario, String contrasenia) {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findByUsuario(usuario);
        Usuario usuarioEncontrado = optionalUsuario.orElseThrow(() -> new CredencialInicioSesionInvalida("Usuario/Contraseña incorrecta!"));

        if (!usuarioEncontrado.getContrasenia().equals(contrasenia)) {
            throw new CredencialInicioSesionInvalida("Usuario/Contraseña incorrecta!");
        }

        return usuarioEncontrado.toDTO();
    }

    // Mostrar toda la lista de usuarios
    public List<UsuarioDTO> getListAllUsersIdDB() {
        return this.usuarioRepositorio.findAll()
                .stream()
                .map(Usuario::toDTO)
                .collect(Collectors.toList());
    }

    // Verificar que no haya campos vacíos
    private boolean comprobarDTO(UsuarioDTO usuarioDTO) {
        return usuarioDTO != null &&
                usuarioDTO.getUsuario() != null &&
                usuarioDTO.getContrasenia() != null &&
                usuarioDTO.getNombre() != null &&
                usuarioDTO.getApellido() != null &&
                usuarioDTO.getFechaNacimiento() != null &&
                usuarioDTO.getGenero() != null &&
                usuarioDTO.getEstadoCivil() != null &&
                usuarioDTO.getDireccion() != null &&
                usuarioDTO.getGmail() != null;
    }

    // Eliminar usuarios de la base de datos
    public void eliminarUsuario(int id) {
        usuarioRepositorio.deleteById(id);
    }
}