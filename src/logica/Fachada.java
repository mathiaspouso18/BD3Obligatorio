package logica;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import config.ConfigException;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;
import persistencia.daos.IDAONiños;
import persistencia.daos.FabricaUtil;

public class Fachada extends UnicastRemoteObject implements  IFachada {
	private static final long serialVersionUID = 1L;

	private IDAONiños daoNiños;
	
	private static Fachada instance = null;
	
	public static Fachada getInstance() throws ClassNotFoundException, IOException, PersistenciaException, ConfigException {
		if (instance == null) {
			instance = new Fachada();
		}
		return instance;
	}
	
	public Fachada() throws ClassNotFoundException, IOException, ConfigException {
		daoNiños = FabricaUtil.buildFabrica().crearDAONiños();
	}
	
	public void AltaNiño(VONiño niño) throws PersistenciaException, NiñosException, ConfigException {
		int _cedula =  niño.getCedula();
		if(!daoNiños.member(_cedula)) {
			String nom = niño.getNombre();
			String ape = niño.getApellido();
			Niño n = new Niño(_cedula, nom, ape);
			daoNiños.insert(n);
		} else {
			throw new NiñosException(1);
		}
	}
	
	public void AltaJuguete(VOJuguete juguete) throws NiñosException, PersistenciaException, JuguetesException, ConfigException {
		int _ced = juguete.getCedulaNinio();
		int _ultimoJuguete = 0;
		String _descripcion = juguete.getDescripcion();
		
		if(daoNiños.member(_ced)) {
			Niño n = daoNiños.find(_ced);
			
			List<VOJuguete> juguetes = n.listarJuguetes();
			_ultimoJuguete = juguetes.size() + 1;
			
			Juguete jug = new Juguete(_ultimoJuguete, _descripcion);
			
			n.addJuguete(jug);
		} else {
			throw new NiñosException(2);
		}
	}	
	
	public void BajaNiño(VONiño niño) throws NiñosException, PersistenciaException, ConfigException {
		int _cedula =  niño.getCedula();
		
		if(daoNiños.member(_cedula)) {
			Niño n = daoNiños.find(_cedula);
			if(n.cantidadJuguetes() != 0) {
				n.borrarJuguetes();
			}
			daoNiños.delete(_cedula);
		} else {
			throw new NiñosException(2);
		}
	}
	
	public ArrayList<VONiño> ListarNiños() throws NiñosException, PersistenciaException {
		ArrayList<VONiño> listNiños;
		List<VONiño> lsta = daoNiños.listarNiños();
		listNiños = new ArrayList<>();
	
		if(!lsta.isEmpty()) {
			Iterator<VONiño> iterador = lsta.iterator();
			while(iterador.hasNext()) {
				VONiño n = iterador.next();
				VONiño von = new VONiño(n.getCedula(), n.getNombre(), n.getApellido());
				listNiños.add(von);
			}
		} else {
			throw new NiñosException(3);
		}
		
		return listNiños;
	}
	
	public ArrayList<VOJuguete> ListarJuguetes(int _ced) throws NiñosException, PersistenciaException, JuguetesException, ConfigException {
		ArrayList<VOJuguete> listJuguetes = null;
		
		if(daoNiños.member(_ced)) {
			Niño n = daoNiños.find(_ced);
			listJuguetes = n.listarJuguetes();
			if(listJuguetes.isEmpty()) {
				throw new JuguetesException(3);
			}
		} else {
			throw new NiñosException(2);
		}
		
		return listJuguetes;
	}
	
	public String DarDescripcion(int _ced, int _num) throws NiñosException, PersistenciaException, JuguetesException, ConfigException {
		String retorno = "";
		if(daoNiños.member(_ced)) {
			Niño n = daoNiños.find(_ced);
			boolean tieneJuguete = n.tieneJuguete(_num);
			if(tieneJuguete) {
				Juguete j = n.obtenerJuguete(_num);
				retorno = j.getDescripcion();
			} else {
				throw new JuguetesException(4);
			}
		} else {
			throw new NiñosException(2);
		}
		
		return retorno;
	}
	
	public void BajaJuguetes(VONiño niño) throws NiñosException, PersistenciaException, RemoteException, ConfigException {
		int _cedula =  niño.getCedula();
		if(daoNiños.member(_cedula)) {
			Niño n = daoNiños.find(_cedula);
			if(n.cantidadJuguetes() != 0) {
				n.borrarJuguetes();
			}
		} else {
			throw new NiñosException(2);
		}
	}
}