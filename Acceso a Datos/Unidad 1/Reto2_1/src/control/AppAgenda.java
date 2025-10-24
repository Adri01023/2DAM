package control;

import java.util.List;
import modelo.Contacto;
import modelo.ModeloAgendaJDBC;

public class AppAgenda {
    private ModeloAgendaJDBC modelo;

    public AppAgenda() {
        modelo = new ModeloAgendaJDBC();
    }

    public List<Contacto> getContactos() {
        return modelo.getAll();
    }

    public Contacto añadirContacto(String nombre, String telefono) {
        Contacto c = new Contacto(nombre, telefono);
        modelo.insertar(c);
        return c;
    }

    public Contacto editarContacto(String nombre, String telefono) {
        Contacto c = new Contacto(nombre, telefono);
        modelo.actualizar(c);
        return c;
    }

    public Contacto borrarContacto(String nombre) {
        modelo.borrar(nombre);
        return new Contacto(nombre, null);
    }

    // Métodos CSV/Serial vacíos
    public void guardarFicheroCSV() {}
    public void recargaFicheroCSV() {}
    public void guardarFicheroSerial() {}
    public void recargaFicheroSerial() {}
}