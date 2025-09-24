import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;

    public Persona(String n, String a, int e) {
        nombre = n;
        apellido = a;
        edad = e;
    }
    @Override
    public String toString() {
        return "Nombre: " + nombre + " Apellido: " + apellido + " Edad: " + edad;
    }
}
