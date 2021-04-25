package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLC {
	String u = "root";
	String p = "4357";
	String url = "jdbc:mysql://localhost:3306/chequeadosTP?useSSL=false";
	Connection c = null;
	
	public Connection conexion() {
		try {
			if (c == null) {
				c =  DriverManager.getConnection(url, u, p);
			} else {
				// TODO Verificar que la conexión este abierta
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public void desconexion() {
		if (c != null) {
			try {
				c.close();
				c = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
