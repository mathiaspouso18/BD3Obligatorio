package grafica.controladores;

import java.rmi.Naming;
import java.rmi.RemoteException;

import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;

public class ControladorBuscarDescripcion {
	private IFachada cap;

	public ControladorBuscarDescripcion(VentanaPrincipal vp) throws Exception {
		String ruta = UtilControlador.obtenerRutaServidor();
		cap = (IFachada) Naming.lookup(ruta);
	}

	public String BuscarDecripcion(int _cedula, int _numero) throws NiñosException, PersistenciaException, JuguetesException, RemoteException {
		String descripcion = "";
		try {
			descripcion = cap.DarDescripcion(_cedula, _numero);
		} catch (JuguetesException je) {
			throw je;
		}
		return descripcion;
	}
}
