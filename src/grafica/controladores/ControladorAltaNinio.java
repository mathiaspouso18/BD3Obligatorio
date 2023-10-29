package grafica.controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.Properties;

import grafica.ventanas.VentanaPrincipal;

public class ControladorAltaNinio{
	public ControladorAltaNinio(VentanaPrincipal vp) throws Exception {
		
		//Properties p = new Properties();
		//String nomArch = "src/config/config.properties";
		//p.load (new FileInputStream (nomArch));
		//String ip = p.getProperty("ipServidor");
		//String puerto = p.getProperty("puertoServidor");
		//String ruta = "//" + ip + ":" + puerto + "/fachada";
		
		//cap = (ICapaLogica) Naming.lookup(ruta);
	}
	
	public void AltaNinio(int _cedula, String _nombre, String _apellido) {
		
	}
}