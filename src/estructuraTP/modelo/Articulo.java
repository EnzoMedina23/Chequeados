package estructuraTP.modelo;

import java.time.LocalDate;

public abstract class Articulo {
	String titulo;
	String epigrafe;
	String contenido;
	LocalDate fecha_creacion;
	
	public String getTitulo() {
		return titulo;
	}
	
	public abstract String getEpigrafe();
	public abstract String getContenido();
	public abstract LocalDate getFecha();
	
	public abstract boolean altoImpacto();
	
	public void setTitulo(String titulo)
	{
		this.titulo= titulo;
	}
	
	public void setEpigrafe(String epigrafe)
	{
		this.epigrafe = epigrafe;
	}
	
	public void setContenido(String contenido)
	{
		this.contenido = contenido;
	}
	
	public void setFecha(LocalDate fecha)
	{
		this.fecha_creacion= fecha;
	}
	
}
