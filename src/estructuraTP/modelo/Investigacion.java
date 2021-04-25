package estructuraTP.modelo;

import java.time.LocalDate;
import java.util.Date;

public class Investigacion extends Articulo {
	String idInvestigacion;
	String categoria;
	String tema;
	String palabras_claves;

	public Investigacion(String idInvestigaciones, String categoria, String tema, String palabras_claves, String titulo,
			String epigrafe, String contenido, LocalDate fecha_creacion) {
		this.idInvestigacion = idInvestigaciones;
		this.categoria = categoria;
		this.tema = tema;
		this.palabras_claves = palabras_claves;
		this.titulo = titulo;
		this.epigrafe = epigrafe;
		this.contenido = contenido;
		this.fecha_creacion = fecha_creacion;
	}

	public String gettema() {
		// TODO Auto-generated method stub
		return tema;
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return titulo;
	}

	public String getEpigrafe() {
		// TODO Auto-generated method stub
		return epigrafe;
	}

	public String getContenido() {
		// TODO Auto-generated method stub
		return contenido;
	}

	public String getclave1() {
		// TODO Auto-generated method stub
		return palabras_claves;
	}

	public LocalDate getFecha() {
		// TODO Auto-generated method stub
		return fecha_creacion;
	}

	public String getcategoria() {
		// TODO Auto-generated method stub
		return categoria;
	}

	public String getid() {
		return idInvestigacion;
	}

	@Override
	public boolean altoImpacto() {
		// TODO Auto-generated method stub
		return true;
	}

}
