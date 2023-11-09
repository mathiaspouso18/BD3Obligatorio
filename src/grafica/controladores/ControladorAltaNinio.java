package grafica.controladores;

import java.rmi.Naming;
import java.rmi.RemoteException;

import config.ConfigException;
import config.ConfigManager;
import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public class ControladorAltaNinio {
	private IFachada cap;

	public ControladorAltaNinio(VentanaPrincipal vp) throws Exception {
		String ip = ConfigManager.getInstance().getProperty("ipServidor");
		String puerto = ConfigManager.getInstance().getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fachada";

		cap = (IFachada) Naming.lookup(ruta);
	}

	public void AltaNinio(int _cedula, String _nombre, String _apellido) throws NiñosException, PersistenciaException, RemoteException, ConfigException {
		VONiño _von;
		_von = new VONiño(_cedula, _nombre, _apellido);

		try {
			cap.AltaNiño(_von);
		} catch (NiñosException ve) {
			throw ve;
		}
	}
}
