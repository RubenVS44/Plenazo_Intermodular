package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador") // Como se llame en tu BD de AWS
    private Long idJugador;      // ¡Ojo al nombre exacto en camello!

    @Column(name = "nombre")
    private String nombre;

    public Jugador() {}

    // --- EL GETTER QUE TE PIDE EL ERROR ---
    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    // --- EL OTRO GETTER QUE DA ERROR EN LA LÍNEA 37 ---
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}