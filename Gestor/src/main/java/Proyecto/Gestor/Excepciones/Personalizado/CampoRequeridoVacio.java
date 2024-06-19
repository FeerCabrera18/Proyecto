package Proyecto.Gestor.Excepciones.Personalizado;

public class CampoRequeridoVacio extends RuntimeException {
    private static final String DESCRIPTION = "Campo requerido vacio";

    public CampoRequeridoVacio(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
