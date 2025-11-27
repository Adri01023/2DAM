import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class VerificaPuerto implements Runnable {
    String ip;
    int puerto;
    public VerificaPuerto(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
    }
    @Override
    public void run() {
        try {
            try (Socket direccion = new Socket(ip, puerto)) {
                direccion.connect(direccion.getLocalSocketAddress());
                System.out.println("\tPUERTO " + puerto + " ABIERTO: " + detectarServicio(puerto));
            }
        } catch (IOException ignored) {}
    }

    public static String detectarServicio(int puerto) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("ficheroWKP.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String cadenapuerto = puerto + "/tcp";
                if (linea.contains(cadenapuerto)) {
                    String [] separadolinea = linea.split(" ");
                    return separadolinea[0];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
