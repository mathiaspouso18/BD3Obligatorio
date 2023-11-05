package grafica.controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.Properties;

import grafica.ventanas.VentanaPrincipal;
import logica.Fachada;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;

public class ControladorAltaJuguetes{
	private Fachada cap;
	
	public ControladorAltaJuguetes(VentanaPrincipal vp) throws Exception {
		cap = new Fachada();
	}
	
	public void AltaJuguete(int _cedula, String _descripcion) throws JuguetesException, Exception {
		VOJuguete _voj;
		_voj = new VOJuguete(_cedula, _descripcion, _cedula);
		try {
			cap.AltaJuguete(_voj);
		} catch(JuguetesException je) {
			throw je;
		}
	}
}
