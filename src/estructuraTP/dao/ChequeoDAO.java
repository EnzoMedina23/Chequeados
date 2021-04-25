package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import estructuraTP.modelo.Chequeo;

public class ChequeoDAO extends MySQLC{

	public void guardar(Chequeo l) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO chequeos (palabraClave,frase,medioPublicacion, enlace, fecha, estado, idCategoria) VALUES (?,?,?,?,?,?,?)");
		
			preparedStatement.setString(1, l.getClave());
			preparedStatement.setString(2, l.getfrase());
			preparedStatement.setString(3, l.getmedio());
			preparedStatement.setString(4, l.getenlace());
			preparedStatement.setDate(5, java.sql.Date.valueOf(l.getfecha()));
			preparedStatement.setBoolean(6, l.getestado());
			preparedStatement.setString(7, l.getcategoria());

		    
			int row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
	}
		
		public ArrayList<Chequeo> consultar() {
			Connection c = conexion();
			ArrayList<Chequeo> s = new ArrayList<Chequeo>();
			try {

				Statement stmt = c.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT idChequeos, palabraClave, frase, medioPublicacion, enlace, fecha, estado, idCategoria FROM chequeos");
				
				while(rs.next()) {
					String idChequeo = rs.getString("idChequeos");
					String Categoria = rs.getString("idCategoria");
					String PalabraClave = rs.getString("palabraClave");
					String Frase = rs.getString("frase");
					String Medio = rs.getString("medioPublicacion");
					String Enlace = rs.getString("enlace");
					java.sql.Date fecha = rs.getDate("fecha");
					Boolean estado = rs.getBoolean("estado");
					Chequeo l1 = new Chequeo(idChequeo, Categoria, PalabraClave, Frase, Medio, Enlace, fecha.toLocalDate(), estado);
					s.add(l1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				desconexion();
			}
			return s;
		}

		public void modificar(Chequeo ch, int valor) {
			Connection c = conexion();
			PreparedStatement preparedStatement;
			try {
				preparedStatement = c.prepareStatement(" UPDATE chequeos SET palabraClave= ?, estado = ?, idCategoria= ? WHERE idChequeos = ? ");
				preparedStatement.setString(1,   ch.getClave());
				preparedStatement.setBoolean(2,  ch.getestado());
				preparedStatement.setString(3,  ch.getcategoria());
				preparedStatement.setInt(4,  valor);
				int row = preparedStatement.executeUpdate();
				System.out.println(row);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				desconexion();
			}
		}
		
		public void borrar(int id) {
			Connection c = conexion();
			PreparedStatement preparedStatement;
			try {
				preparedStatement = c.prepareStatement("DELETE FROM chequeos WHERE idChequeos = ?");
				
				preparedStatement.setInt(1, id);

				int row = preparedStatement.executeUpdate();
				System.out.println(row);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				desconexion();
			}
		}
		
		public ArrayList<Chequeo> consultarBusqueda(String clave) {
			Connection c = conexion();
			ArrayList<Chequeo> s = new ArrayList<Chequeo>();
			try {						
				PreparedStatement preparedStatement;

				preparedStatement = c.prepareStatement("SELECT idChequeos, idCategoria, palabraClave, frase, medioPublicacion, enlace, fecha, estado FROM chequeos WHERE palabraClave LIKE ?");
				
				preparedStatement.setString(1, clave+"%");
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					
					String idChequeo = rs.getString("idChequeos");
					String Categoria = rs.getString("idCategoria");
					String PalabraClave = rs.getString("palabraClave");
					String Frase = rs.getString("frase");
					String Medio = rs.getString("medioPublicacion");
					String Enlace = rs.getString("enlace");
					java.sql.Date fecha = rs.getDate("fecha");
					Boolean estado = rs.getBoolean("estado");
					Chequeo l1 = new Chequeo(idChequeo, Categoria, PalabraClave, Frase, Medio, Enlace, fecha.toLocalDate(), estado);
					s.add(l1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				desconexion();
			}
			return s;
		}
	}

