package Codigo;

import java.util.HashMap;
import java.util.Map;

public class clasificacion {

    // Usamos un HashMap donde la clave es la Pareja y el valor son sus puntos totales
    private Map<Pareja, Integer> tablaPuntos;

    public clasificacion() {
        this.tablaPuntos = new HashMap<>();
    }

    // Inicializa o añade una pareja a la tabla con 0 puntos si no existía
    public void registrarParejaEnTabla(Pareja pareja) {
        if (!tablaPuntos.containsKey(pareja)) {
            tablaPuntos.put(pareja, 0);
        }
    }

    // Suma puntos a una pareja específica
    public void sumarPuntos(Pareja pareja, int puntosASumar) {
        if (pareja != null) {
            // Si la pareja no estaba registrada por error, la añadimos primero
            registrarParejaEnTabla(pareja);
            int puntosActuales = tablaPuntos.get(pareja);
            tablaPuntos.put(pareja, puntosActuales + puntosASumar);
        }
    }

    // Muestra por consola la tabla ordenada de posiciones
    public void mostrarTabla() {
        System.out.println("\n--- TABLA DE CLASIFICACIÓN GENERAL ---");
        System.out.printf("%-20s | %s\n", "Pareja", "Puntos");
        System.out.println("---------------------------------------");

        // Ordenamos las entradas del mapa de mayor a menor puntuación antes de imprimir
        tablaPuntos.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entrada -> {
                    System.out.printf("%-20s | %d pts\n",
                            entrada.getKey().getNombrePareja(),
                            entrada.getValue());
                });
        System.out.println("---------------------------------------");
    }
}