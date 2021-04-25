package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Articulo;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

public class ArticuloDAO extends MySQLC {
	public ArrayList<Articulo> consultarAlto() {
		Connection c = conexion();
		ArrayList<Articulo> s = new ArrayList<Articulo>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT  titulo, epigrafe, contenido, fechaCreacion FROM investigacion");
			
			while(rs.next()) {
				
				String Titulo = rs.getString("titulo");
				String Epigrafe = rs.getString("epigrafe");
				String Contenido = rs.getString("contenido");
				java.sql.Date fecha = rs.getDate("fechaCreacion");
				Investigacion l1 = new Investigacion(null, null, null,null,Titulo, Epigrafe, Contenido, fecha.toLocalDate());
				s.add(l1);
			}
			
			
			ResultSet rs2 = stmt.executeQuery("SELECT idExplicacion, titulo, epigrafe, contenido, fechaCreacion FROM explicacion");
			
			while(rs2.next()) {
				ArrayList<Chequeo> s2 = new ArrayList<Chequeo>();
				String idExplicaciones = rs2.getString("idExplicacion");
				PreparedStatement preparedStatement;

				preparedStatement = c.prepareStatement("select idChequeos from chequeo_explicacion where idExplicacion LIKE ?");
				
				preparedStatement.setInt(1, Integer.parseInt(idExplicaciones));
				
				ResultSet rs1 = preparedStatement.executeQuery();
while(rs1.next()) {
					int idChequeoss= rs1.getInt("idChequeos");
					PreparedStatement preparedStatement1;

					preparedStatement1 = c.prepareStatement("select idChequeos, frase, palabraClave, medioPublicacion, enlace, fecha, estado, idCategoria from chequeos where idChequeos LIKE ?");
					
					preparedStatement1.setInt(1,idChequeoss);
					
					ResultSet rs3 = preparedStatement1.executeQuery();
					while(rs3.next()) 
					{
						String idChequeo = rs3.getString("idChequeos");
						String Categoria = rs3.getString("idCategoria");
						String PalabraClave = rs3.getString("palabraClave");
						String Frase = rs3.getString("frase");
						String Medio = rs3.getString("medioPublicacion");
						String Enlace = rs3.getString("enlace");
						java.sql.Date fecha = rs3.getDate("fecha");
						Boolean estado = rs3.getBoolean("estado");
						Chequeo l1 = new Chequeo(idChequeo, Categoria, PalabraClave, Frase, Medio, Enlace, fecha.toLocalDate(), estado);
						s2.add(l1);
						
					}
					
					
				}
				String titul = rs2.getString("titulo");
				String epigraf = rs2.getString("epigrafe");
				String contenidoo = rs2.getString("contenido");
				java.sql.Date fechaa = rs2.getDate("fechaCreacion");
				Explicacion ex= new Explicacion(null, titul, epigraf, contenidoo, fechaa.toLocalDate());
				ex.setAsociados(s2);
				if(ex.altoImpacto())
					s.add(ex);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}

}

