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

public class ControladorBajaJuguetes {

	private IFachada fachada;

	public ControladorBajaJuguetes(VentanaPrincipal vp) throws Exception {
		Properties p = new Properties();
		String nomArch = "src/config/config.properties";
		p.load(new FileInputStream(nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fachada";

		fachada = (IFachada) Naming.lookup(ruta);
	}

	public void BajarJuguetes(int _cedula) throws NiñosException, PersistenciaException, RemoteException, ConfigException {
		try {
			VONiño von = new VONiño(_cedula, null, null);
			fachada.BajaJuguetes(von);
		} catch(NiñosException ne) {
			throw ne;
		}
	}
}
