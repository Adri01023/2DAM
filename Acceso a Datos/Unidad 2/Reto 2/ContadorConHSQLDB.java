import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContadorConHSQLDB {
	static final String url = "jdbc:hsqldb:file:Contadores_HSQLDB/DB_Contadores;shutdown=true";
	static final String sqlCreate = "create table if not exists contadores (nombre varchar(20) PRIMARY KEY, cuenta int);";
	static final String sqlInsert = "insert into PUBLIC.contadores values (?,0);";
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection(url, "SA", "");
			System.out.println("Conexion realizada con éxito");
			PreparedStatement creacion = conn.prepareStatement(sqlCreate);
			PreparedStatement insertar = conn.prepareStatement(sqlInsert);
			creacion.executeUpdate();
			System.out.println("Creacion de tabla exitosa");
			insertar.setString(1,"contador1");
			insertar.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
