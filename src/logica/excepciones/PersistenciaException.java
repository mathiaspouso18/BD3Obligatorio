package logica.excepciones;

public class PersistenciaException {
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
			mensaje = "Error al recuperar el archivo";
			break;
		case 3:
			mensaje  = "El archivo no existe en disco";
			break;
		default:
			mensaje = "Error no especificado";
		}

		return mensaje;

	}
}
