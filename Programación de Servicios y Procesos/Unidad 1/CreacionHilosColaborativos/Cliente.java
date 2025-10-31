package CreacionHilosColaborativos;

public class Cliente extends Thread {
    public final int NUM_OPERACIONES = 10;
    public final int TIEMPO_PAUSA = 1000;
    public final int CANTIDAD_POR_OPERACION = 1000;

    @Override
    public void run() {
        CuentaBancaria cuenta = new CuentaBancaria();
        int operaciones = (int) (Math.random() * NUM_OPERACIONES) + 1;
        for (int i = 0; i < operaciones; i++) {
            System.out.println(this.getName());
            int tipo_operacion = (int) (Math.random() * 2) + 1;
            int tiempo_sleep = (int) (Math.random() * TIEMPO_PAUSA) + 1;
            int cantidad = (int) (Math.random() * CANTIDAD_POR_OPERACION) + 1;
             if (tipo_operacion == 1) {
                 cuenta.ingresar(cantidad);
             } else {
                 cuenta.retirar(cantidad);
             }
            try {
                this.sleep(tiempo_sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
