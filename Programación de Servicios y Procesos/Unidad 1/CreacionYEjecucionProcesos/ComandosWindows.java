package CreacionYEjecucionProcesos;

import java.io.*;
import java.util.Scanner;

public class ComandosWindows extends Comandos {
    public void lanzarping(String ip) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","ping -n 3 " + ip);
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
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","dir");
            Process proceso = pb.start();
            pb.redirectOutput(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listarprocesos() {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","tasklist");
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
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "taskkill /f /pid " + PID);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void visitarweb(String web) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start " + web);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
