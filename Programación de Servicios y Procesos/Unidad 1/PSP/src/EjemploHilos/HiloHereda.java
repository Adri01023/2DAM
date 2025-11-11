package EjemploHilos;

public class HiloHereda extends Thread {
    @Override
    public void run() {
        int contador = 1;
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + " - " + contador);
            contador++;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finalizada la ejecución del proceso: " + getName());
    }
}
