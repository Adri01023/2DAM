package Actividad02;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;

public class Ejercicio1y2 {

    public static void main(String[] args) {
        try {
            XStream xs = new XStream();

            // Configuración de alias
            xs.alias("proveedor", Proveedor.class);
            xs.alias("cafe", Cafe.class);

            // atributo en XML
            xs.useAttributeFor(Cafe.class, "tipo");
            xs.aliasAttribute("tipoCafe", "tipo");

            // Crear proveedor con cafés
            Proveedor p = new Proveedor("Tienda1");
            p.addCafe(new Cafe("Colombiano", "fuerte"));
            p.addCafe(new Cafe("Brasil", "suave"));

            // Convertir a XML y mostrarlo en consola
            String xml = xs.toXML(p);
            System.out.println(xml);

            // Persistir en archivo
            try (FileOutputStream fos = new FileOutputStream("proveedor.xml")) {
                xs.toXML(p, fos);
            }

            // Leer de archivo
            Proveedor p2;
            try (FileInputStream fis = new FileInputStream("proveedor.xml")) {
                p2 = (Proveedor) xs.fromXML(fis);
            }

            System.out.println("Objeto recuperado: " + p2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
