package grafica.controladores;

import java.rmi.Naming;
import java.rmi.RemoteException;

import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public class ControladorBajaJuguetes {
	private IFachada fachada;

	public ControladorBajaJuguetes(VentanaPrincipal vp) throws Exception {
		String ruta = UtilControlador.obtenerRutaServidor();
		fachada = (IFachada) Naming.lookup(ruta);
	}

	public void BajarJuguetes(int _cedula) throws NiñosException, PersistenciaException, RemoteException, JuguetesException {
		try {
			VONiño von = new VONiño(_cedula, null, null);
			fachada.BajaJuguetes(von);
		} catch (NiñosException ne) {
			throw ne;
		}
	}
}
