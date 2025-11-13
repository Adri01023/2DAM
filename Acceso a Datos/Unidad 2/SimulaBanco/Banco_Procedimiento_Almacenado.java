import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.mysql.cj.jdbc.CallableStatement;

public class Banco_Procedimiento_Almacenado {
	private final int saldoInicial;
	private final int númeroDeCuentas;
	private boolean abierto;
	private Connection conexión;

	public Banco_Procedimiento_Almacenado(int numCuentas, int saldoInicial) {
		this.abierto = true;
		this.saldoInicial = saldoInicial;
		this.númeroDeCuentas = numCuentas;

		try {
			conexión = DriverManager.getConnection("jdbc:mysql://localhost/adat1?allowPublicKeyRetrieval=true", "dam2",
					"asdf.1234");
			// Inicializa la base de datos de cuentas:
			Statement sql = conexión.createStatement();
			sql.execute("DROP TABLE IF EXISTS cuentas ");
			sql.execute("create table cuentas(id int primary key, saldo float)");
			for (int i = 0; i < numCuentas; i++) {
				sql.execute(String.format("insert into cuentas values(%d,%d)", i, saldoInicial));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			conexión = null;
			this.abierto = false;
		}
	}

	public void transfiere(int origen, int destino, int cantidad, Connection conexiónHilo) {
		
		String sql_procedure = """
				CREATE PROCEDURE transfiere(IN origen int, IN destino int, IN cantidad int, OUT mensajeSalida varchar(80))
				BEGIN
				SET mensajeSalida = "Todo ha salido bien";
				IF (SELECT saldo from cuentas where id = origen) > cantidad THEN
				UPDATE cuentas SET saldo = saldo + cantidad where id = destino;
				UPDATE cuentas SET saldo = saldo - cantidad where id = origen;
				ELSE
				SET mensajeSalida = concat("No se han podido transferir ",cantidad," desde ",origen," a ",destino," por falta de fondos.");
				END IF;
				IF (SELECT saldo from cuentas where id = origen) < 0 THEN
				SET mensajeSalida = concat("Descubierto en cuenta ",origen," saldo: ",(SELECT saldo from cuentas where id = origen));
				END IF;
				END
				""";
		try {
			ResultSet rs = conexiónHilo.getMetaData().getProcedures(null, "PUBLIC", "transfiere");
			if (!rs.next()) {
				PreparedStatement procedure = conexiónHilo.prepareStatement(sql_procedure);
				procedure.executeUpdate();
			}
			java.sql.CallableStatement llamar_procedimiento = conexiónHilo.prepareCall("{call transfiere(?,?,?,?)}");
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
		Statement sql = conexión.createStatement();
		ResultSet res = sql.executeQuery("SELECT SUM(saldo) FROM cuentas");
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
