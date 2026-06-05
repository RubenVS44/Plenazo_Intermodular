package Codigo;

import java.time.LocalDateTime;

public class ReservaPista {

    private int idReserva;
    private LocalDateTime fechaHora;
    private Pista pista;
    private boolean activa; // Atributo para controlar el estado de la reserva

    // Constructor modificado para incluir la Pista obligatoriamente al reservar
    public ReservaPista(int idReserva, LocalDateTime fechaHora, Pista pista) {
        this.idReserva = idReserva;
        this.fechaHora = fechaHora;
        this.pista = pista;
        this.activa = true; // Toda reserva nace activa
    }

    // Getters y Setters
    public int getIdReserva() {
        return idReserva;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public boolean isActiva() {
        return activa;
    }

    // Lógica para cancelar la reserva
    public boolean cancelarReserva() {
        if (this.activa) {
            this.activa = false;
            System.out.println("La reserva con ID " + idReserva + " ha sido cancelada con éxito.");
            return true;
        } else {
            System.out.println("La reserva ya se encontraba cancelada.");
            return false;
        }
    }
}
