package Actividad02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class LeerLibroAlises {

	public static void main(String[] args) {

        XStream xstream = new XStream();

        //alises
        xstream.alias("libros", List.class);
        xstream.alias("libro", Libro.class);
        
        xstream.aliasField("titulo", Libro.class, "titulo");
        xstream.aliasField("editorial", Libro.class, "editorial");
        xstream.aliasField("paginas", Libro.class, "paginas");
        xstream.aliasField("autor", Libro.class, "autor");
        xstream.aliasField("copias", Libro.class, "copias");
        xstream.aliasField("ISBN", Libro.class, "ISBN");

        xstream.allowTypes(new Class[] { Libro.class });

        try (BufferedReader br= new BufferedReader(new FileReader("libros1.xml"))) {
        	
            List<Libro> listaRecuperada = (List<Libro>) xstream.fromXML(br);
            System.out.println("Libros recuperados desde libros1.xml:");
            for (Libro libro : listaRecuperada) {
                System.out.println(libro);
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo libros1.xml.");
            e.printStackTrace();
        }
    }

}
