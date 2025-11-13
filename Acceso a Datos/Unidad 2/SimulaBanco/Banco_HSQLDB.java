import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.sql.CallableStatement;

public class Banco_HSQLDB {
	private final int saldoInicial;
	private final int númeroDeCuentas;
	private boolean abierto;
	private Connection conexión;

	public Banco_HSQLDB(int numCuentas, int saldoInicial) {
		this.abierto = true;
		this.saldoInicial = saldoInicial;
		this.númeroDeCuentas = numCuentas;

		try {
			conexión = DriverManager.getConnection("jdbc:hsqldb:file:bancoHSQLDB;shutdown=true", "SA", "");
			// Inicializa la base de datos de cuentas:
			Statement sql = conexión.createStatement();
			//sql.execute("DROP TABLE IF EXISTS cuentas ");
			//sql.execute("create table cuentas(id int primary key, saldo float)");
			// En constructor, después de crear la tabla
			Statement st = conexión.createStatement();
			st.execute("DROP PROCEDURE IF EXISTS TRANSFIERE"); // opcional en desarrollo
			st.execute("""
			    CREATE PROCEDURE TRANSFIERE(IN origen INT, IN destino INT, IN cantidad INT, OUT mensajeSalida VARCHAR(80))
			    MODIFIES SQL DATA
BEGIN ATOMIC
    DECLARE saldo_origen INT;
    DECLARE saldo_destino INT;
    SET mensajeSalida = 'Todo ha salido bien';

    SET saldo_origen = (SELECT saldo FROM CUENTAS WHERE id = origen);
    SET saldo_destino = (SELECT saldo FROM CUENTAS WHERE id = destino);

    IF saldo_origen >= cantidad THEN
        UPDATE CUENTAS SET saldo = saldo + cantidad WHERE id = destino;
        UPDATE cuentas SET saldo = saldo - cantidad WHERE id = origen;
        SET saldo_origen = saldo_origen - cantidad; -- actualizamos la variable
    ELSE
        SET mensajeSalida = CONCAT('No se han podido transferir ', cantidad,
                                   ' desde ', origen, ' a ', destino,
                                   ' por falta de fondos. Saldo=', saldo_origen);
    END IF;

    IF saldo_origen < 0 THEN
        SET mensajeSalida = CONCAT('Descubierto en cuenta ', origen, ' saldo: ', saldo_origen);
    END IF;
END
			""");
			st.close();

			for (int i = 0; i < numCuentas; i++) {
				sql.execute(String.format("insert into cuentas values(%d,%d)", i, saldoInicial));
			}
			ResultSet res = sql.executeQuery("SELECT id, saldo FROM cuentas ORDER BY id");
			while (res.next()) {
			    System.out.printf("Cuenta %d: saldo %.2f%n", res.getInt(1), res.getFloat(2));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			conexión = null;
			this.abierto = false;
		}
	}

	public void transfiere(int origen, int destino, int cantidad, Connection conexiónHilo) {
		try {
		/*ResultSet rs = conexiónHilo.getMetaData().getProcedures(null, "PUBLIC", "TRANSFIERE");
			if (!rs.next()) {
			    Statement st = conexiónHilo.createStatement();
			    st.execute("""
			        CREATE PROCEDURE TRANSFIERE(IN origen INT, IN destino INT, IN cantidad INT, OUT mensajeSalida VARCHAR(80))
			        MODIFIES SQL DATA
			        BEGIN ATOMIC
			            DECLARE id_min INT;
			            DECLARE id_max INT;
			            SET mensajeSalida = 'Todo ha salido bien';
			            IF (SELECT saldo FROM cuentas WHERE id = origen) > cantidad THEN
			                UPDATE cuentas SET saldo = saldo + cantidad WHERE id = destino;
			                UPDATE cuentas SET saldo = saldo - cantidad WHERE id = origen;
			            ELSE
			                SET mensajeSalida = CONCAT('No se han podido transferir ', cantidad, ' desde ', origen, ' a ', destino, ' por falta de fondos.');
			            END IF;
			            IF (SELECT saldo FROM cuentas WHERE id = origen) < 0 THEN
			                SET mensajeSalida = CONCAT('Descubierto en cuenta ', origen, ' saldo: ', (SELECT saldo FROM cuentas WHERE id = origen));
			            END IF;
			        END
			    """);
			    st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} try {*/
			CallableStatement llamar_procedimiento = conexiónHilo.prepareCall("{call transfiere(?,?,?,?)}");
			llamar_procedimiento.setInt(1, origen);
			llamar_procedimiento.setInt(2, destino);
			llamar_procedimiento.setInt(3, cantidad);
			llamar_procedimiento.registerOutParameter(4, Types.VARCHAR);
			llamar_procedimiento.execute();
			String mensaje = llamar_procedimiento.getString(4);
			if (mensaje.equals("Todo ha salido bien")) {
				
			} else {
				System.err.println(mensaje);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void comprueba() throws SQLException {
		int saldoTotal = 0;
		PreparedStatement sql = conexión.prepareStatement("SELECT SUM(saldo) FROM cuentas");
		ResultSet res = sql.executeQuery();
		if (res.next()) {
			saldoTotal = (int) res.getFloat(1);
			if (saldoTotal != (númeroDeCuentas * saldoInicial)) {
				System.err.println("¡¡¡¡¡No cuadran las cuentas!!!! saldo total " + saldoTotal);
			} else {
				System.out.println("Balance correcto");
			}
		}
		
		res = sql.executeQuery("SELECT id FROM cuentas WHERE saldo<0");
		while (res.next()) {
			System.err.println("DESCUBIERTO en cuenta " + res.getInt(1));
		}
		

		/*
		 * Detallando por cuenta: ResultSet res =
		 * sql.executeQuery("SELECT id,saldo FROM cuentas"); while (res.next()) { int
		 * saldo = (int) res.getFloat(2); saldoTotal += saldo;
		 * System.out.printf("Cuenta %d , saldo %d, parcial %d\n", res.getInt(1),
		 * saldo,* saldoTotal); }
		 */

	} // comprueba

	public int getNúmeroDeCuentas() {
		return númeroDeCuentas;
	}

	boolean abierto() {
		return abierto;
	}

	void cierraBanco() {
		abierto = false;

	}

	void cierraConexiónBD() {
		try {
			conexión.close();
		} catch (SQLException e) {
			System.err.println("Error cerrando conexión de BBDD " + e.getMessage());
		}
	}
}
