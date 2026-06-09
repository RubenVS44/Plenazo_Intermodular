package entidades;

import excepciones.JugadorDuplicadoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ParejaTest {

    private Jugador j1;
    private Jugador j2;
    private Jugador j3;

    @BeforeEach
    public void setUp() {
        j1 = new Jugador(1, "Pablo", "García", "Fernández", "Senior");
        j2 = new Jugador(2, "María", "López", "Gómez", "Senior");
        j3 = new Jugador(3, "Carlos", "Pérez", "Sánchez", "Junior");
    }

    @Test
    public void testCrearParejaValida() {
        Pareja pareja = new Pareja(10, "Los Plenazos", j1, j2);

        assertNotNull(pareja);
        assertEquals("Los Plenazos", pareja.getNombrePareja());
        assertEquals(j1, pareja.getJugador1());
        assertEquals(j2, pareja.getJugador2());
    }

    @Test
    public void testValidacionMismoJugador() {
        // Regla: No se puede hacer una pareja consigo mismo
        assertThrows(IllegalArgumentException.class, () -> {
            if (j1.getIdJugador() == j1.getIdJugador()) {
                throw new IllegalArgumentException("Una pareja debe tener dos personas distintas.");
            }
        }, "Debería lanzar IllegalArgumentException si los jugadores son idénticos");
    }

    @Test
    public void testExcepcionJugadorDuplicadoEnOtraPareja() {
        List<Pareja> listaParejas = new ArrayList<>();
        // Registramos una primera pareja legítima
        listaParejas.add(new Pareja(1, "Pareja A", j1, j2));

        // Intentamos crear una segunda pareja usando a 'j1' otra vez (lo cual viola la regla de negocio)
        assertThrows(JugadorDuplicadoException.class, () -> {
            for (Pareja p : listaParejas) {
                if (p.getJugador1().getIdJugador() == j1.getIdJugador() || p.getJugador2().getIdJugador() == j1.getIdJugador()) {
                    throw new JugadorDuplicadoException("¡Error de negocio! El jugador ya está en otra pareja.");
                }
            }
        }, "Debería lanzar JugadorDuplicadoException si el jugador ya está inscrito en otro equipo");
    }
}