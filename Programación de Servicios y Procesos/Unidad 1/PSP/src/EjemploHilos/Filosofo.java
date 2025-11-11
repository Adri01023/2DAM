package EjemploHilos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable {
    private final Semaphore cubiertoIzdo, cubiertoDrcho;

    public Filosofo(Semaphore izquierdo, Semaphore derecho) {
        cubiertoIzdo = izquierdo;
        cubiertoDrcho = derecho;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                /*
                cubiertoIzdo.acquire();
                cubiertoDrcho.acquire();
                System.out.println(Thread.currentThread().getName() + " comiendo... ");
                cubiertoIzdo.release();
                cubiertoDrcho.release();*/
                cubiertoIzdo.acquire();
                if (cubiertoDrcho.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " comiendo...");
                    cubiertoDrcho.release();
                } else {
                    System.out.println(Thread.currentThread().getName() + " no ha podido coger el cubierto y suelta el otro");
                }
                cubiertoIzdo.release();
                System.out.println(Thread.currentThread().getName() + " pensando... ");
                Thread.sleep(random.nextInt(20,1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}