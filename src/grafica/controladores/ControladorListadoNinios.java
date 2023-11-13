package grafica.controladores;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public class ControladorListadoNinios {
	private IFachada cap;

	public ControladorListadoNinios(VentanaPrincipal vp) throws Exception {
		String ruta = UtilControlador.obtenerRutaServidor();
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
