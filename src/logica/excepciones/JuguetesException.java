package logica.excepciones;

public class JuguetesException {
	private int codigoError;

	public JuguetesException(int cod) {
		codigoError = cod;
	}

	public String getMensajePersistenciaExcep() {
		String mensaje = "";

		switch (codigoError) {
		case 1:
			mensaje = "El juguete ya fue asignado a este niño";
			break;
		case 2:
			mensaje = "El juguete no existe";
			break;
		case 3:
			mensaje  = "El niño no tiene juguetes asignados";
			break;
		case 4:
			mensaje  = "El niño no tiene juguete con ese numero";
			break;
		default:
			mensaje = "Error no especificado";
		}

		return mensaje;

	}
}
