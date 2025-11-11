package PSP.procesos;

import java.io.*;

public class EjecProcess {
    public void ejecutarPB (String comando) {
        ProcessBuilder pb;
        Process proceso;
        try {
            File log = new File("salida.log");
            pb = new ProcessBuilder("bash","-c","ping -c 3 8.8.8.8");
            proceso = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutarRun(String[] comandos) {
        Runtime runtime;
        Process proceso;
        try {
            runtime = Runtime.getRuntime();
            proceso = runtime.exec(comandos);
            synchronized (proceso) {
                proceso.wait(2000);
            }
            proceso.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String comando = "google-chrome";
        EjecProcess ej = new EjecProcess();
        System.out.println(System.getProperty("os.name"));
        ej.ejecutarPB(comando);
        //ej.ejecutarRun(args);
    }
}
