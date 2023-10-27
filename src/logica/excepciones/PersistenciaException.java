package logica.excepciones;

public class PersistenciaException extends Exception {
	private static final long serialVersionUID = 1L;
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
			mensaje = "Error al ejecutando la sentencia";
			break;
		default:
			mensaje = "Error no especificado";
		}

		return mensaje;
	}
}
