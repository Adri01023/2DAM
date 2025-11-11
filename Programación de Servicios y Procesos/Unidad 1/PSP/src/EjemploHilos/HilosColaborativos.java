package EjemploHilos;

public class HilosColaborativos {
    private static int valor = 0;
    private static final int THREADS = 10;
    private static final int INCREMENTA_PER_THREAD = 100_000;

    synchronized public void incrementa() {
        int valor_previo = valor;
        valor++;
        if (valor_previo != valor-1) {
            System.out.println("Valor previo: " + valor_previo + " Valor nuevo: " + valor);
        }
    }

    public static void main(String[] args) {
        HilosColaborativos hc = new HilosColaborativos();
        Thread[] hilos = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTA_PER_THREAD; j++) {
                    hc.incrementa();
                }
            });
            hilos[i].start();
        }
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Esperado: " + (THREADS * INCREMENTA_PER_THREAD));
        System.out.println("Obtenido: " + valor);
    }
}