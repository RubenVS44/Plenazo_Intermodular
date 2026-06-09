package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "resultados_clasificacion") // Nombre exacto de la última tabla de tu imagen
public class ResultadoClasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "puntos")
    private Integer puntos;

    @Column(name = "posicion")
    private Integer posicion;

    public java.lang.String toString() {
        return "ResultadoClasificacion(id=" + this.getId() + ", puntos=" + this.getPuntos() + ", posicion=" + this.getPosicion() + ")";
    }

    public ResultadoClasificacion() {}

    public ResultadoClasificacion(Integer puntos, Integer posicion) {
        this.puntos = puntos;
        this.posicion = posicion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getPuntos() { return puntos; }
    public void setPuntos(Integer puntos) { this.puntos = puntos; }

    public Integer getPosicion() { return posicion; }
    public void setPosicion(Integer posicion) { this.posicion = posicion; }
}
