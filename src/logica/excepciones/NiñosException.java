package logica.excepciones;

public class NiñosException extends Exception {
	private int codigoError;

	public NiñosException(int cod) {
		codigoError = cod;
	}

	public String getMensajeNiñosExcep() {
		String mensaje = "";

		switch (codigoError) {
		case 1:
			mensaje = "No se pudo insertar: El niño ya existe";
			break;
		case 2:
			mensaje = "El niño con la cedula ingresada no existe";
			break;
		case 3:
			mensaje  = "No hay niños en la lista";
			break;
		default:
			mensaje = "Error no especificado";
		}
		
		return mensaje;
	}
}
