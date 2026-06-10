package codigo;

import java.util.ArrayList;
import java.util.List;

import entidades.Jugador;
import entidades.Pareja;
import entidades.Partido;
import entidades.ReservaPista;
import excepciones.RegistroJugadorException;

/**
 * Clase encargada de administrar toda la lógica del negocio de los torneos.
 * Trabaja con estructuras de datos persistentes en memoria (Colecciones).
 */
public class gestionTorneo {

    // Colecciones que emulan las tablas de una base de datos directamente en la memoria RAM de la aplicación
    private List<Jugador> listaJugadores;
    private List<Pareja> listaParejas;
    private List<Partido> calendarioPartidos;
    private List<ReservaPista> historialReservas;

    // Constructor que inicializa las listas como ArrayLists vacíos para evitar excepciones de tipo NullPointerException
    public gestionTorneo() {
        this.listaJugadores = new ArrayList<>();
        this.listaParejas = new ArrayList<>();
        this.calendarioPartidos = new ArrayList<>();
        this.historialReservas = new ArrayList<>();
    }

    /**
     * Registra un jugador en la lista controlando manualmente que no se repita su ID.
     * @param nuevoJugador Objeto jugador a inscribir
     * @throws RegistroJugadorException Si encuentra un jugador con el mismo ID exacto
     */
    public void registrarJugador(Jugador nuevoJugador) throws RegistroJugadorException {
        // Recorremos la lista actual de jugadores mediante un bucle for-each (Checkeo manual de duplicados)
        for (Jugador j : listaJugadores) {
            if (j.getIdJugador() == nuevoJugador.getIdJugador()) {
                // Si el ID ya existe, cortamos el flujo lanzando nuestra excepción personalizada
                throw new RegistroJugadorException("El jugador con ID " + nuevoJugador.getIdJugador() + " ya está registrado.");
            }
        }

        // Si supera el bucle sin lanzar la excepción, se añade con éxito a la lista
        listaJugadores.add(nuevoJugador);
        System.out.println("Jugador " + nuevoJugador.getNombre() + " registrado con éxito.");
    }

    /**
     * Agrega e inscribe una pareja en el listado interno del torneo.
     */
    public void registrarPareja(Pareja pareja) {
        listaParejas.add(pareja);
        System.out.println("Pareja '" + pareja.getNombrePareja() + "' inscrita correctamente.");
    }

    /**
     * Añade un partido estructurado al calendario de la competición.
     * Se apoya en el encadenamiento de métodos (Getters de Partido que devuelven objetos Pareja).
     */
    public void programarPartido(Partido partido) {
        calendarioPartidos.add(partido);
        // Imprime un reporte limpio leyendo de forma dinámica el nombre de las parejas involucradas y la jornada actual
        System.out.println("Partido programado: " + partido.getParejaLocal().getNombrePareja() +
                " VS " + partido.getParejaVisitante().getNombrePareja() + " (Jornada " + partido.getJornada() + ")");
    }

    // --- MÉTODOS GETTERS ---
    // Permiten a otras clases o vistas del sistema consultar las listas en memoria a modo de "tablas de consulta"
    public List<Jugador> getListaJugadores() { return listaJugadores; }
    public List<Pareja> getListaParejas() { return listaParejas; }
    public List<Partido> getCalendarioPartidos() { return calendarioPartidos; }
}
