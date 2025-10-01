package ComunicacionProcesos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class GeneraRandoms {
    public static void main(String[] args) {
        int numero = (int) (Math.random() * 9 + 1);
        File archivo = new File("numeros.txt");
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            fos.write(numero);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
