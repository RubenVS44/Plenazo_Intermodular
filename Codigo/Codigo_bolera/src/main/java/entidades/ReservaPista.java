package entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas_pistas") // Nombre exacto que aparece en tu phpMyAdmin
public class ReservaPista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora; // Mapea con columnas tipo DATETIME/TIMESTAMP de MySQL

    public ReservaPista() {}

    public ReservaPista(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
