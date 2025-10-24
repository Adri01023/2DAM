package gestionAlumnos;

import gestionAlumnos.Model.Alumno;
import gestionAlumnos.Model.ModeloAlumnosJDBC;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        ModeloAlumnosJDBC modelo = new ModeloAlumnosJDBC();

        Alumno a1 = new Alumno("12345678A", "Ana", "García López", "28001");
        Alumno a2 = new Alumno("87654321B", "Carlos", "Martínez Ruiz", "41002");

        System.out.println("Creando alumnos");
        modelo.crear(a1);
        modelo.crear(a2);

        // Listar todos los alumnos
        System.out.println("Listado de alumnos");
        List<String> alumnos = modelo.getAll();
        for (String al : alumnos) {
            System.out.println(al);
        }

        // Buscar un alumno por DNI
        System.out.println("Buscar alumno con DNI 12345678A");
        Alumno encontrado = modelo.getAlumnoPorDNI("12345678A");
        if (encontrado != null) {
            System.out.println(encontrado.getDNI() + " -- " + encontrado.getNombre() + " -- " + encontrado.getApellidos());
        } else {
            System.out.println("Alumno no encontrado.");
        }

        // Modificar un alumno
        System.out.println("Modificando alumno 12345678A");
        if (encontrado != null) {
            encontrado.setNombre("Ana María");
            encontrado.setCP("28002");
            boolean modificado = modelo.modificarAlumno(encontrado);
            System.out.println("Alumno modificado: " + modificado);
        }

        // Eliminar un alumno
        System.out.println("Eliminando alumno con DNI 87654321B");
        boolean eliminado = modelo.eliminarAlumno("87654321B");
        System.out.println("Alumno eliminado: " + eliminado);

        //Mostrar lista 
        System.out.println("Lista final de alumnos");
        alumnos = modelo.getAll();
        for (String al : alumnos) {
            System.out.println(al);
        }
    }
}
