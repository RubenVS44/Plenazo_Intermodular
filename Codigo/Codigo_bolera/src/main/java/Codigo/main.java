package Codigo;

import Excepciones.RegistroJugadorException;
import Excepciones.JugadorDuplicadoException;
import Excepciones.PistaOcupadaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        List<Jugador> listaJugadores = new ArrayList<>();
        List<Pareja> listaParejas = new ArrayList<>();
        List<ReservaPista> historialReservas = new ArrayList<>();

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n=======================================");
            System.out.println("   TORNEO DE BOLOS - VALIDACIONES OK   ");
            System.out.println("=======================================");
            System.out.println("1. Registrar Jugador");
            System.out.println("2. Registrar Pareja (Evitar Jugador Duplicado)");
            System.out.println("3. Reservar Pista y Jugar (Evitar Pista Ocupada)");
            System.out.println("4. Mostrar Datos Registrados");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR NUEVO JUGADOR ---");
                    try {
                        System.out.print("Introduce ID (Número): ");
                        int id = Integer.parseInt(teclado.nextLine());

                        for (Jugador j : listaJugadores) {
                            if (j.getIdJugador() == id) {
                                throw new RegistroJugadorException("¡Error! Ya existe un jugador con el ID " + id);
                            }
                        }

                        System.out.print("Introduce Nombre: ");
                        String nom = teclado.nextLine();
                        System.out.print("Introduce Apellido 1: ");
                        String ap1 = teclado.nextLine();
                        System.out.print("Introduce Apellido 2: ");
                        String ap2 = teclado.nextLine();
                        System.out.print("Introduce Categoría: ");
                        String cat = teclado.nextLine();

                        listaJugadores.add(new Jugador(id, nom, ap1, ap2, cat));
                        System.out.println("¡Jugador registrado con éxito!");

                    } catch (RegistroJugadorException e) {
                        System.err.println("EXCEPCIÓN: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error genérico: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n--- REGISTRAR PAREJA ---");
                    if (listaJugadores.size() < 2) {
                        System.out.println("No hay suficientes jugadores en el sistema.");
                        break;
                    }
                    try {
                        System.out.print("Introduce ID de la Pareja: ");
                        int idPareja = Integer.parseInt(teclado.nextLine());
                        System.out.print("Introduce Nombre de la Pareja: ");
                        String nombreP = teclado.nextLine();

                        System.out.println("Jugadores disponibles:");
                        for (int i = 0; i < listaJugadores.size(); i++) {
                            System.out.println(i + ". " + listaJugadores.get(i).getNombre());
                        }

                        System.out.print("Seleccione el índice del Jugador 1: ");
                        int idx1 = Integer.parseInt(teclado.nextLine());
                        System.out.print("Seleccione el índice del Jugador 2: ");
                        int idx2 = Integer.parseInt(teclado.nextLine());

                        if (idx1 == idx2) {
                            throw new IllegalArgumentException("Una pareja debe estar compuesta por dos personas distintas.");
                        }

                        Jugador j1 = listaJugadores.get(idx1);
                        Jugador j2 = listaJugadores.get(idx2);

                        // VALIDACIÓN AVANZADA: Verificar si alguno ya está en otra pareja
                        for (Pareja p : listaParejas) {
                            if (p.getJugador1().getIdJugador() == j1.getIdJugador() || p.getJugador2().getIdJugador() == j1.getIdJugador()) {
                                throw new JugadorDuplicadoException("¡Error de negocio! " + j1.getNombre() + " ya juega en la pareja '" + p.getNombrePareja() + "'");
                            }
                            if (p.getJugador1().getIdJugador() == j2.getIdJugador() || p.getJugador2().getIdJugador() == j2.getIdJugador()) {
                                throw new JugadorDuplicadoException("¡Error de negocio! " + j2.getNombre() + " ya juega en la pareja '" + p.getNombrePareja() + "'");
                            }
                        }

                        listaParejas.add(new Pareja(idPareja, nombreP, j1, j2));
                        System.out.println("¡Pareja '" + nombreP + "' registrada con éxito!");

                    } catch (JugadorDuplicadoException e) {
                        System.err.println("EXCEPCIÓN: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\n--- SIMULAR PARTIDO Y RESERVA ---");
                    if (listaParejas.size() < 2) {
                        System.out.println("Se necesitan al menos 2 parejas registradas.");
                        break;
                    }

                    try {
                        System.out.print("Introduce el número de pista que deseas usar (ej. 1, 2, 3): ");
                        int numPista = Integer.parseInt(teclado.nextLine());

                        // Simulamos una fecha fija para la prueba (por ejemplo, hoy)
                        LocalDateTime fechaPartido = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);

                        // VALIDACIÓN AVANZADA: Comprobar si la pista ya está reservada a esa hora
                        for (ReservaPista r : historialReservas) {
                            if (r.getPista().getNumeroPista() == numPista && r.getFechaHora().equals(fechaPartido) && r.isActiva()) {
                                throw new PistaOcupadaException("¡Conflicto de reserva! La pista " + numPista + " ya está ocupada para la hora seleccionada.");
                            }
                        }

                        // Si no saltó la excepción, procedemos de forma segura
                        Pista pistaSeleccionada = new Pista(numPista, numPista, "Ocupada");
                        ReservaPista nuevaReserva = new ReservaPista(historialReservas.size() + 1, fechaPartido, pistaSeleccionada);
                        historialReservas.add(nuevaReserva);

                        Pareja local = listaParejas.get(0);
                        Pareja visitante = listaParejas.get(1);
                        Partido partido = new Partido(1, 1, local, visitante, nuevaReserva);

                        partido.iniciarPartido();
                        System.out.print("Introduce bolos ganados por " + local.getNombrePareja() + ": ");
                        int bL = Integer.parseInt(teclado.nextLine());
                        System.out.print("Introduce bolos ganados por " + visitante.getNombrePareja() + ": ");
                        int bV = Integer.parseInt(teclado.nextLine());
                        partido.finalizarPartido();

                        if (bL > bV) {
                            System.out.println("¡Ganador: " + local.getNombrePareja() + "!");
                        } else if (bV > bL) {
                            System.out.println("¡Ganador: " + visitante.getNombrePareja() + "!");
                        } else {
                            System.out.println("Empate.");
                        }

                    } catch (PistaOcupadaException e) {
                        System.err.println("EXCEPCIÓN: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\n--- DATOS ACTUALES ---");
                    System.out.println("Jugadores registrados: " + listaJugadores.size());
                    System.out.println("Parejas registradas: " + listaParejas.size());
                    System.out.println("Reservas activas de pista: " + historialReservas.size());
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        }
    }
}