package grafica.controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.Properties;

import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.JuguetesException;
import logica.valueObjects.VOJuguete;

public class ControladorAltaJuguetes {
	private IFachada cap;

	public ControladorAltaJuguetes(VentanaPrincipal vp) throws Exception {
		Properties p = new Properties();
		String nomArch = "src/config/config.properties";
		p.load(new FileInputStream(nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fachada";

		cap = (IFachada) Naming.lookup(ruta);
	}

	public void AltaJuguete(int _cedula, String _descripcion) throws JuguetesException, Exception {
		VOJuguete _voj;
		_voj = new VOJuguete(_cedula, _descripcion, _cedula);
		try {
			cap.AltaJuguete(_voj);
		} catch (JuguetesException je) {
			throw je;
		}
	}
}
