package entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad JPA encargada del histórico o control de reservas de pistas.
 */
@Entity
@Table(name = "reservas_pistas") // Se enlaza con el nombre exacto definido en el motor de bases de datos
public class ReservaPista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // LocalDateTime almacena tanto la fecha como la hora exacta.
    // Es ideal para mapearse con tipos DATETIME o TIMESTAMP de MySQL
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    // Constructor vacío obligatorio para JPA
    public ReservaPista() {}

    // Constructor rápido para generar una reserva pasando fecha y hora
    public ReservaPista(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
