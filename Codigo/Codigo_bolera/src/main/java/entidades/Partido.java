package entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad JPA que representa los partidos disputados en la bolera.
 */
@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Almacena la fecha del partido usando la API moderna de Java (mapea a DATE de SQL)
    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "resultado", length = 50)
    private String resultado;

    @Column(name = "jornada")
    private int jornada;

    // @Transient le dice a JPA que ignore por completo estos atributos en la base de datos.
    // Se usan temporalmente como objetos en memoria para que funcione el encadenamiento de métodos sin configurar @ManyToOne
    @Transient
    private Pareja parejaLocal;

    @Transient
    private Pareja parejaVisitante;

    // Constructor vacío obligatorio para el framework JPA
    public Partido() {}

    // --- MÉTODOS REQUERIDOS POR GESTIONTORNEO (GETTERS Y SETTERS PARA OBJETOS EN MEMORIA) ---

    public Pareja getParejaLocal() { return parejaLocal; }
    public void setParejaLocal(Pareja parejaLocal) { this.parejaLocal = parejaLocal; }

    public Pareja getParejaVisitante() { return parejaVisitante; }
    public void setParejaVisitante(Pareja parejaVisitante) { this.parejaVisitante = parejaVisitante; }

    public int getJornada() { return jornada; }
    public void setJornada(int jornada) { this.jornada = jornada; }

    // --- OTROS GETTERS Y SETTERS ESTÁNDAR ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    // Corrige sutilmente el nombre o retorna el atributo "resultado" mapeado arriba
    public String getResultados() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
}