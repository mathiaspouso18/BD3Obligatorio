package grafica.controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.Properties;

import grafica.ventanas.VentanaPrincipal;

public class ControladorListadoJuguetes{
	public ControladorListadoJuguetes(VentanaPrincipal vp) throws Exception {
		
		//Properties p = new Properties();
		//String nomArch = "src/config/config.properties";
		//p.load (new FileInputStream (nomArch));
		//String ip = p.getProperty("ipServidor");
		//String puerto = p.getProperty("puertoServidor");
		//String ruta = "//" + ip + ":" + puerto + "/fachada";
		
		//cap = (ICapaLogica) Naming.lookup(ruta);
	}
	
	public void ListadoJuguetes(int _cedula) {
		
	}
}