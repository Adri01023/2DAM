package Actividad02;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class LeerLibro {
    public static void main(String[] args) {
        System.out.println("Directorio actual: " + System.getProperty("user.dir"));

        XStream xstream = new XStream();
        xstream.alias("libro", Libro.class);

        xstream.allowTypes(new Class[] { Libro.class });

        try (FileReader reader = new FileReader("libros1.xml")) {
            List<Libro> listaRecuperada = (List<Libro>) xstream.fromXML(reader);
            System.out.println("Libros recuperados desde libros1.xml:");
            for (Libro libro : listaRecuperada) {
                System.out.println(libro);
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo libros1.xml. Primero ejecuta GuardarLibro.");
            e.printStackTrace();
        }
    }
}
