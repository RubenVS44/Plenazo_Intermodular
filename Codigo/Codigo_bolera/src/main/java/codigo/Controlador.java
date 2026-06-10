package codigo;

import entidades.Usuario;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

/**
 * Clase controladora que interactúa directamente con JPA (Hibernate) para
 * gestionar la persistencia y la seguridad de los usuarios en la base de datos de AWS.
 */
public class Controlador {

    // Se encarga de leer el archivo persistence.xml y gestionar la fábrica de conexiones bajo la unidad "UsuariosPU"
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosPU");

    /**
     * Registra un nuevo usuario aplicando un hash seguro a la contraseña antes de guardarlo.
     * @param username Nombre del usuario a registrar
     * @param password Contraseña en texto plano
     * @return true si se guardó correctamente, false si hubo un error o duplicado
     */
    public boolean registrarUsuario(String username, String password) {
        // Creamos el gestor de entidades para esta operación concreta (abre conexión)
        EntityManager em = emf.createEntityManager();
        try {
            // Iniciamos una transacción de base de datos (necesaria para operaciones de escritura: INSERT/UPDATE/DELETE)
            em.getTransaction().begin();

            // Ciframos la contraseña usando BCrypt agregando un 'salt' aleatorio para evitar ataques de diccionario
            String passwordHaseada = BCrypt.hashpw(password, BCrypt.gensalt());

            // Creamos la instancia del objeto entidad con los datos ya listos
            Usuario nuevo = new Usuario(username, passwordHaseada);

            // Le pasamos el objeto a JPA. Hibernate se encarga de transformarlo en un INSERT SQL para AWS
            em.persist(nuevo);

            // Confirmamos la transacción para aplicar de forma permanente los cambios en la BD
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            // Si algo falla, revertimos la transacción (Rollback) para evitar dejar la BD en un estado corrupto o incompleto
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        } finally {
            // Pase lo que pase, cerramos el EntityManager para liberar los recursos y la conexión del pool
            em.close();
        }
    }

    /**
     * Valida el inicio de sesión comparando el username y el hash de la contraseña en la base de datos.
     * @return true si el usuario existe y la contraseña coincide, false en caso contrario
     */
    public boolean iniciarSesion(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            // Creamos una consulta tipada usando JPQL (Java Persistence Query Language).
            // Apuntamos a la clase Entidad 'Usuario' y a su propiedad 'username' en lugar de usar tablas SQL puras.
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :user", Usuario.class);

            // Seteamos de forma segura el parámetro posicional para evitar inyección SQL
            query.setParameter("user", username);

            // Ejecutamos la consulta y obtenemos la lista de registros coincidentes
            List<Usuario> resultados = query.getResultList();

            // Si la lista contiene elementos, significa que el username existe
            if (!resultados.isEmpty()) {
                Usuario usuarioBD = resultados.get(0); // Extraemos el primer (y único) registro encontrado

                // Comparamos la contraseña introducida con el hash seguro que guardamos en la BD
                return BCrypt.checkpw(password, usuarioBD.getPassword());
            }
            return false; // El usuario no existe en el sistema
        } finally {
            em.close(); // Liberamos el EntityManager
        }
    }

    /**
     * Cierra de forma segura la fábrica de conexiones de JPA al finalizar la aplicación.
     */
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    /**
     * Constructor del controlador. Se ejecuta al iniciar y sirve para diagnosticar
     * qué clases entidad han sido mapeadas correctamente por el framework.
     */
    public Controlador() {
        System.out.println("=== LOG DE CLASES JPA DETECTADAS ===");
        try {
            // Recorremos el metamodelo de JPA para auditar por consola qué entidades están correctamente configuradas
            emf.getMetamodel().getEntities().forEach(entity -> {
                System.out.println("[JPA MODEL] Clase cargada con éxito: " + entity.getName());
            });
        } catch (Exception e) {
            System.out.println("[ERROR LOG] No se pudieron escanear las clases: " + e.getMessage());
        }
        System.out.println("====================================\n");
    }
}
