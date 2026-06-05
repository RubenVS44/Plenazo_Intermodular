package Codigo;

import java.util.ArrayList;
import java.util.List;
import Excepciones.RegistroJugadorException;

public class gestionTorneo {

    // PERSISTENCIA EN MEMORIA: Listas para almacenar los datos del torneo
    private List<Jugador> listaJugadores;
    private List<Pareja> listaParejas;
    private List<Partido> calendarioPartidos;
    private List<ReservaPista> historialReservas;

    public gestionTorneo() {
        this.listaJugadores = new ArrayList<>();
        this.listaParejas = new ArrayList<>();
        this.calendarioPartidos = new ArrayList<>();
        this.historialReservas = new ArrayList<>();
    }

    // LÓGICA CON EXCEPCIONES: Registrar un jugador controlando duplicados
    public void registrarJugador(Jugador nuevoJugador) throws RegistroJugadorException {
        // Validamos si el ID del jugador ya existe en nuestra "base de datos"
        for (Jugador j : listaJugadores) {
            if (j.getIdJugador() == nuevoJugador.getIdJugador()) {
                throw new RegistroJugadorException("El jugador con ID " + nuevoJugador.getIdJugador() + " ya está registrado.");
            }
        }

        listaJugadores.add(nuevoJugador);
        System.out.println("Jugador " + nuevoJugador.getNombre() + " registrado con éxito.");
    }

    // Agregar una pareja al torneo
    public void registrarPareja(Pareja pareja) {
        listaParejas.add(pareja);
        System.out.println("Pareja '" + pareja.getNombrePareja() + "' inscrita correctamente.");
    }

    // Programar un partido en el calendario
    public void programarPartido(Partido partido) {
        calendarioPartidos.add(partido);
        System.out.println("Partido programado: " + partido.getParejaLocal().getNombrePareja() +
                " VS " + partido.getParejaVisitante().getNombrePareja() + " (Jornada " + partido.getJornada() + ")");
    }

    // Getters para poder consultar las "tablas" de nuestra base de datos
    public List<Jugador> getListaJugadores() { return listaJugadores; }
    public List<Pareja> getListaParejas() { return listaParejas; }
    public List<Partido> getCalendarioPartidos() { return calendarioPartidos; }
}
