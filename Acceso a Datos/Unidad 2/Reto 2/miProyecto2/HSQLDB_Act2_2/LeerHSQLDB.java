package HSQLDB_Act2_2;

import java.sql.*;

public class LeerHSQLDB {
    public static void main(String[] args) {
        // URL de la base de datos (relativa al directorio actual)
        String url = "jdbc:hsqldb:file:miBase/prueba";
        String user = "SA";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 1. Cargar el driver JDBC de HSQLDB
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            System.out.println("Conectado a la base de datos HSQLDB.");

            // 2. Desactivar auto-commit para controlar transacciones
            conn.setAutoCommit(false);

            try (Statement st = conn.createStatement()) {
                // 3. Crear tabla si no existe con ID auto-generado
                st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS PERSONA (" +
                    "ID INT PRIMARY KEY, " +
                    "NOMBRE VARCHAR(50), " +
                    "EDAD INT)"
                );

                // 4. Insertar datos siempre (ID se genera automáticamente)
                st.executeUpdate("INSERT INTO PERSONA (ID, NOMBRE, EDAD) VALUES (3, 'Ana', 25)");
                st.executeUpdate("INSERT INTO PERSONA (ID, NOMBRE, EDAD) VALUES (4, 'Luis', 30)");

                // 5. Confirmar transacción
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

            // 6. Consultar y mostrar los datos
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM PERSONA")) {

                System.out.println("Datos de la tabla PERSONA:");
                while (rs.next()) {
                    System.out.println(
                        "ID=" + rs.getInt("ID") +
                        " | Nombre=" + rs.getString("NOMBRE") +
                        " | Edad=" + rs.getInt("EDAD")
                    );
                }
            }

            System.out.println("Conexión cerrada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

