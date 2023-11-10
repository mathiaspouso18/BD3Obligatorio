package logica.excepciones;

public class PersistenciaException extends Exception {
	private int codigoError;

	public PersistenciaException(int cod) {
		codigoError = cod;
	}

	public String getMensajePersistenciaExcep() {
		String mensaje = "";
		switch (codigoError) {
			case 1:
				mensaje = "Error al generar la conexión";
				break;
			case 2:
				mensaje = "Error al cerrar la conexión";
				break;
			case 3:
				mensaje = "Error al obtener datos";
				break;
			default:
				mensaje = "La aplicación ha encontrado un error desconocido";
		}
		return mensaje;
	}
}
