package prueba_completa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaUpdate {
	public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadEmpleados");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empleado yo = em.find(Empleado.class, 2);
            yo.setJob("Nini");
            em.persist(new Departamento("Casa Ruben","Torre Arias"));
            yo.setNumdept(em.find(Departamento.class, 3));
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
