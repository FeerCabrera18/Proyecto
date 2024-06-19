package Proyecto.Gestor.Excepciones;

import Proyecto.Gestor.Excepciones.Personalizado.CampoRequeridoVacio;
import Proyecto.Gestor.Excepciones.Personalizado.CredencialInicioSesionInvalida;
import Proyecto.Gestor.Excepciones.Personalizado.ErrorCreacionUsuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ManejadorExcepciones {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampoRequeridoVacio.class, ErrorCreacionUsuario.class})
    @ResponseBody
    public MensajeError errorSolicitud(HttpServletRequest request, Exception exception) {
        return new MensajeError(exception.getClass().getSimpleName(), exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CredencialInicioSesionInvalida.class)
    @ResponseBody
    public void unauthorized() {
    }
}
