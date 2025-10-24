package modelo;

import java.io.Serializable;

public class Contacto implements Serializable, Comparable<Contacto> {
    protected String nombre;
    protected String telefono;

    @Override
    public int compareTo(Contacto arg0) {
        return nombre.toLowerCase().compareTo(arg0.nombre.toLowerCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Contacto)) return false;
        return nombre.toLowerCase().equals(((Contacto) obj).nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return nombre + ":" + telefono;
    }

    public String[] toArray() {
        return new String[]{nombre, telefono};
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Contacto(String nombre, String telefono) {
        super();
        this.nombre = nombre;
        this.telefono = telefono;
    }
}