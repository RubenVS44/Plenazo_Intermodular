package excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta registrar
 * un jugador cuyo identificador o nombre ya existe en el sistema.
 */
public class JugadorDuplicadoException extends Exception {
    // Constructor que recibe un mensaje personalizado y se lo pasa a la clase madre (Exception)
    public JugadorDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
