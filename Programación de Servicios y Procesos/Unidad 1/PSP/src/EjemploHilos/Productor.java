package EjemploHilos;

public class Productor implements Runnable {
    private BufferProductos bufferProductos;
    private final int idProducto, numProductos;
    public Productor(int id, int cantidadproductos, BufferProductos buffer) {
        bufferProductos = buffer;
        idProducto = id;
        numProductos = cantidadproductos;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numProductos; i++) {
            int producto = idProducto * 1000 + i;
            try {
                bufferProductos.producir(producto);
                System.out.println("El productor " + idProducto + " produjo: " + producto);
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
