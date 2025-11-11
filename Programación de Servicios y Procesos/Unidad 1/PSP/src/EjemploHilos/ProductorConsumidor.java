package EjemploHilos;

public class ProductorConsumidor {
    public static void main(String[] args) throws InterruptedException {
        final int CAPACIDAD = 5;
        final int CANTIDAD_PRODUCTORES = 2;
        final int CANTIDAD_CONSUMIDORES = 3;
        final int ITEMS_POR_PRODUCTO = 20;

        BufferProductos bufferProductos = new BufferProductos(CAPACIDAD);

        Thread[] productores = new Thread[CANTIDAD_PRODUCTORES];
        Thread[] consumidores = new Thread[CANTIDAD_CONSUMIDORES];

        for (int i = 0; i < productores.length; i++) {
            productores[i] = new Thread(new Productor(i+1, ITEMS_POR_PRODUCTO, bufferProductos));
            productores[i].start();
        }
        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Thread(new Consumidor(i+1, bufferProductos));
            consumidores[i].start();
        }
        for (Thread p : productores) {
            p.join();
        }
        while (!bufferProductos.estaVacio()) {
            Thread.sleep(100);
        }
        for (Thread c : consumidores) {
            c.interrupt();
            c.join();
        }
    }
}
