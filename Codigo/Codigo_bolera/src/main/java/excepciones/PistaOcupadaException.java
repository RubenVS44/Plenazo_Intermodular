package excepciones;

/**
 * Excepción personalizada para controlar la disponibilidad de las pistas.
 * Se lanza al intentar reservar o asignar una pista que ya tiene un partido en curso.
 */
public class PistaOcupadaException extends Exception {
    public PistaOcupadaException(String mensaje) {
        super(mensaje);
    }
}
