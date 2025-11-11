package GestionHilos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class GestionaEmpleados {
    static int mesasyordenadores, empleados;
    static Semaphore mesa,ordenador;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> total_empleados = new ArrayList<>();
        boolean salebucle = true;
        Scanner sc = new Scanner(System.in);
        while (salebucle) {
            try {
                System.out.println("Introduce el número de empleados:");
                empleados = sc.nextInt();
                System.out.println("Introduce el número de mesas y ordenadores:");
                mesasyordenadores = sc.nextInt();
                salebucle = false;
                if (empleados <= mesasyordenadores) {
                    System.out.println("El número de ordenadores y mesas ha de ser inferior al número de empleados");
                    salebucle = true;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Introduce un número entero");
                sc.nextLine();
            }
        }
        mesa = new Semaphore(mesasyordenadores);
        ordenador = new Semaphore(mesasyordenadores);
        for (int i = 1; i < empleados + 1; i++) {
            Empleado empleado = new Empleado(mesa, ordenador);
            Thread hilo = new Thread(empleado, "Empleado" + i);
            total_empleados.add(hilo);
        }
        for (Thread e : total_empleados) {
            e.start();
        }
        for (Thread e : total_empleados) {
            e.join();
        }
    }
}
