package EjemploHilos;

import java.util.concurrent.Semaphore;

public class ProblemaFilosofos {
    public static void main(String[] args) throws InterruptedException {
    Semaphore sem12 = new Semaphore(1);
    Semaphore sem23 = new Semaphore(1);
    Semaphore sem31 = new Semaphore(1);
    Filosofo filo1 = new Filosofo(sem12, sem31);
    Filosofo filo2 = new Filosofo(sem23, sem12);
    Filosofo filo3 = new Filosofo(sem31, sem23);
    Thread h1 = new Thread(filo1, "Filosofo 1");
    Thread h2 = new Thread(filo2, "Filosofo 2");
    Thread h3 = new Thread(filo3, "Filosofo 3");
    h1.start();
    h2.start();
    h3.start();

    h1.join();
    h2.join();
    h3.join();
    }
}
