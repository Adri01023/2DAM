package prueba_completa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaDelete {
	public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadEmpleados");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Departamento depa = em.find(Departamento.class, 2);
            em.remove(depa);
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
