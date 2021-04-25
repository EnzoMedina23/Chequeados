package estructuraTP.modelo;

import java.time.LocalDate;
import java.util.Date;

//import java.sql.Date;

public class Chequeo {
	String idChequeo;
	String Categoria;
	String PalabraClave;
	String Frase;
	String Medio;
	String Enlace;
	LocalDate fecha;
	boolean estado;

	public Chequeo(String idChequeo, String Categoria, String PalabraClave, String Frase, String Medio, String Enlace,
			LocalDate fecha, boolean estado) {
		this.idChequeo = idChequeo;
		this.Categoria = Categoria;
		this.PalabraClave = PalabraClave;
		this.Frase = Frase;
		this.Medio = Medio;
		this.Enlace = Enlace;
		this.fecha = fecha;
		this.estado = estado;
	}

	public String getid() {
		return idChequeo;
	}

	public String getClave() {
		// TODO Auto-generated method stub
		return PalabraClave;
	}

	public String getfrase() {
		// TODO Auto-generated method stub
		return Frase;
	}

	public String getmedio() {
		// TODO Auto-generated method stub
		return Medio;
	}

	public String getenlace() {
		// TODO Auto-generated method stub
		return Enlace;
	}

	public LocalDate getfecha() {
		// TODO Auto-generated method stub
		return fecha;
	}

	public boolean getestado() {
		// TODO Auto-generated method stub
		return estado;
	}

	public String getcategoria() {
		// TODO Auto-generated method stub
		return Categoria;
	}

	public void modificar(String clave, String categoria1, boolean estad) {
		this.PalabraClave = clave;
		this.Categoria = categoria1;
		this.estado = estad;
	}
	public String getTitulo() {
		// TODO Auto-generated method stub
		return null;
	}


}
