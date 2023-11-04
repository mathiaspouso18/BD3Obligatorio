package grafica.controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.Properties;

import grafica.ventanas.VentanaPrincipal;
import logica.Fachada;
import logica.excepciones.NiñosException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;

public class ControladorAltaJuguetes{
	private Fachada cap;
	
	public ControladorAltaJuguetes(VentanaPrincipal vp) throws Exception {
		cap = new Fachada();
	}
	
	public void AltaJuguete(int _cedula, String _descripcion) {
		VOJuguete _voj;
		_voj = new VOJuguete(_cedula, _descripcion, 0);
	}
}
