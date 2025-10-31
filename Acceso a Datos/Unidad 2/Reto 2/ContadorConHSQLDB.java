import java.sql.*;

public class ContadorConHSQLDB {
    static final String url = "jdbc:hsqldb:file:Contadores_HSQLDB/DB_Contadores;shutdown=true";
    static final String sqlCreate = "create table IF NOT EXISTS contadores (NOMBRE VARCHAR(20) PRIMARY KEY, CUENTA INT);";
    static final String sqlInsert = "INSERT INTO contadores (NOMBRE, CUENTA) VALUES (?, 0)";
    static final String SQL_CONSULTA = "select cuenta from contadores where nombre='contador1'";
	static final String SQL_ACTUALIZA = "update contadores set cuenta=? where nombre='contador1'";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, "SA", "")) {
        	ResultSet rs = conn.getMetaData().getTables(null, "PUBLIC", "CONTADORES", new String[] {"TABLE"});
        	if (!rs.next()) {
        		PreparedStatement creacion = conn.prepareStatement(sqlCreate);
                creacion.executeUpdate();
                creacion.close();
                PreparedStatement insertar = conn.prepareStatement(sqlInsert);
                insertar.setString(1, "contador1");
                insertar.executeUpdate();
                insertar.close();
                System.out.println("Inserción exitosa");
        	}
            int cuenta = 0;
			for (int i=1; i<=1000; i++) {
				PreparedStatement consulta = conn.prepareStatement(SQL_CONSULTA);
				PreparedStatement actualiza = conn.prepareStatement(SQL_ACTUALIZA);
				ResultSet res = consulta.executeQuery();
				if (res.next()) cuenta = res.getInt(1) + 1;
				actualiza.setInt(1, cuenta);
				actualiza.executeUpdate();
			}
			System.out.println("Valor final: " + cuenta);
            conn.createStatement().execute("SHUTDOWN");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
