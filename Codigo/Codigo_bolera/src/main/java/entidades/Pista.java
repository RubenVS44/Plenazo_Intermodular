package entidades;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa las pistas físicas de la bolera.
 */
@Entity
@Table(name = "pistas") // Mapeado con la tabla física 'pistas'
public class Pista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pista") // Clave primaria basada en el número identificador de la pista
    private Integer numeroPista;

    @Column(name = "estado") // Almacena si la pista está "Libre", "Ocupada", "Mantenimiento", etc.
    private String estado;

    // Constructor vacío obligatorio para JPA
    public Pista() {}

    // Nota: Aquí se añadirían los Getters, Setters y Constructores adicionales según necesidades.
}
