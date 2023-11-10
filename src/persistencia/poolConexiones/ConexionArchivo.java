package persistencia.poolConexiones;

public class ConexionArchivo implements IConexion {
	private boolean escritura;
	
	public ConexionArchivo(boolean escritura) {
		this.escritura = escritura;
	}

	public boolean getEscritura() {
		return escritura;
	}

	public void setEscritura(boolean escritura) {
		this.escritura = escritura;
	}
}
