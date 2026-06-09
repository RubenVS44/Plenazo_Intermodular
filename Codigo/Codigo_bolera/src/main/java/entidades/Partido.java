package entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "resultado", length = 50)
    private String resultado;

    @Column(name = "jornada")
    private int jornada;

    // Mapeamos las parejas como objetos para que funcione el encadenamiento de métodos
    // Usamos @Transient temporalmente para que JPA no te pida configurar relaciones complejas todavía
    @Transient
    private Pareja parejaLocal;

    @Transient
    private Pareja parejaVisitante;

    // Constructor vacío obligatorio para JPA
    public Partido() {}

    // --- LOS GETTERS QUE TE PIDE GESTIONTORNEO ---

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

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    // --- OTROS GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getResultados() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
}