package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloAgendaJDBC {
    private Connection conn;

    public ModeloAgendaJDBC() {
        try {
            // Cambia usuario/contraseña si hace falta
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adat2", "dam2", "asdf.1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contacto> getAll() {
        List<Contacto> lista = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre, telefono FROM contactos")) {
            while (rs.next()) {
                lista.add(new Contacto(rs.getString("nombre"), rs.getString("telefono")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertar(Contacto c) {
        String sql = "INSERT INTO contactos(nombre, telefono) VALUES (?, ?) ON DUPLICATE KEY UPDATE telefono=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Contacto c) {
        String sql = "UPDATE contactos SET telefono = ? WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getTelefono());
            ps.setString(2, c.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrar(String nombre) {
        String sql = "DELETE FROM contactos WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}