package persistencia.daos;

import java.io.File;

public class UtilArchivo {
	
	public static String obtenerRuta() {
		File archivoActual = new File(".");
		return archivoActual.getAbsolutePath().replace(".","PersistenciaArchivos/");
		
	}
}
