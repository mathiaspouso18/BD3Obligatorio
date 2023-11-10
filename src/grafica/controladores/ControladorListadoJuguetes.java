package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;

public class ControladorListadoJuguetes {
	private IFachada cap;

	public ControladorListadoJuguetes(VentanaPrincipal vp) throws JuguetesException, FileNotFoundException, IOException, NotBoundException {
		Properties p = new Properties();
		String nomArch = "src/config/config.properties";
		p.load(new FileInputStream(nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fachada";
		cap = (IFachada) Naming.lookup(ruta);
	}

	public ArrayList<String[]> ListadoJuguetes(int _cedula) throws JuguetesException, NiñosException, PersistenciaException, RemoteException {
		ArrayList<String[]> array = new ArrayList<String[]>();
		try {
			ArrayList<VOJuguete> arr = cap.ListarJuguetes(_cedula);
			for (VOJuguete v : arr) {
				String[] data = new String[3];
				Integer numero = v.getNumero();
				Integer cedula = v.getCedulaNinio();
				String descripcion = v.getDescripcion();
				data[0] = String.valueOf(numero);
				data[1] = String.valueOf(cedula);
				data[2] = descripcion;
				array.add(data);
			}
		} catch (JuguetesException je) {
			throw je;
		}
		return array;
	}
}
