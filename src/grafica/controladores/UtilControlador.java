package grafica.controladores;

import config.ConfigManager;

public class UtilControlador {
	
	public static String obtenerRutaServidor() throws Exception  {
		String ip = ConfigManager.getInstance().getProperty("ipServidor");
		String puerto = ConfigManager.getInstance().getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fachada";
		return ruta;
	}
}
