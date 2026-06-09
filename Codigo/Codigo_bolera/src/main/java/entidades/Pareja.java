package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "parejas") // Vincula con la tabla 'parejas' de tu bolera
public class Pareja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Clave primaria autoincremental
    private Long id;

    @Column(name = "nombre_pareja", length = 100)
    private String nombrePareja;

    // Constructores obligatorios para JPA
    public Pareja() {}

    public Pareja(String nombrePareja) {
        this.nombrePareja = nombrePareja;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombrePareja() { return nombrePareja; }
    public void setNombrePareja(String nombrePareja) { this.nombrePareja = nombrePareja; }
}