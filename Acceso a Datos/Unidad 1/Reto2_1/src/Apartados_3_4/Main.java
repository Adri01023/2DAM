package Apartados_3_4;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoCRUD crud = new EmpleadoCRUD();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMENÚ EMPLEADOS");
            System.out.println("1. Insertar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Actualizar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Buscar por nombre");
            System.out.println("6. Listar empleados y departamentos");
            System.out.println("7. Salir");
            System.out.print("\nElige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1: {
                	System.out.println("ID: ");
                	Integer id = sc.nextInt();
                	sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Puesto: ");
                    String puesto = sc.nextLine();
                    System.out.print("ID Manager: ");
                    Integer id_mgr = sc.nextInt();
                    System.out.println("Salario (número entero): ");
                    Integer salario = sc.nextInt();
                    System.out.println("Comisión (número entero): ");
                    Integer comision = sc.nextInt();
                    System.out.print("ID Departamento (10, 20 o 30): ");
                    Integer deptId = sc.nextInt();

                    Empleado e = new Empleado(id, nombre, puesto, id_mgr, LocalDate.now().toString(),
                    		salario, comision, deptId);
                    crud.insertarEmpleado(e);
                    break;
                }
                case 2: {
                    List<Empleado> empleados = crud.listarEmpleados();
                    for (Empleado e : empleados) {
                    	System.out.println(e);
                    }
                    break;
                }
                case 3: {
                    System.out.print("ID del empleado a actualizar: ");
                    Integer id = sc.nextInt(); 
                    sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo puesto: ");
                    String puesto = sc.nextLine();
                    System.out.print("ID nuevo manager: ");
                    Integer id_mgr = sc.nextInt();
                    System.out.println("Nuevo salario (número entero): ");
                    Integer salario = sc.nextInt();
                    System.out.println("Nueva comisión (número entero): ");
                    Integer comision = sc.nextInt();
                    System.out.print("Nuevo ID de departamento (10, 20 o 30): ");
                    Integer deptId = sc.nextInt();

                    Empleado e = new Empleado(id, nombre, puesto, id_mgr, LocalDate.now().toString(), 
                    		salario, comision, deptId);
                    crud.actualizarEmpleadoPorID(e);
                    break;
                }
                case 4: {
                    System.out.print("ID del empleado a eliminar: ");
                    int id = sc.nextInt();
                    crud.eliminarEmpleadoPorID(id);
                    break;
                }
                case 5: {
                    System.out.print("Nombre a buscar: ");
                    String nombre = sc.nextLine();
                    List<Empleado> resultados = crud.buscarPorNombre(nombre);
                    for (Empleado e : resultados) {
                    	System.out.println(e);
                    }
                    break;
                }
                case 6: {
                	List<String> resultados = crud.obtenerEmpleadosConDepartamento();
                	for (String cadena : resultados) {
                		System.out.println(cadena);
                	}
                	break;
                }
                case 7: System.out.println("Saliendo del programa..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
        sc.close();
    }
}
