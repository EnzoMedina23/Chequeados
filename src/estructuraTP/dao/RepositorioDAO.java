package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Sugerencia;
import estructuraTP.vista.Repositorio;

public class RepositorioDAO extends MySQLC{


		public ArrayList<Sugerencia> consultar() {
			Connection c = conexion();
			ArrayList<Sugerencia> s = new ArrayList<Sugerencia>();
			try {

				Statement stmt = c.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT idSugerencia, frase, medioPublicacion, enlace, fecha FROM repositorio");
				
				while(rs.next()) {
					String idSugerencia= rs.getString("idSugerencia");
					int idSugerencia2 = Integer.parseInt(idSugerencia);
					String frase = rs.getString("frase");
					String Medio = rs.getString("medioPublicacion");
					String Enlace = rs.getString("enlace");
					java.sql.Date fecha = rs.getDate("fecha");
					Sugerencia l1 = new Sugerencia(idSugerencia2, frase, Medio, Enlace, fecha.toLocalDate());
					s.add(l1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				desconexion();
			}
			return s;
		}

		public void borrar(int id) {
			// TODO Auto-generated method stub
			Connection c = conexion();
			PreparedStatement preparedStatement;
			try {
				preparedStatement = c.prepareStatement("DELETE FROM repositorio WHERE idSugerencia = ?");
				
				preparedStatement.setInt(1, id);

				int row = preparedStatement.executeUpdate();
				System.out.println(row);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				desconexion();
			}
		}
	}


