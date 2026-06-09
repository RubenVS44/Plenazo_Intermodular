package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "pistas") // Se asocia con tu tabla 'pistas'
public class Pista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pista")
    private Integer numeroPista;

    @Column(name = "estado")
    private String estado;

    public Pista() {}
    // Getters, Setters y Constructores...
}
