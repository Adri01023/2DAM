package prueba_completa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaCrear {
	public static void main(String[] args) {
		Salario sal1 = new Salario(10, 1000);
		Salario sal2 = new Salario(20, 2000);
		Departamento depa1 = new Departamento("IT", "Madrid");
		Departamento depa2 = new Departamento("Filetes", "Madrid");
		Empleado yo = new Empleado("Adrián Martín", "Programador", sal2, depa1);
		Empleado tu = new Empleado("Ruben Escarpa", "Filete", yo, sal1, depa2);
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadEmpleados");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(sal1);
            em.persist(sal2);
            em.persist(depa1);
            em.persist(depa2);
            em.persist(yo);
            em.persist(tu);
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
