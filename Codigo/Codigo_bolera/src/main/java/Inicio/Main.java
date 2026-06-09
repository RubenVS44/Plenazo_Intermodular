package Inicio;

import codigo.Controlador;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciamos el controlador que maneja JPA y AWS
        Controlador controlador = new Controlador();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BIENVENIDO A LA BOLERA PLENAZO ===");

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("> ");

            String opcion = scanner.nextLine();

            if (opcion.equals("3")) {
                System.out.println("Saliendo de la aplicación...");
                break;
            }

            if (!opcion.equals("1") && !opcion.equals("2")) {
                System.out.println("[ERROR] Opción no válida. Pruebe de nuevo.");
                continue;
            }

            // Pedimos los datos comunes para ambas opciones
            System.out.print("Introduce nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("Introduce contraseña: ");
            String password = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.println("Conectando con AWS para registrar...");
                    boolean registrado = controlador.registrarUsuario(username, password);

                    if (registrado) {
                        System.out.println("[ÉXITO] Usuario guardado de forma segura en 'bolera_plenazo'.");
                    } else {
                        System.out.println("[ERROR] El usuario ya existe o no hay conexión con el servidor.");
                    }
                }
                case "2" -> {
                    System.out.println("Verificando credenciales...");
                    boolean accesoConcedido = controlador.iniciarSesion(username, password);

                    if (accesoConcedido) {
                        System.out.println("[ACCESO CONCEDIDO] ¡Bienvenido, " + username + "!");
                        // Aquí es donde redirigirías al usuario al menú principal de tu bolera
                    } else {
                        System.out.println("[DENEGADO] El usuario o la contraseña no son correctos.");
                    }
                }
            }
        }

        // Cerramos el scanner y liberamos los recursos de JPA al salir
        scanner.close();
        controlador.cerrar();
        System.out.println("Conexión con AWS cerrada correctamente.");
    }
}