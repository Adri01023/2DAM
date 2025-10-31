import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContadorEnBDsoloUpdateSql {
	static final String SQL_CONSULTA=
			"select cuenta from contadores where nombre='contador1'";
	public static void main(String[] args) {
		final String claveContador = "contador1";
		// La actualización en el propio SQL sí es atómica:
		final String sqlActualización = "UPDATE contadores SET cuenta=cuenta+1 WHERE nombre='" + claveContador + "';";
	
		 try{
			 //Class.forName("org.mariadb.jdbc.Driver");  
			 Connection connection = DriverManager.getConnection(  
	                "jdbc:mysql://localhost:3306/adat2", "dam2", "asdf.1234");  
			 PreparedStatement actualización = connection.prepareStatement(sqlActualización);
			 for (int i=0; i<1000;i++) {
				 if (actualización.executeUpdate() != 1) break;
				//if (i%10==0) System.out.println(i/10 + "%");
			 }
			 PreparedStatement consulta = connection.prepareStatement(SQL_CONSULTA);
			 ResultSet rs = consulta.executeQuery();
			 rs.next();
			 int valor = rs.getInt(1);
			 System.out.println("Valor final: " + valor);
		 } // try
		 catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
	} // main

} // class
