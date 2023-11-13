package grafica.controladores;

import java.rmi.Naming;
import java.rmi.RemoteException;

import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public class ControladorBajaNinio {
	private IFachada cap;
	
	public ControladorBajaNinio(VentanaPrincipal vp) throws Exception {
		String ruta = UtilControlador.obtenerRutaServidor();
		cap = (IFachada) Naming.lookup(ruta);
	}

	public void BajaNinio(int _cedula) throws NiñosException, PersistenciaException, RemoteException {
		try {
			VONiño von = new VONiño(_cedula, null, null);
			cap.BajaNiño(von);
		} catch (NiñosException ne) {
			throw ne;
		}
	}
}
