package CreacionHilosColaborativos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numclientes = 0;
        boolean continuar = true;
        CuentaBancaria cuenta = new CuentaBancaria();
        List<Cliente> clientes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos clientes deseas que interactuen: ");
        while (continuar) {
            try {
                numclientes = sc.nextInt();
                continuar = false;
            } catch (InputMismatchException i) {
                System.out.println("Debe ingresar un numero de clientes");
                sc.nextLine();
                continuar = true;
            }
        }
        for (int i = 1; i < numclientes + 1; i++) {
            Cliente cliente = new Cliente(cuenta, "Cliente " + i);
            clientes.add(cliente);
            cliente.start();
        }
        for (Cliente c : clientes) {
            try {
                c.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int transacciones = cuenta.getSaldoInicial() + (cuenta.gettotalDepositado() - cuenta.gettotalRetirado());
        System.out.println("Saldo total: " + cuenta.getSaldo());
        System.out.println("Saldo de las transacciones: " + transacciones);
    }
}
