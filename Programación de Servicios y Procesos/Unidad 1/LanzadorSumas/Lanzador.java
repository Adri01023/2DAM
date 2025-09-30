package LanzadorSumas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lanzador {
    static String path = "fichero.txt";
    private void lanzaSumas(Integer n1, Integer n2, Integer proceso) {
        ProcessBuilder pb;
        Process process;
        String classname = "LanzadorSumas.Sumador";
        //String classpath = "/home/alumno/IdeaProjects/2DAM/out/production/Programación de Servicios y Procesos/";
        String currentPath = System.getProperty("user.dir");
        String classpath = currentPath + "/out/production/Programación de Servicios y Procesos/";
        try {
            pb = new ProcessBuilder("java", "-cp", classpath, classname, n1.toString(), n2.toString(), proceso.toString(), path);
            pb.inheritIO();
            process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Lanzador lanzador = new Lanzador();
        lanzador.lanzaSumas(2, 5, 1);
        lanzador.lanzaSumas(6, 8, 2);
        lanzador.lanzaSumas(5, 14, 3);
        lanzador.lanzaSumas(4, 12, 4);
        lanzador.lanzaSumas(5, 7, 5);
        lanzador.lanzaSumas(4, 8, 6);
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
