package Proyecto.Gestor.Excepciones.Personalizado;

public class ErrorCreacionUsuario extends RuntimeException {
    private static final String DESCRIPTION = "Error al crear el usuario";

    public ErrorCreacionUsuario(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
