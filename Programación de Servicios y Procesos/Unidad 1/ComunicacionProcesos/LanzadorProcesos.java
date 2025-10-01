package ComunicacionProcesos;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class LanzadorProcesos {
    private void lanzaRandoms() {
        ProcessBuilder pb;
        Process process;
        String classname = "ComunicacionProcesos.GeneraRandoms";
        String currentpath = System.getProperty("user.dir");
        String classpath = currentpath + "/out/production/Programación de Servicios y Procesos/";
        try {
            pb = new ProcessBuilder("java", "-cp", classpath, classname);
            pb.inheritIO();
            process = pb.start();
            process.waitFor();
            File archivo = new File("numeros.txt");
            FileInputStream fis = new FileInputStream(archivo);
            System.out.print(fis.read());
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LanzadorProcesos lanzador = new LanzadorProcesos();
        String linea = "";
        int veces;
        char[] array;
        System.out.print("Introduce caracteres: ");
        while (!linea.equals("fin")) {
            System.out.println();
            linea = sc.nextLine();
            if (!linea.equals("fin")) {
                array = linea.toCharArray();
                veces = array.length;
                for (int i = 0; i < veces; i++) {
                    lanzador.lanzaRandoms();
                }
            }
        }
    }
}
