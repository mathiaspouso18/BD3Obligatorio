package config;

public class ConfigException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigoError;

	public ConfigException(int cod) {
		codigoError = cod;
	}
	
	public String getMensajeNiñosExcep() {
		String mensaje = "";

		switch (codigoError) {
		case 1:
			mensaje = "Error al cargar el archivo de configuración";
			break;
		case 2:
			mensaje = "Formato inválido, la clave solicitada no es de tipo int";
			break;
		default:
			mensaje = "Error no especificado";
		}
		
		return mensaje;
	}

}
