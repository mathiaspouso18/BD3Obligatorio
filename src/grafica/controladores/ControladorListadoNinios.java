package grafica.controladores;

import java.util.ArrayList;

import grafica.ventanas.VentanaPrincipal;
import logica.Fachada;
import logica.valueObjects.VONiño;

public class ControladorListadoNinios{
	private Fachada cap;
	
	public ControladorListadoNinios(VentanaPrincipal vp) throws Exception {
		cap = new Fachada();
	}
	
	public ArrayList<String []> ListadoNinios() throws Exception {
		ArrayList<String []> array = new ArrayList<String []>();
		try {
			ArrayList<VONiño> arr = cap.ListarNiños();
			for(VONiño v: arr) {
				String [] data = new String[3];
				Integer cedula = v.getCedula();
				String nombre = v.getNombre();
				String apellido = v.getApellido();
				
				data[0] = String.valueOf(cedula);
				data[1] = nombre;
				data[2] = apellido;
				array.add(data);
			}	
		} catch(Exception ve) {
			throw ve;
		}
		return array;
	}
}
