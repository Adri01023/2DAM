package EjemploHilos;

public class BufferProductos {
    private static final int DEFAULT_MAX_CAPACIDAD = 10;
    private int[] buffer;
    private int siguiente;

    private boolean estaVacio;
    private boolean estaLleno;

    public BufferProductos(int capacidad) {
        buffer = new int[capacidad];
        siguiente = 0;
        estaVacio = true;
        estaLleno = false;
    }

    public BufferProductos() {this(DEFAULT_MAX_CAPACIDAD);}

    public boolean estaVacio() {return estaVacio;}

    synchronized public int consumir() throws InterruptedException {
        while (estaVacio) {
            System.out.println("Buffer Vacio");
            wait();
        }

        siguiente--;
        if (siguiente == 0) {
            estaVacio = true;
        }

        estaLleno = false;
        notifyAll();
        return(buffer[siguiente]);
    }

    synchronized public void producir(int producto) throws InterruptedException {
        while (estaLleno) {
            wait();
        }

        buffer[siguiente] = producto;

        siguiente++;

        if (siguiente == buffer.length) {
            estaLleno = true;
        }

        estaVacio = false;
        notifyAll();
    }
}
