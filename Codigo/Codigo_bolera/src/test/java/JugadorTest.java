// IMPORTANTE: Estos son los imports correctos de JUnit 5 que te faltaban
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// IMPORTANTE: Asegúrate de que importe tu clase Jugador si está en otro paquete
import entidades.Jugador;

public class JugadorTest {

    @Test
    public void testCreacionJugadorCorrecta() {
        Jugador jugador = new Jugador(1, "Pablo", "García", "Fernández", "Senior");

        assertAll("Comprobando propiedades del jugador",
                () -> assertEquals(1, jugador.getIdJugador(), "El ID debería ser 1"),
                () -> assertEquals("Pablo", jugador.getNombre(), "El nombre debería coincidir")
        );
    }
}
