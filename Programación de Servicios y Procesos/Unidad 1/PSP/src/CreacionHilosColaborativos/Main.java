package CreacionHilosColaborativos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos clientes deseas que interactuen: ");
        int clientes = sc.nextInt();
        for (int i = 1; i < clientes + 1; i++) {
            Cliente cliente = new Cliente();
            cliente.setName("Cliente" + i);
            cliente.start();
        }
    }
}
