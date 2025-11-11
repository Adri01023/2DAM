package EjemploHilos;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EjemploLock {
    private static int valor = 0;
    private static final int THREADS = 10;
    private static final int INCREMENTA_PER_THREAD = 100_000;
    private final Lock lock  = new ReentrantLock();

    public void incrementa() {
        //lock.lock();
        try {
            if (lock.tryLock(5, TimeUnit.MILLISECONDS)) {
                int valor_previo = valor;
                valor++;
                if (valor_previo != valor-1) {
                    System.out.println("Valor previo: " + valor_previo + " Valor nuevo: " + valor);
                }
            } else {
                System.out.println("El hilo no ha conseguido el cerrojo");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        EjemploLock el = new EjemploLock();
        Thread[] hilos = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTA_PER_THREAD; j++) {
                    el.incrementa();
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
