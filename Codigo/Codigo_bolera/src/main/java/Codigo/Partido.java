package Codigo;

public class Partido {

    private int idPartido;
    private int jornada;
    private Pareja parejaLocal;
    private Pareja parejaVisitante;
    private ReservaPista reserva;
    private boolean enCurso; // Atributo extra útil para controlar el estado del partido

    // Constructor modificado: Un partido no puede existir sin equipos ni una pista reservada
    public Partido(int idPartido, int jornada, Pareja parejaLocal, Pareja parejaVisitante, ReservaPista reserva) {
        this.idPartido = idPartido;
        this.jornada = jornada;
        this.parejaLocal = parejaLocal;
        this.parejaVisitante = parejaVisitante;
        this.reserva = reserva;
        this.enCurso = false; // El partido empieza sin jugarse
    }

    // Getters y Setters
    public int getIdPartido() {
        return idPartido;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public Pareja getParejaLocal() {
        return parejaLocal;
    }

    public void setParejaLocal(Pareja parejaLocal) {
        this.parejaLocal = parejaLocal;
    }

    public Pareja getParejaVisitante() {
        return parejaVisitante;
    }

    public void setParejaVisitante(Pareja parejaVisitante) {
        this.parejaVisitante = parejaVisitante;
    }

    public ReservaPista getReserva() {
        return reserva;
    }

    public void setReserva(ReservaPista reserva) {
        this.reserva = reserva;
    }

    // Lógica de control de partido
    public void iniciarPartido() {
        if (!enCurso) {
            this.enCurso = true;
            System.out.println("El partido " + idPartido + " de la jornada " + jornada + " ha comenzado.");
        } else {
            System.out.println("El partido ya está en curso.");
        }
    }

    public void finalizarPartido() {
        if (enCurso) {
            this.enCurso = false;
            System.out.println("El partido " + idPartido + " ha finalizado.");
            // Aquí podrías llamar a métodos para registrar el resultado final
        } else {
            System.out.println("El partido no ha iniciado o ya había finalizado.");
        }
    }
}
