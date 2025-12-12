import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;

public class HostInfo {
    public static void main(String[] args) {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Nombre del localhost: " + localhost.getHostName());
            System.out.println("Dirección IP local: " + localhost.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            InetAddress google = InetAddress.getByName("www.google.com");
            System.out.println("Nombre del localhost: " + google.getHostName());
            System.out.println("IP de google: " + google.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            Enumeration<InetAddress> adresses;
            while (interfaces.hasMoreElements()) {
                NetworkInterface interfaz = interfaces.nextElement();


                adresses = interfaz.getInetAddresses();
                System.out.println("Nombre de la interfaz: " + interfaz.getName());
                System.out.println("Interfaz activa: " + interfaz.isUp());
                System.out.println("Dirección MAC: " + Arrays.toString(interfaz.getHardwareAddress()));

                while (adresses.hasMoreElements()) {
                    InetAddress direccion = adresses.nextElement();
                    System.out.println("\tDirección IP: " + direccion.getHostAddress());
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}