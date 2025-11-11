package EjemploHilos;

public class ContadorObjectLock {
    private static final int THREADS = 10;
    private static final int INCREMENTA_PER_THREAD = 100_000;
    public static int contador1 = 0;
    public static int contador2 = 0;

    private final Object lock = new Object();
    private final Object lock2 = new Object();

    public void incrementa() {
        synchronized (lock) {
            contador1++;
        }
    }

    public void incrementa2() {
        synchronized (lock2) {
            contador2++;
        }
    }

    public static void main(String[] args) {
        ContadorObjectLock col = new ContadorObjectLock();
        Thread[] hilos = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTA_PER_THREAD; j++) {
                    col.incrementa();
                    col.incrementa2();
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
        System.out.println("Obtenido contador1: " + contador1);
        System.out.println("Obtenido contador2: " + contador2);
    }
}
