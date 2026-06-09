package codigo;

import entidades.Usuario;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

public class Controlador {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosPU");

    // 1. REGISTRAR CUALQUIER ENTIDAD CON JPA PURO
    public boolean registrarUsuario(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            String passwordHaseada = BCrypt.hashpw(password, BCrypt.gensalt());
            Usuario nuevo = new Usuario(username, passwordHaseada);

            em.persist(nuevo); // JPA se encarga de enviarlo a la tabla 'usuarios' de AWS

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    // 2. CONSULTAR DATOS CON JPQL (SQL de objetos de JPA)
    public boolean iniciarSesion(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            // Buscamos apuntando a la Clase 'Usuario' y a su atributo 'username'
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :user", Usuario.class);
            query.setParameter("user", username);

            List<Usuario> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                Usuario usuarioBD = resultados.get(0);
                return BCrypt.checkpw(password, usuarioBD.getPassword());
            }
            return false;
        } finally {
            em.close();
        }
    }
    // Añade este método al final de tu clase Controlador
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    public Controlador() {
        // Al hacer el create, Hibernate mapeará las clases registradas en el persistence.xml
        System.out.println("=== LOG DE CLASES JPA DETECTADAS ===");
        try {
            emf.getMetamodel().getEntities().forEach(entity -> {
                System.out.println("[JPA MODEL] Clase cargada con éxito: " + entity.getName());
            });
        } catch (Exception e) {
            System.out.println("[ERROR LOG] No se pudieron escanear las clases: " + e.getMessage());
        }
        System.out.println("====================================\n");
    }
}
