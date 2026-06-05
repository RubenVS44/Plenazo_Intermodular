package Codigo;

public class Pista {
    private int id_pista;
    private int numero_pista;
    private String estado;

    public Pista(int id_pista, int numero_pista, String estado) {
        this.id_pista = id_pista;
        this.numero_pista = numero_pista;
        this.estado = estado;
    }

    public int getIdPista() { return id_pista; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Si te falta el getter de numero_pista, añádelo aquí:
    public int getNumeroPista() { return numero_pista; }
}
