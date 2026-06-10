package entidades;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa las credenciales y cuentas de acceso de los usuarios del sistema.
 */
@Entity
@Table(name = "usuarios") // Enlazado con la tabla 'usuarios' alojada en la infraestructura Cloud (AWS)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Clave primaria autoincremental única por usuario
    private Long id;

    // Restricciones de base de datos: el username no se puede repetir (unique) ni quedar vacío (nullable = false)
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    // Longitud configurada a 100 para admitir de manera holgada hashes de encriptación seguros como BCrypt
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    // Constructor vacío por especificación obligatoria de JPA
    public Usuario() {}

    // Constructor para inicialización de instancias durante el proceso de registro
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}