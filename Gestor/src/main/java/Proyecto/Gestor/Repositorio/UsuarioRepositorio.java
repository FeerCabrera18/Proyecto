package Proyecto.Gestor.Repositorio;

import Proyecto.Gestor.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsuarioAndContrasenia(String usuario, String contrasenia);

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findById(Integer id);
}
