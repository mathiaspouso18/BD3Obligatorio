package grafica.controladores;

import java.util.ArrayList;

import grafica.ventanas.VentanaPrincipal;
import logica.Fachada;
import logica.valueObjects.VOJuguete;

public class ControladorListadoJuguetes{
	private Fachada cap;
	public ControladorListadoJuguetes(VentanaPrincipal vp) throws Exception {
		cap = new Fachada();
	}
	
	public ArrayList<String []> ListadoJuguetes(int _cedula) throws Exception {
		ArrayList<String []> array = new ArrayList<String []>();
		try {
			ArrayList<VOJuguete> arr = cap.ListarJuguetes(_cedula);
			for(VOJuguete v: arr) {
				String [] data = new String[3];
				Integer cedula = v.getCedulaNinio();
				String descripcion = v.getDescripcion();
				
				data[0] = String.valueOf(cedula);
				data[1] = descripcion;
				array.add(data);
			}	
		}catch(Exception ve) {
			throw ve;
		}
		
		return array;
		
	}
}