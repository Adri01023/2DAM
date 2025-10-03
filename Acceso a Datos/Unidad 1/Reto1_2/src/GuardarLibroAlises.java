package Actividad02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class GuardarLibroAlises {

	public static void main(String[] args) {

        Libro l1 = new Libro(1234,"El Quijote","Alianza",1400,"Miguel de Cervantes",4);
        Libro l2 = new Libro(2345,"Cien años de soledad","Sudamericana",496,"Gabriel García Márquez",10);
        Libro l3 = new Libro(3456,"Orgullo y prejuicio","Penguin Classics",432,"Jane Austen",7);

        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(l1);
        listaLibros.add(l2);
        listaLibros.add(l3);

        XStream xstream = new XStream();

        // alises
        xstream.alias("libros", List.class); // alias para la lista
        xstream.alias("libro", Libro.class); // alias para la clase
        
        xstream.aliasField("titulo", Libro.class, "titulo");
        xstream.aliasField("editorial", Libro.class, "editorial");
        xstream.aliasField("paginas", Libro.class, "paginas");
        xstream.aliasField("autor", Libro.class, "autor");
        xstream.aliasField("copias", Libro.class, "copias");
        xstream.aliasField("ISBN", Libro.class, "ISBN");

        xstream.allowTypes(new Class[] { Libro.class });

        String xml = xstream.toXML(listaLibros);
        System.out.println("XML generado con los libros:");
        System.out.println(xml);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("libros1.xml"))) {
            bw.write(xml);
            System.out.println("\nArchivo libros1.xml creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
