package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Investigacion;

public class InvestigacionDAO extends MySQLC {

	public void guardar(Investigacion l) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO investigacion (idCategoria,tema,palabrasClaves,titulo, epigrafe, contenido, fechaCreacion) VALUES (?,?,?,?,?,?,?)");
		
			preparedStatement.setString(1, l.getcategoria());
			preparedStatement.setString(2, l.gettema());
			preparedStatement.setString(3, l.getclave1());
			preparedStatement.setString(4, l.getTitulo());
			preparedStatement.setString(5, l.getEpigrafe());
			preparedStatement.setString(6, l.getContenido());
			preparedStatement.setDate(7, java.sql.Date.valueOf(l.getFecha()));
		    
			int row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
	}
	
	public ArrayList<Investigacion> consultar() {
		Connection c = conexion();
		ArrayList<Investigacion> s = new ArrayList<Investigacion>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT idInvestigacion, idCategoria, tema, palabrasClaves, titulo, epigrafe, contenido, fechaCreacion FROM investigacion");
			
			while(rs.next()) {
				String idInvestigaciones = rs.getString("idInvestigacion");
				String Categoria = rs.getString("idCategoria");
				String Tema = rs.getString("tema");
				String PalabraClave = rs.getString("palabrasClaves");
				String Titulo = rs.getString("titulo");
				String Epigrafe = rs.getString("epigrafe");
				String Contenido = rs.getString("contenido");
				java.sql.Date fecha = rs.getDate("fechaCreacion");
				Investigacion l1 = new Investigacion(idInvestigaciones, Categoria, Tema, PalabraClave, Titulo, Epigrafe, Contenido, fecha.toLocalDate());
				s.add(l1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}

	public void borrar(int valor) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("DELETE FROM investigacion WHERE idInvestigacion = ?");
			
			preparedStatement.setInt(1, valor);

			int row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		
	}


		public ArrayList<Investigacion> consultarBusqueda(String clave) {
			Connection c = conexion();
			ArrayList<Investigacion> s = new ArrayList<Investigacion>();
			try {						
				PreparedStatement preparedStatement;

				preparedStatement = c.prepareStatement("SELECT idInvestigacion, idCategoria, tema, palabrasClaves, titulo, epigrafe, contenido, fechaCreacion FROM investigacion WHERE tema LIKE ?");
				
				preparedStatement.setString(1, clave+"%");
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					
					String idInvestigacion = rs.getString("idInvestigacion");
					String idCategoria = rs.getString("idCategoria");
					String tema1 = rs.getString("tema");
					String palabrasClaves = rs.getString("palabrasClaves");
					String titulo = rs.getString("titulo");
					String epigrafe = rs.getString("epigrafe");
					String contenido = rs.getString("contenido");
					java.sql.Date fechaCreacion = rs.getDate("fechaCreacion");
					Investigacion l1 = new Investigacion(idInvestigacion, idCategoria, tema1, palabrasClaves, titulo, epigrafe, contenido, fechaCreacion.toLocalDate());
					s.add(l1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				desconexion();
			}
			return s;
		}

		public void modificar(Investigacion c) {
			Connection c1 = conexion();
			PreparedStatement preparedStatement;
			try {
				preparedStatement = c1.prepareStatement(" UPDATE investigacion SET idCategoria= ?, tema = ?, palabrasClaves= ?,  titulo = ?, epigrafe = ?, contenido = ? WHERE idInvestigacion = ? ");
				preparedStatement.setString(1,   c.getcategoria());
				preparedStatement.setString(2,  c.gettema());
				preparedStatement.setString(3, c.getclave1());
				preparedStatement.setString(4, c.getTitulo());
				preparedStatement.setString(5, c.getEpigrafe());
				preparedStatement.setString(6, c.getContenido());
				preparedStatement.setInt(7,  Integer.parseInt(c.getid()));
				int row = preparedStatement.executeUpdate();
				System.out.println(row);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				desconexion();
			}
			
		}
	}


