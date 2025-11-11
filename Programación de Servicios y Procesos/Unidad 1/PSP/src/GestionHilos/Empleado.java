package GestionHilos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Empleado implements Runnable {
    Random random = new Random();
    Semaphore mesa, ordenador;
    final int TIEMPO_TRABAJANDO = random.nextInt(10000);
    public Empleado(Semaphore mesita, Semaphore ordenadorcito) {
        mesa = mesita;
        ordenador = ordenadorcito;
    }

    @Override
    public void run() {
        while (true) {
            int aleatorio = random.nextInt(0,2);
            if (aleatorio == 0) {
                if (mesa.tryAcquire() && ordenador.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " ha cogido mesa y ordenador");
                    try {
                        System.out.println(Thread.currentThread().getName() + " está trabajando...");
                        Thread.sleep(TIEMPO_TRABAJANDO);
                        System.out.println(Thread.currentThread().getName() + " libera su mesa y ordenador");
                        mesa.release();
                        ordenador.release();
                        Thread.sleep(random.nextInt(500, 1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " no ha podido coger ni mesa ni asiento.");
                    mesa.release();
                    ordenador.release();
                }
            } else {
                if (ordenador.tryAcquire() && mesa.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " ha cogido mesa y ordenador");
                    try {
                        System.out.println(Thread.currentThread().getName() + " está trabajando...");
                        Thread.sleep(TIEMPO_TRABAJANDO);
                        System.out.println(Thread.currentThread().getName() + " libera su mesa y ordenador");
                        mesa.release();
                        ordenador.release();
                        Thread.sleep(random.nextInt(500, 1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " no ha podido coger ni mesa ni asiento.");
                    mesa.release();
                    ordenador.release();
                }
            }
    }
    }
}
