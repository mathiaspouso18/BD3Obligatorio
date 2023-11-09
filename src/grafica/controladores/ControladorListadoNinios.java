package grafica.controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import config.ConfigException;
import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public class ControladorListadoNinios {
	private IFachada cap;

	public ControladorListadoNinios(VentanaPrincipal vp) throws Exception {
		Properties p = new Properties();
		String nomArch = "src/config/config.properties";
		p.load(new FileInputStream(nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fachada";

		cap = (IFachada) Naming.lookup(ruta);
	}

	public ArrayList<String[]> ListadoNinios() throws NiñosException, PersistenciaException, RemoteException {
		ArrayList<String[]> array = new ArrayList<String[]>();
		try {
			ArrayList<VONiño> arr = cap.ListarNiños();
			for (VONiño v : arr) {
				String[] data = new String[3];
				Integer cedula = v.getCedula();
				String nombre = v.getNombre();
				String apellido = v.getApellido();

				data[0] = String.valueOf(cedula);
				data[1] = nombre;
				data[2] = apellido;
				array.add(data);
			}
		} catch (NiñosException ne) {
			throw ne;
		}
		return array;
	}
}
