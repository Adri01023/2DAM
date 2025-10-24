package gestionAlumnos.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloAlumnosJDBC implements IModeloAlumnos {

	private static String cadenaConexion = "jdbc:mysql://localhost:3306/adat1";
	private static String user = "dam2";
	private static String pass = "asdf.1234";
	
	public ModeloAlumnosJDBC() {
	}
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(cadenaConexion, user, pass);
	
	}
	@Override
	public List<String> getAll() {
		List<String> alumnos = new ArrayList<>();
	    String sql = "SELECT * FROM alumnos";

	    try (Connection con = getConnection();
	         Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {

	        while (rs.next()) {
	            String alumno = rs.getString("DNI") + " -- " +
	                            rs.getString("nombre") + " -- " +
	                            rs.getString("apellidos") + " -- " +
	                            rs.getString("CP");
	            alumnos.add(alumno);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return alumnos;
	}

	@Override
	public Alumno getAlumnoPorDNI(String DNI) {
		 String sql = "SELECT * FROM alumnos WHERE DNI = ?";
		    Alumno alumno = null;

		    try (Connection con = getConnection();
		         PreparedStatement pst = con.prepareStatement(sql)) {

		        pst.setString(1, DNI);
		        ResultSet rs = pst.executeQuery();

		        if (rs.next()) {
		            alumno = new Alumno();
		            alumno.setDNI(rs.getString("DNI")) ;
		            alumno.setNombre(rs.getString("nombre"));
		            alumno.setApellidos(rs.getString("apellidos"));
		            alumno.setCP(rs.getString("CP"));
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return alumno;
		}

	@Override
	public boolean modificarAlumno(Alumno alumno) {
		String sql = "UPDATE alumnos SET DNI=?, nombre=?, apellidos=?, CP=? WHERE DNI=?";
        boolean actualizado = false;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
        	
        	pst.setString(1, alumno.getDNI());
            pst.setString(2, alumno.getNombre());
            pst.setString(3, alumno.getApellidos());
            pst.setString(4, alumno.getCP());
            pst.setString(5, alumno.getDNI());

            int filas = pst.executeUpdate();
            actualizado = filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actualizado;
    }
	@Override
	public boolean eliminarAlumno(String DNI) {
		String sql = "DELETE FROM alumnos WHERE DNI = ?";
        boolean eliminado = false;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, DNI);
            int filas = pst.executeUpdate();
            eliminado = filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminado;
    }

	@Override
	public boolean crear(Alumno alumno) {
		String sql = "INSERT INTO alumnos (DNI, nombre, apellidos, CP) VALUES (?, ?, ?, ?)";
        boolean insertado = false;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, alumno.getDNI());
            pst.setString(2, alumno.getNombre());
            pst.setString(3, alumno.getApellidos());
            pst.setString(4, alumno.getCP());

            int filas = pst.executeUpdate();
            insertado = filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insertado;
    }
}