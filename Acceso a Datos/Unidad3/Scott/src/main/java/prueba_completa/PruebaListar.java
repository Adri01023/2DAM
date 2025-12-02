package prueba_completa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaListar {
	public static void main(String[] args) {
	        EntityManagerFactory emf =
	                Persistence.createEntityManagerFactory("UnidadEmpleados");
	        EntityManager em = emf.createEntityManager();
	        try {
	            em.getTransaction().begin(); 
	            // Consulta JPQL moderna
	            List<Empleado> empleados = em
	                    .createQuery("SELECT e FROM Empleado e", Empleado.class)
	                    .getResultList();
	            for (Empleado e : empleados) {
	            	for (Proyecto p : e.getProyectos()) {}
	            	System.out.println(e);
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
