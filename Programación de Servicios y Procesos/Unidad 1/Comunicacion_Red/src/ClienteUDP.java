import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ClienteUDP {
    public static void main(String[] args) throws UnknownHostException {
        int PUERTO = 6789;
        int BUFFER_SIZE = 1000;
        System.out.println("Soy el cliente y voy a enviar un datagrama");
        String mensaje = "Hola soy un cliente UDP";
        try (DatagramSocket socketUDP = new DatagramSocket()) {
            byte[] men = mensaje.getBytes();
            InetAddress direccion = InetAddress.getByName("localhost");
            DatagramPacket paquete = new DatagramPacket(men, men.length, direccion, PUERTO);
            socketUDP.send(paquete);
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket respuesta = new DatagramPacket(buffer, BUFFER_SIZE);
            socketUDP.receive(respuesta);
            System.out.println("Respuesta: " + new String(respuesta.getData()).trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
