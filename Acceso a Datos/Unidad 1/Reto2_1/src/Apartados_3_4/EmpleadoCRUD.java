package Apartados_3_4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoCRUD {

	private static final String URL = "jdbc:mysql://localhost:3306/scott";
    private static final String USER = "root";
    private static final String PASSWORD = "1357";
    
    	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    	}
    
	    public void insertarEmpleado(Empleado emp) {
	        String sql = "INSERT INTO emp (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, emp.getId());
	            stmt.setString(2, emp.getNombre());
	            stmt.setString(3, emp.getTrabajo());
	            if (emp.getManager() != null)
	                stmt.setInt(4, emp.getManager());
	            else
	                stmt.setNull(4, Types.INTEGER);
	            
	            stmt.setString(5, emp.getFechaContratacion());
	            stmt.setInt(6, emp.getSalario());

	            if (emp.getComision() != null)
	                stmt.setInt(7, emp.getComision());
	            else
	                stmt.setNull(7, Types.INTEGER);

	            stmt.setInt(8, emp.getNDepartamento());
	            stmt.executeUpdate();
	            System.out.println("\nEmpleado insertado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Empleado> listarEmpleados() {
	        List<Empleado> lista = new ArrayList<>();
	        String sql = "SELECT * FROM emp";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                lista.add(new Empleado(
	                    rs.getInt("EMPNO"),
	                    rs.getString("ENAME"),
	                    rs.getString("JOB"),
	                    rs.getInt("MGR"),	// (Integer) rs.getObject("MGR"),
	                    rs.getString("HIREDATE"),
	                    rs.getInt("SAL"),
	                    (Integer) rs.getObject("COMM"),
	                    rs.getInt("DEPTNO")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }

	    public void actualizarEmpleadoPorID(Empleado emp) {
	        String sql = "UPDATE emp SET ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? WHERE EMPNO=?";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, emp.getNombre());
	            stmt.setString(2, emp.getTrabajo());
	            if (emp.getManager() != null)
	                stmt.setInt(3, emp.getManager());
	            else
	                stmt.setNull(3, Types.INTEGER);
	            stmt.setString(4, emp.getFechaContratacion());
	            stmt.setInt(5, emp.getSalario());
	            if (emp.getComision() != null)
	                stmt.setInt(6, emp.getComision());
	            else
	                stmt.setNull(6, Types.INTEGER);
	            stmt.setInt(7, emp.getNDepartamento());
	            stmt.setInt(8, emp.getId());
	            
	            stmt.executeUpdate();
	            System.out.println("\nEmpleado actualizado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void eliminarEmpleadoPorID(int empno) {
	        String sql = "DELETE FROM emp WHERE EMPNO=?";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, empno);
	            stmt.executeUpdate();
	            System.out.println("\nEmpleado eliminado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Empleado> buscarPorNombre(String nombre) {
	        List<Empleado> lista = new ArrayList<>();
	        String sql = "SELECT * FROM emp WHERE ENAME LIKE ?";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, "%" + nombre + "%");
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                lista.add(new Empleado(
	                    rs.getInt("EMPNO"),
	                    rs.getString("ENAME"),
	                    rs.getString("JOB"),
	                    (Integer) rs.getObject("MGR"),
	                    rs.getString("HIREDATE"),
	                    rs.getInt("SAL"),
	                    (Integer) rs.getObject("COMM"),
	                    rs.getInt("DEPTNO")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }
	    
	    public List<String> obtenerEmpleadosConDepartamento() {
	        List<String> lista = new ArrayList<>();

	        String sql = """
	            SELECT e.ENAME AS empleado, e.JOB AS trabajo, d.DNAME AS departamento
	            FROM emp e
	            JOIN dept d ON e.DEPTNO = d.DEPTNO
	            ORDER BY d.DNAME, e.ENAME;
	        """;

	        try (Connection conn = getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                String empleado = rs.getString("empleado");
	                String trabajo = rs.getString("trabajo");
	                String departamento = rs.getString("departamento");
	                lista.add("Empleado: " + empleado + " Trabajo: " + trabajo + " Departamento: " + departamento);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return lista;
	    }

}
