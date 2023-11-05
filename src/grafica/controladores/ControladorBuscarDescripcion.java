package grafica.controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Properties;

import grafica.ventanas.VentanaPrincipal;
import logica.Fachada;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;

public class ControladorBuscarDescripcion{
	private Fachada cap;
	public ControladorBuscarDescripcion(VentanaPrincipal vp) throws Exception {
		
		//Properties p = new Properties();
		//String nomArch = "src/config/config.properties";
		//p.load (new FileInputStream (nomArch));
		//String ip = p.getProperty("ipServidor");
		//String puerto = p.getProperty("puertoServidor");
		//String ruta = "//" + ip + ":" + puerto + "/fachada";
		
		//cap = (ICapaLogica) Naming.lookup(ruta);
		cap = new Fachada();
	}
	
	public String BuscarDecripcion(int _cedula, int _numero) throws NiñosException, PersistenciaException, JuguetesException {
		String descripcion = "";
		try {
			descripcion = cap.DarDescripcion(_cedula, _numero);	
		} catch(JuguetesException je) {
			throw je;
		}
		return descripcion;
	}
}
