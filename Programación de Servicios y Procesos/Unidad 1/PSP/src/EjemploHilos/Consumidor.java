package EjemploHilos;

public class Consumidor implements Runnable {
    private BufferProductos bufferProductos;
    private final int idConsumidor;

    public Consumidor(int id, BufferProductos buffer) {
        bufferProductos = buffer;
        idConsumidor = id;
    }

    @Override
    public void run() {
        int producto;
        while (true) {
            try {
                producto = bufferProductos.consumir();
                System.out.println("El consumidor " + idConsumidor + " ha consumido el producto " + producto);
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Me han interrumpido soy el consumidor " + idConsumidor);
                return;
            }
        }
    }
}
