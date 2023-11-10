package logica.valueObjects;

import java.io.Serializable;

public class VONiño implements Serializable {
	int cedula;
	String nombre;
	String apellido;

	public VONiño() { }

	public VONiño(int cedula, String nombre, String apellido) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
}
