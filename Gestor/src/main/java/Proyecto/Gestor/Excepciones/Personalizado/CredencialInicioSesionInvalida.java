package Proyecto.Gestor.Excepciones.Personalizado;

public class CredencialInicioSesionInvalida extends RuntimeException {
    private static final String DESCRIPTION = "Credencial de inicio de sesion invalida";

    public CredencialInicioSesionInvalida(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
