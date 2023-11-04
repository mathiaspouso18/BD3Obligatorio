package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logica.excepciones.NiñosException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;
import persistencia.daos.DAOJuguetes;
import persistencia.daos.DAONiños;

public class Fachada implements IFachada {
	private DAONiños daoNiños;
	
	public Fachada() {
		daoNiños = new DAONiños();
	}
	
	public void AltaNiño(VONiño niño) throws Exception {
		int _cedula =  niño.getCedula();
		
		try {
			if(!daoNiños.member(_cedula)) {
				String nom = niño.getNombre();
				String ape = niño.getApellido();
				
				Niño n = new Niño(_cedula, nom, ape);
				daoNiños.insert(n);
			}
			else {
				throw new NiñosException(1);
			}
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}	
	
	public void AltaJuguete(VOJuguete juguete) throws Exception {
		try {
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}	
	
	public void BajaNiño(VONiño niño) throws Exception {
		int _cedula =  niño.getCedula();
		
		try {
			if(daoNiños.member(_cedula)) {
				daoNiños.delete(_cedula);
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
			List<VONiño> lsta = daoNiños.listarNiños();
			listNiños = new ArrayList<>();

			if(!lsta.isEmpty()) {
				Iterator<VONiño> iterador = lsta.iterator();

				while(iterador.hasNext()) {
					VONiño n = iterador.next();
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
	
	public ArrayList<VOJuguete> ListarJuguetes(int _ced) throws Exception {
		ArrayList<VOJuguete> listJuguetes = new ArrayList<VOJuguete>();
		
		try {
			Niño n = daoNiños.find(_ced);
			List<VOJuguete> juguetes = n.listarJuguetes();
			//List<Juguete> lsta = diccioJuguetes.listarJuguetes();
			//listJuguetes = new ArrayList<>();

			//if(!lsta.isEmpty()) {
				//Iterator<Juguete> iterador = lsta.iterator();

				//while(iterador.hasNext()) {
					//Juguete n = iterador.next();
					//VOJuguete voj = new VOJuguete(n.getNumero(), n.getDescripcion(), _ced);
					//listJuguetes.add(voj);
				//}
			//}
			//else {
				//throw new NiñosException(3);
			//}
			
			return listJuguetes;
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	
}