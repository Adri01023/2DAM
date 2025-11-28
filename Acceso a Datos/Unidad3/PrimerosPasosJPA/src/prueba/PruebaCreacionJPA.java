package prueba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaCreacionJPA {

    public static void main(String[] args) {

        Persona yo = new Persona("Sergio","Sancho", 21);
        yo.addEmail("Sergio@gmail.com");
        yo.addEmail("Sergio2@gmail.com");
        yo.addEmail("Sergio4@gmail.com");
        Persona tu = new Persona("Gloria", "Serra", 61);
        tu.addEmail("gloriaserra@gmail.com");
        tu.addEmail("Gloria@gmail.com");
        Persona el = new Persona("Mónica", "Yuste", 54);
        el.addEmail("moniyuste@gmail.com");

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadPersonas");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(yo);
            em.persist(tu);
            em.persist(el);
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