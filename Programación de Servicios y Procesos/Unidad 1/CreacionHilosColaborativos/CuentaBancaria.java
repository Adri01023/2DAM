package CreacionHilosColaborativos;

public class CuentaBancaria {
     public final int SALDO_INICIAL = 1000;
     public int totalDepositado, totalRetirado;
     public int saldo;

     public CuentaBancaria() {
         saldo = SALDO_INICIAL;
     }

     synchronized public void ingresar(int cantidad, String cliente) {
         totalDepositado += cantidad;
         saldo += cantidad;
         System.out.println(cliente + " ha ingresado: " + cantidad);
     }

     synchronized public void retirar(int cantidad, String cliente) {
         if (cantidad > saldo) {
             System.out.println( cliente + " ha realizado una retirada de "
                             + saldo + " debido a que la cuenta quedaría en números rojos.");
             cantidad = saldo;
             saldo -= cantidad;
             totalRetirado += cantidad;
         } else {
             saldo -= cantidad;
             totalRetirado += cantidad;
             System.out.println( cliente + " ha retirado " + cantidad);
         }
     }

     public int getSaldo() {
         return saldo;
     }

     public int gettotalDepositado() {
         return totalDepositado;
     }

     public int gettotalRetirado() { return totalRetirado; }

    public int getSaldoInicial() { return SALDO_INICIAL; }
}
