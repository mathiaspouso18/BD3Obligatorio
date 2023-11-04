package grafica.controladores;
import grafica.ventanas.VentanaPrincipal;
import logica.Fachada;
import logica.excepciones.NiñosException;
import logica.valueObjects.VONiño;

public class ControladorAltaNinio{
	private Fachada cap;
	
	public ControladorAltaNinio(VentanaPrincipal vp) throws Exception {
		cap = new Fachada();
	}
	
	public void AltaNinio(int _cedula, String _nombre, String _apellido) throws Exception {
		VONiño _von;
		_von = new VONiño(_cedula,_nombre,_apellido);
		
		try {
			cap.AltaNiño(_von);
		} catch(NiñosException ve) {
			throw ve;
		}
	}
}
