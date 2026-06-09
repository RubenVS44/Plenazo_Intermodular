package entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ResultadoClasificacionTest {

    private Pareja local;
    private Pareja visitante;
    private Partido partido;

    @BeforeEach
    public void setUp() {
        Jugador j1 = new Jugador(1, "Pablo", "García", "Fernández", "Senior");
        Jugador j2 = new Jugador(2, "María", "López", "Gómez", "Senior");
        Jugador j3 = new Jugador(3, "Carlos", "Pérez", "Sánchez", "Junior");
        Jugador j4 = new Jugador(4, "Ana", "Martínez", "Ruiz", "Junior");

        local = new Pareja(1, "Local Team", j1, j2);
        visitante = new Pareja(2, "Visitante Team", j3, j4);

        Pista pista = new Pista(1, 101, "Ocupada");
        ReservaPista reserva = new ReservaPista(1, LocalDateTime.now(), pista);
        partido = new Partido(1, 1, local, visitante, reserva);
    }

    @Test
    public void testCalcularGanadorEquipoLocal() {
        // 500 bolos locales vs 400 visitantes -> Gana local
        ResultadoClasificacion resultado = new ResultadoClasificacion(1, 500, 400, 2, 0, partido);

        Pareja ganador = resultado.calcularGanador();

        assertNotNull(ganador, "El ganador no debe ser nulo");
        assertEquals("Local Team", ganador.getNombrePareja(), "Debería haber ganado el equipo local");
    }

    @Test
    public void testCalcularGanadorEquipoVisitante() {
        // 350 bolos locales vs 480 visitantes -> Gana visitante
        ResultadoClasificacion resultado = new ResultadoClasificacion(2, 350, 480, 0, 2, partido);

        Pareja ganador = resultado.calcularGanador();

        assertNotNull(ganador, "El ganador no debe ser nulo");
        assertEquals("Visitante Team", ganador.getNombrePareja(), "Debería haber ganado el equipo visitante");
    }

    @Test
    public void testCalcularGanadorEmpate() {
        // Mismos bolos -> Empate (devuelve null)
        ResultadoClasificacion resultado = new ResultadoClasificacion(3, 400, 400, 1, 1, partido);

        Pareja ganador = resultado.calcularGanador();

        assertNull(ganador, "En caso de empate, el ganador debe retornar null");
    }
}
