package pruebasXStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Apartado1_1 {
    public static void main(String[] args) {
        Libro voz = new Libro("La voz a ti debida", "Pedro Salinas", "Signo",96);
        XStream xstream = new XStream(new DomDriver());
        try {
            File file = new File("libros.xml");
            try (FileWriter fw = new FileWriter(file)) {
                xstream.toXML(voz, fw);
            }
            System.out.println("Archivo creado en: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
