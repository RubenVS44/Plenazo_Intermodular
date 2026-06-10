package entidades;

import jakarta.persistence.*;

/**
 * Entidad JPA que mapea la tabla de posiciones y rendimiento de la clasificación del torneo.
 */
@Entity
@Table(name = "resultados_clasificacion") // Sincronizado con la estructura de la tabla correspondiente en BD
public class ResultadoClasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "puntos") // Puntos acumulados por la pareja/jugador
    private Integer puntos;

    @Column(name = "posicion") // Puesto que ocupa en el ranking
    private Integer posicion;

    // Método toString() personalizado para debuguear y pintar fácilmente los datos de la clasificación por consola
    @Override
    public java.lang.String toString() {
        return "ResultadoClasificacion(id=" + this.getId() + ", puntos=" + this.getPuntos() + ", posicion=" + this.getPosicion() + ")";
    }

    // Constructor por defecto requerido por el motor ORM (JPA)
    public ResultadoClasificacion() {}

    // Constructor completo para instanciar clasificaciones rápidamente
    public ResultadoClasificacion(Integer puntos, Integer posicion) {
        this.puntos = puntos;
        this.posicion = posicion;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getPuntos() { return puntos; }
    public void setPuntos(Integer puntos) { this.puntos = puntos; }

    public Integer getPosicion() { return posicion; }
    public void setPosicion(Integer posicion) { this.posicion = posicion; }
}
