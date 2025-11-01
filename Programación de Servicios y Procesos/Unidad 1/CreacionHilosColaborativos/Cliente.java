package CreacionHilosColaborativos;

import java.util.Random;

public class Cliente extends Thread {
    public final int NUM_OPERACIONES = 10;
    public final int TIEMPO_PAUSA = 1000;
    public final int CANTIDAD_POR_OPERACION = 1000;
    public Random rand = new Random();
    public CuentaBancaria cuenta;
    public Cliente(CuentaBancaria cuenta, String nombre) {
        this.cuenta = cuenta;
        this.setName(nombre);
    }

    @Override
    public void run() {
        int operaciones = rand.nextInt(1, NUM_OPERACIONES + 1);
        for (int i = 0; i < operaciones; i++) {
            int tipo_operacion = rand.nextInt(2);
            int tiempo_sleep = rand.nextInt(1, TIEMPO_PAUSA + 1);
            int cantidad = rand.nextInt(1, CANTIDAD_POR_OPERACION + 1);
             if (tipo_operacion == 0) {
                 cuenta.ingresar(cantidad, getName());
             } else {
                 cuenta.retirar(cantidad, getName());
             }
            try {
                sleep(tiempo_sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
