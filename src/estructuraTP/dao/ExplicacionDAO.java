package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

public class ExplicacionDAO extends MySQLC {


	public void guardar(Explicacion l, String enlaccc) {
		Connection c = conexion();
		String idCheque= "";
		String idExplicaciones="";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO explicacion (titulo, epigrafe,contenido,FechaCreacion) VALUES (?,?,?,?)");
		
			preparedStatement.setString(1, l.getTitulo());
			preparedStatement.setString(2, l.getEpigrafe());
			preparedStatement.setString(3, l.getContenido());
			preparedStatement.setDate(4, java.sql.Date.valueOf(l.getFecha()));

		   
			int row = preparedStatement.executeUpdate();
			PreparedStatement stmt;
			
			stmt = c.prepareStatement("SELECT idExplicacion FROM explicacion where FechaCreacion LIKE ? AND titulo LIKE ? and epigrafe like ? and contenido like ?");
			stmt.setDate(1, java.sql.Date.valueOf(l.getFecha()));
			stmt.setString(2, l.getTitulo());
			stmt.setString(3, l.getEpigrafe());
			stmt.setString(4, l.getContenido());
			
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				 idExplicaciones = rs.getString("idExplicacion");
			}
			
			PreparedStatement stmt1;
			
			stmt1 = c.prepareStatement("SELECT idChequeos FROM chequeos where enlace LIKE ? ");
			stmt1.setString(1, enlaccc);
			
			ResultSet rs2 = stmt1.executeQuery();
			while(rs2.next()) {
				 idCheque = rs2.getString("idChequeos");
				 PreparedStatement preparedStatement1;
					try {
						preparedStatement1 = c.prepareStatement("insert into chequeo_explicacion(idChequeos, idExplicacion) values(?,?) ");
						preparedStatement1.setInt(1,  Integer.parseInt(idCheque));
						preparedStatement1.setInt(2,  Integer.parseInt(idExplicaciones));
						int row1 = preparedStatement1.executeUpdate();
						System.out.println(row1);
					}
						finally {
						}
					}
			
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		
	}
	
	public ArrayList<Explicacion> consultar() {
		Connection c = conexion();
		ArrayList<Explicacion> s = new ArrayList<Explicacion>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT idExplicacion, titulo, epigrafe, contenido, fechaCreacion FROM explicacion");
			
			while(rs.next()) {
				String idExplicaciones = rs.getString("idExplicacion");
				String Epigrafe= rs.getString("epigrafe");
				String Titulo = rs.getString("titulo");
				String Contenido = rs.getString("contenido");
				java.sql.Date fecha = rs.getDate("fechaCreacion");
				Explicacion l1 = new Explicacion(idExplicaciones, Titulo, Epigrafe, Contenido, fecha.toLocalDate());
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
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("DELETE FROM explicacion WHERE idExplicacion = ?");
			
			preparedStatement.setInt(1, id);

			int row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
	}

	public void modificar(Explicacion ch) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(" UPDATE explicacion SET titulo= ?, epigrafe = ?, contenido= ? WHERE idExplicacion = ? ");
			preparedStatement.setString(1,   ch.getTitulo());
			preparedStatement.setString(2,  ch.getEpigrafe());
			preparedStatement.setString(3,  ch.getContenido());
			preparedStatement.setInt(4,  Integer.parseInt(ch.getIdExplicacion()));
			int row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			desconexion();
		}
	}

	public ArrayList<Explicacion> altoImpac() {
		Connection c = conexion();
		ArrayList<Explicacion> s = new ArrayList<Explicacion>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT idExplicacion, titulo, epigrafe, contenido, fechaCreacion FROM explicacion");
			
			while(rs.next()) {
				String idExplicaciones = rs.getString("idExplicacion");
				PreparedStatement preparedStatement;

				preparedStatement = c.prepareStatement("select e.titulo, e.epigrafe, e.contenido, e.fechaCreacion from explicacion as e inner join chequeos as c on e.idExplicacion = c.idExplicacion where c.idExplicacion LIKE ? group by c.idExplicacion having count(*) >3");
				
				preparedStatement.setString(1, idExplicaciones+"%");
				
				ResultSet rs1 = preparedStatement.executeQuery();
while(rs1.next()) {
					
					
					String titulo = rs.getString("titulo");
					String epigrafe = rs.getString("epigrafe");
					String contenido = rs.getString("contenido");
					java.sql.Date fechaCreacion = rs.getDate("fechaCreacion");
					Explicacion l1 = new Explicacion(null,titulo, epigrafe, contenido, fechaCreacion.toLocalDate());
					s.add(l1);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}

}
