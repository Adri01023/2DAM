package CreacionYEjecucionDeHilos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float base, alto;
        int prioridad;
        List<Thread> lista_hilos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuantos triángulos deseas calcular?");
        int veces = sc.nextInt();
        System.out.println("¿Quiere asignar prioridad al cálculo de los triángulos? (S o N)");
        sc.nextLine();
        String prio = sc.nextLine();
        if (prio.equalsIgnoreCase("s")) {
            for (int i = 1; i < veces + 1; i++) {
                System.out.println("Triángulo " + i);
                System.out.println("Introduce la base: ");
                base = sc.nextFloat();
                System.out.println("Introduce la altura: ");
                alto = sc.nextFloat();
                System.out.println("Introduce la prioridad de 1 a 10: ");
                try {
                    prioridad = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Para la prioridad escribe un número entero");
                    prioridad = 1;
                }
                Thread hilo = new Triangulos(alto, base);
                hilo.setName("Triángulo " + i);
                hilo.setPriority(prioridad);
                lista_hilos.add(hilo);
            }
        } else {
            for (int i = 1; i < veces + 1; i++) {
                System.out.println("Triángulo " + i);
                System.out.println("Introduce la base: ");
                base = sc.nextFloat();
                System.out.println("Introduce la altura: ");
                alto = sc.nextFloat();
                Thread hilo = new Triangulos(alto, base);
                hilo.setName("Triángulo " + i);
                lista_hilos.add(hilo);
            }
        }
        for (Thread hilo : lista_hilos) {
            hilo.start();
        }
    }
}