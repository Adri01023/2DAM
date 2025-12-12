
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket cliente = null; //Esto es un cliente
        int numCliente = 0;
        int numPuerto = 5000;

        try {
            servidor = new ServerSocket(numPuerto);
            System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + numPuerto);

            do{
                cliente = servidor.accept();
                numCliente ++;
                System.out.println("\tLlega el cliente: " + numCliente);

                DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                dos.writeUTF("Usted es mi cliente: " + numCliente);

                cliente.close();
                System.out.println("Se ha cerrado la conexión de cliente");
            }while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                servidor.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
