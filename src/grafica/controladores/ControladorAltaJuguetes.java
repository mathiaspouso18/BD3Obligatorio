package grafica.controladores;

import java.rmi.Naming;

import grafica.ventanas.VentanaPrincipal;
import logica.IFachada;
import logica.excepciones.JuguetesException;
import logica.valueObjects.VOJuguete;

public class ControladorAltaJuguetes {
	private IFachada cap;

	public ControladorAltaJuguetes(VentanaPrincipal vp) throws Exception {
		String ruta = UtilControlador.obtenerRutaServidor();
		cap = (IFachada) Naming.lookup(ruta);
	}

	public void AltaJuguete(int _cedula, String _descripcion) throws JuguetesException, Exception {
		VOJuguete _voj;
		_voj = new VOJuguete(0, _descripcion, _cedula);
		try {
			cap.AltaJuguete(_voj);
		} catch (JuguetesException je) {
			throw je;
		}
	}
}
