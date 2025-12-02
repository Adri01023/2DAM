package prueba_completa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaUpdate {
	public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadEmpleados");
        EntityManager em = emf.createEntityManager();
        Proyecto proyecto = new Proyecto("AVENTADOR");
        try {
            em.getTransaction().begin();
            Empleado yo = em.find(Empleado.class, 2);
            em.persist(proyecto);
            yo.addProyecto(proyecto);
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
