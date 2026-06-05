package Codigo;

public class Pareja {
    private int idPareja;
    private String nombrePareja;
    private Jugador jugador1;
    private Jugador jugador2;

    // Constructor modificado para recibir también a los dos jugadores
    public Pareja(int idPareja, String nombrePareja, Jugador jugador1, Jugador jugador2) {
        this.idPareja = idPareja;
        this.nombrePareja = nombrePareja;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    // Getters y Seters
    public int getIdPareja() {
        return idPareja;
    }

    public String getNombrePareja() {
        return nombrePareja;
    }

    public void setNombrePareja(String nombrePareja) {
        this.nombrePareja = nombrePareja;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    // Implementación lógica del método
    public int calcularPuntuacionTotal() {
        // Validamos que los jugadores no sean nulos para evitar un NullPointerException
        if (jugador1 != null && jugador2 != null) {
            // Asumiendo que la clase Jugador tiene un método getPuntuacion()
            return jugador1.getPuntuacion() + jugador2.getPuntuacion();
        }else{
            return 0;
        }
    }
}
