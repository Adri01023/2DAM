package prueba;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaListarJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadPersonas");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); 
            // Consulta JPQL moderna
            List<Persona> personas = em
                    .createQuery("SELECT p FROM Persona p", Persona.class)
                    .getResultList();
            for (Persona p : personas) {
            	for (Email mail : p.getEmails()) {}
            	System.out.println("Nombre: " + p.getNombre()+ " Apellido: " + p.getApellidos() + " Edad: " + p.getEdad() + " Emails: " + p.getEmails());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
