package CreacionHilosColaborativos;

public class CuentaBancaria {
     public static int saldo_total;
     public final int SALDO_INICIAL = 1000;
     public int saldo;

     public CuentaBancaria() {
         saldo = SALDO_INICIAL;
     }

     synchronized public void ingresar(int cantidad) {
         saldo_total += cantidad;
         saldo += cantidad;
         System.out.print(" ha ingresado: " + cantidad);
     }

     synchronized public void retirar(int cantidad) {
         if (cantidad > saldo) {
             System.out.println(" no ha podido realizar la retirada debido a que la cuenta quedaría en números rojos.");
             cantidad = saldo;
             saldo -= cantidad;
             saldo_total -= cantidad;
         } else {
             saldo -= cantidad;
             System.out.print( " ha retirado " + cantidad);
         }
     }

     public int getSaldo() {
         return saldo;
     }

     public int getSaldoTotal() {
         return saldo_total;
     }
}
