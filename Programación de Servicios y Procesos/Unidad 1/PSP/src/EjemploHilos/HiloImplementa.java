package EjemploHilos;

public class HiloImplementa implements Runnable {

    @Override
    public void run() {
        System.out.println("Hola me llamo: " + Thread.currentThread().getName());
    }
}
