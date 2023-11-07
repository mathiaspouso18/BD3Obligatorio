package grafica.controladores;
import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

import config.ConfigException;
import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public class ControladorAltaNinio{
	private IFachada cap;
	
	public ControladorAltaNinio(VentanaPrincipal vp) throws Exception {
		Properties p = new Properties();
		String nomArch = "src/config/config.properties";
		p.load (new FileInputStream (nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fachada";
				
		cap = (IFachada) Naming.lookup(ruta);
	}
	
	public void AltaNinio(int _cedula, String _nombre, String _apellido) throws NiñosException, PersistenciaException, RemoteException, ConfigException {
		VONiño _von;
		_von = new VONiño(_cedula,_nombre,_apellido);
		
		try {
			cap.AltaNiño(_von);
		} catch(NiñosException ve) {
			throw ve;
		}
	}
}
