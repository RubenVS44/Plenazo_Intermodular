package Inicio;

import codigo.Controlador;
import java.util.Scanner;

/**
 * Clase principal que actúa como punto de entrada de la aplicación de la bolera.
 * Gestiona la interfaz de usuario por consola para el registro y login.
 */
public class Main {
    public static void main(String[] args) {
        // Instanciamos el controlador que maneja la lógica de persistencia (JPA) y la conexión a AWS
        Controlador controlador = new Controlador();
        // Scanner para capturar las entradas de texto del usuario por la consola
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BIENVENIDO A LA BOLERA PLENAZO ===");

        // Bucle infinito para mantener el menú activo hasta que el usuario decida salir
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("> ");

            // Leer la opción seleccionada por el usuario
            String opcion = scanner.nextLine();

            // Si la opción es 3, se rompe el bucle para finalizar el programa
            if (opcion.equals("3")) {
                System.out.println("Saliendo de la aplicación...");
                break;
            }

            // Validación de entrada: si no es ni 1 ni 2, vuelve a saltar al inicio del bucle
            if (!opcion.equals("1") && !opcion.equals("2")) {
                System.out.println("[ERROR] Opción no válida. Pruebe de nuevo.");
                continue;
            }

            // Bloque común: Tanto para registrar como para loguear se solicitan las mismas credenciales
            System.out.print("Introduce nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("Introduce contraseña: ");
            String password = scanner.nextLine();

            // Estructura condicional moderna (Switch Expression) según la opción elegida
            switch (opcion) {
                case "1" -> {
                    System.out.println("Conectando con AWS para registrar...");
                    // Llama al método del controlador para persistir el usuario
                    boolean registrado = controlador.registrarUsuario(username, password);

                    if (registrado) {
                        System.out.println("[ÉXITO] Usuario guardado de forma segura en 'bolera_plenazo'.");
                    } else {
                        System.out.println("[ERROR] El usuario ya existe o no hay conexión con el servidor.");
                    }
                }
                case "2" -> {
                    System.out.println("Verificando credenciales...");
                    // Llama al controlador para validar el usuario y contraseña contra la base de datos
                    boolean accesoConcedido = controlador.iniciarSesion(username, password);

                    if (accesoConcedido) {
                        System.out.println("[ACCESO CONCEDIDO] ¡Bienvenido, " + username + "!");
                        // Espacio reservado para redirigir al flujo o menú principal de la bolera
                    } else {
                        System.out.println("[DENEGADO] El usuario o la contraseña no son correctos.");
                    }
                }
            }
        }

        // Bloque de cierre: Limpieza y liberación de recursos al salir del bucle
        scanner.close(); // Cierra el flujo de lectura de consola
        controlador.cerrar(); // Cierra el Entity Manager Factory de JPA para liberar la conexión con AWS
        System.out.println("Conexión con AWS cerrada correctamente.");
    }
}