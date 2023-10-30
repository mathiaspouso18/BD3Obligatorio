package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logica.excepciones.NiñosException;
import logica.valueObjects.VONiño;
import persistencia.daos.DAOJuguetes;
import persistencia.daos.DAONiños;

public class Fachada {
	private DAOJuguetes diccioJuguetes;
	private DAONiños diccioNiños;
	
	public Fachada() {
		diccioJuguetes = new DAOJuguetes(0);
		diccioNiños = new DAONiños();
	}
	
	public void AltaNiño(VONiño niño) throws Exception {
		int _cedula =  niño.getCedula();
		
		try {
			if(!diccioNiños.member(_cedula)) {
				String nom = niño.getNombre();
				String ape = niño.getApellido();
				
				Niño n = new Niño(_cedula, nom, ape);
				diccioNiños.insert(n);
			}
			else {
				throw new NiñosException(1);
			}
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}	
	
	public void BajaNiño(VONiño niño) throws Exception {
		int _cedula =  niño.getCedula();
		
		try {
			if(diccioNiños.member(_cedula)) {
				diccioNiños.delete(_cedula);
			}
			else {
				throw new NiñosException(2);
			}
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public ArrayList<VONiño> ListarNiños() throws Exception {
		ArrayList<VONiño> listNiños;
		
		try {
			List<Niño> lsta = diccioNiños.listarNiños();
			listNiños = new ArrayList<>();

			if(!lsta.isEmpty()) {
				Iterator<Niño> iterador = lsta.iterator();

				while(iterador.hasNext()) {
					Niño n = iterador.next();
					VONiño von = new VONiño(n.getCedula(), n.getNombre(), n.getApellido());
					listNiños.add(von);
				}
			}
			else {
				throw new NiñosException(3);
			}
			
			return listNiños;
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	
}