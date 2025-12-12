
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        InetAddress direccion;
        Socket servidor = null;
        int numPuerto = 5000;

        System.out.println("Soy el cliente intentando conectarse");

        try {
            direccion = InetAddress.getLocalHost(); //direccion del servidor
            servidor = new Socket(direccion, numPuerto);
            System.out.println("Conexión realizada con éxito");
            DataInputStream dis = new DataInputStream(servidor.getInputStream());
            System.out.println(dis.readUTF());
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally{
            try {
                if (servidor != null) {
                    servidor.close();
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
