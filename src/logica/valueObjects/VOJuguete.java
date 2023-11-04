package logica.valueObjects;

public class VOJuguete {
	int numero;
	String descripcion;
	int cedulaNinio;
	
	public VOJuguete(int numero, String descripcion, int cedulaNinio) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.cedulaNinio = cedulaNinio;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getCedulaNinio() {
		return cedulaNinio;
	}
}
