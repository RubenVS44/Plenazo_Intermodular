package Codigo;

import Excepciones.RegistroJugadorException;

public class Jugador {
    private int id_jugador;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private int puntuacion;

    public Jugador(int id_jugador, String nombre, String apellidos, String correo, String telefono) {
        this.id_jugador = id_jugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getIdJugador() {
        return id_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public boolean registrarJugador() throws RegistroJugadorException {
        // Lógica de registro del jugador
        if (this.nombre == null || this.nombre.trim().isEmpty()) {
            throw new RegistroJugadorException("El nombre del jugador no puede estar vacío.");
        }
        if (this.correo == null || !this.correo.contains("@")) {
            throw new RegistroJugadorException("El correo electrónico proporcionado no es válido.");
        }
        if (this.telefono == null || this.telefono.trim().length() < 9) {
            throw new RegistroJugadorException("El teléfono debe tener un formato válido (mínimo 9 dígitos).");
        }

        return true;
    }
}
