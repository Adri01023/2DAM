import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class Serializacion {
    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<Persona>();
        Persona persona1 = new Persona("Manolo", "Fuentes", 47);
        Persona persona2 = new Persona("Juan", "López", 34);
        Persona persona3 = new Persona("Marisa", "Benito", 77);
        personas.add(persona1);
        personas.add(persona2);
        personas.add(persona3);
        cargarDatos(personas);
        leerDatos();
    }
    public static void cargarDatos(List<Persona> personas) {
        File fichero = new File("personas.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(personas);
            System.out.println("Personas guardadas en " + fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void leerDatos() {
        File fichero = new File("personas.dat");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            List<Persona> leidas = (List<Persona>) ois.readObject();
            System.out.println("Personas: \n");
            for (Persona p : leidas) {
                System.out.println(p);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
