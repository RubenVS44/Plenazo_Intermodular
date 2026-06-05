package Codigo;

public class ResultadoClasificacion {

    private int idResultado;
    private int bolosLocales;
    private int bolosVisitantes;
    private int puntosParejaLocal;
    private int puntosParejaVisitante;
    private Partido partido;

    public ResultadoClasificacion(int idResultado, int bolosLocales, int bolosVisitantes,
                                  int puntosParejaLocal, int puntosParejaVisitante, Partido partido) {
        this.idResultado = idResultado;
        this.bolosLocales = bolosLocales;
        this.bolosVisitantes = bolosVisitantes;
        this.puntosParejaLocal = puntosParejaLocal;
        this.puntosParejaVisitante = puntosParejaVisitante;
        this.partido = partido;
    }

    public Pareja calcularGanador() {
        if (partido == null) return null;
        if (bolosLocales > bolosVisitantes) {
            return partido.getParejaLocal();
        } else if (bolosVisitantes > bolosLocales) {
            return partido.getParejaVisitante();
        }
        return null; // Empate
    }

    public void actualizarClasificacion() {
        Pareja ganador = calcularGanador();
        if (ganador != null) {
            System.out.println("El ganador del encuentro es: " + ganador.getNombrePareja());
        } else {
            System.out.println("El encuentro ha quedado en empate.");
        }
    }
}
