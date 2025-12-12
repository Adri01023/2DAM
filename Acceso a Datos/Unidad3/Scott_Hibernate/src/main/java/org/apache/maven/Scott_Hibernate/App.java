package org.apache.maven.Scott_Hibernate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import prueba_Hibernate.Emp;
import prueba_Hibernate.POJO_Emp_Sal;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {
	
	private static Session sesion;
	private static Transaction tx;
	
	public static void main( String[] args ) {
		// La siguiente línea elimina los mensajes informativos en consola
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		SessionFactory singleton = new
		Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		sesion = singleton.openSession();
		tx =sesion.getTransaction();
		tx.begin();
		try {
			
			Query<Emp> query1 = sesion.createQuery("FROM Emp e WHERE e.dept.loc = :localizacion", Emp.class);
			query1.setParameter("localizacion", "Barcelona");
			List<Emp> list1 = (List<Emp>) query1.list();
			System.out.println(list1);
			//Query<Emp> query1 = sesion.getNamedQuery("empleadosCiertaInfo");
			//List<Emp> list1 = query1.list();
			// PROBLEMA TAMBIÉN CON CONSULTAS SIMPLES
			
			Query query = sesion.createQuery("SELECT new prueba_Hibernate.POJO_Emp_Sal(e.ename, s.quantity) FROM Emp e JOIN e.sals s ORDER BY s.quantity DESC", POJO_Emp_Sal.class);
			// SI LA QUERY DE ARRIBA NO LA PONES CON NEW POJO INTENTARÁ BUSCAR UN CONSTRUCTOR DE EMP Y DARÁ FALLO
			// CURIOSIDAD LA QUERY ALMACENADA EN EMP.XML ES PROCESADA ANTES DE ENTRAR A LA SESIÓN OSEA SI 
			// LA QUERY DEL XML NO ES CORRECTA SE PRODUCIRÁ UNA EXCEPCIÓN ANTES DE EJECUTAR CUALQUIER COSA
			List<POJO_Emp_Sal> list = (List<POJO_Emp_Sal>) query.list();
			System.out.println("Query del JOIN: " + list);
			/* INTENTO QUE VIMOS EN CLASE ERROR: java.lang.IllegalArgumentException: No query named 'empleadosPorSalario'
			Query<Emp> query = sesion.createNamedQuery("empleadosPorSalario", Emp.class);
		    List<Emp> list= (List<Emp>) query.getResultList();
		    System.out.println(list);*/
		tx.commit();
		}catch (HibernateException e) {
		e.printStackTrace();
		} finally {
		sesion.close();
		singleton.close();
		}
	}
}
