package CreacionYEjecucionProcesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ComandosLinux extends Comandos {
    public void lanzarping(String ip) {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash","-c","ping -c 5 " + ip);
            Process proceso = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listar(String archivo) {
        try {
            File file = new File(archivo);
            ProcessBuilder pb = new ProcessBuilder("bash","-c","ls");
            pb.redirectError(file);
            pb.redirectOutput(file);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listarprocesos() {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash","-c","ps aux");
            Process proceso = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("\nIndica el PID el proceso que quieres matar: ");
            String pid = sc.next();
            matarproceso(pid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void matarproceso(String PID) {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", "kill " + PID);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void visitarweb(String web) {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", "/usr/bin/firefox " + web);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
