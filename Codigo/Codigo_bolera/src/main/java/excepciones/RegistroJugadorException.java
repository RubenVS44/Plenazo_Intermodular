package excepciones;

/**
 * Excepción genérica para controlar fallos o violaciones de reglas de negocio
 * específicas durante el proceso de registro de un jugador en la aplicación.
 */
public class RegistroJugadorException extends Exception {
    public RegistroJugadorException(String mensaje) {
        super(mensaje);
    }
}
