package estructuraTP.modelo;

import java.time.LocalDate;

public class Sugerencia {

	
	int idSugerencia;
	String frase;
	String medio;
	String enlace;
	LocalDate  fecha;
	
	public Sugerencia(int idSugerencia2, String frase2, String medio2, String enlace3,
			LocalDate localDate) {
		idSugerencia= idSugerencia2;
		frase= frase2;
		medio=medio2;
		enlace=enlace3;
		fecha= localDate;
	}

	public int getid() {
		// TODO Auto-generated method stub
		return idSugerencia;
	}

	public String getfrase() {
		// TODO Auto-generated method stub
		return frase;
	}

	public String getmedio() {
		// TODO Auto-generated method stub
		return medio;
	}

	public String getenlace() {
		// TODO Auto-generated method stub
		return enlace;
	}

	public LocalDate getfecha() {
		// TODO Auto-generated method stub
		return fecha;
	}
}
