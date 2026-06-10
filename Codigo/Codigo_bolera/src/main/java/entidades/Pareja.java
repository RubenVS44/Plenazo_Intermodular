package entidades;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa a una pareja de juego en el torneo de la bolera.
 * Se mapea directamente con la tabla 'parejas'.
 */
@Entity
@Table(name = "parejas") // Vincula explícitamente esta clase con la tabla 'parejas' de la base de datos
public class Pareja {

    @Id // Define este atributo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la clave como autoincremental (SERIAL / AUTO_INCREMENT)
    @Column(name = "id") // Mapea con la columna física llamada 'id'
    private Long id;

    // Configura la columna de texto con un límite de 100 caracteres
    @Column(name = "nombre_pareja", length = 100)
    private String nombrePareja;

    // Constructor vacío obligatorio. Requisito indispensable para que JPA pueda reconstruir el objeto mediante Reflection
    public Pareja() {}

    // Constructor de conveniencia para instanciar la pareja con su nombre directamente
    public Pareja(String nombrePareja) {
        this.nombrePareja = nombrePareja;
    }

    // --- MÉTODOS GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombrePareja() { return nombrePareja; }
    public void setNombrePareja(String nombrePareja) { this.nombrePareja = nombrePareja; }
}