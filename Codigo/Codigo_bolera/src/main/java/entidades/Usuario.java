package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios") // Se asocia explícitamente con la tabla 'usuarios'
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Nombre exacto de la columna en AWS
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100) // Recuerda: VARCHAR(100) para BCrypt
    private String password;

    public Usuario() {}

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters...
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}