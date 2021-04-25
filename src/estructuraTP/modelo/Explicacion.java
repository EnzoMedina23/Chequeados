package estructuraTP.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Explicacion extends Articulo {

	String idExplicacion;
	private ArrayList<Chequeo> chequeosAso = new ArrayList<Chequeo>();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public LocalDate getFecha() {
		return fecha_creacion;
	}

	public void setFecha_creacion(LocalDate fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getIdExplicacion() {
		return idExplicacion;
	}

	public void setIdExplicacion(String idExplicacion) {
		this.idExplicacion = idExplicacion;
	}

	public Explicacion(String idExplicacion, String titulo, String epigrafe, String contenido,
			LocalDate fecha_creacion) {
		this.idExplicacion = idExplicacion;
		this.titulo = titulo;
		this.epigrafe = epigrafe;
		this.contenido = contenido;
		this.fecha_creacion = fecha_creacion;
	}

	public String getEpigrafe() {
		// TODO Auto-generated method stub
		return epigrafe;
	}

	@Override
	public boolean altoImpacto() {
		// TODO Auto-generated method stub
		return this.chequeosAso.size() > 3;
	}

	public void setAsociados(ArrayList<Chequeo> s2) {
		chequeosAso= s2;
		
	}

}
