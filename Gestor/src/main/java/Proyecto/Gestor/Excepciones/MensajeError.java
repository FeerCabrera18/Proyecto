package Proyecto.Gestor.Excepciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeError {
    private String exception;
    private String mensaje;
    private String path;

    public MensajeError(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.mensaje = exception.getMessage();
        this.path = path;
    }
}
