package logica;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import config.ConfigException;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;
import persistencia.daos.DAONiños;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada {
	private static final long serialVersionUID = 1L;

	private DAONiños daoNiños;
	
	private static Fachada instance = null;
	
	private static IPoolConexiones pool;
	
	public static Fachada getInstance() throws ClassNotFoundException, IOException, PersistenciaException, ConfigException {
		if (instance == null) {
			pool = new PoolConexiones();
			instance = new Fachada();
		}
		return instance;
	}
	
	public Fachada() throws ClassNotFoundException, IOException, ConfigException {
		daoNiños = new DAONiños();
	}
	
	public void AltaNiño(VONiño niño) throws PersistenciaException, NiñosException, ConfigException {
		int _cedula =  niño.getCedula();
		IConexion icon = pool.obtenerConexion(false);
		try {
			if(!daoNiños.member(icon, _cedula)) {
				String nom = niño.getNombre();
				String ape = niño.getApellido();
				Niño n = new Niño(_cedula, nom, ape);
				daoNiños.insert(icon, n);
			} else {
				throw new NiñosException(1);
			}
		} catch(PersistenciaException e) {			
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
	}
	
	public void AltaJuguete(VOJuguete juguete) throws NiñosException, PersistenciaException, JuguetesException, ConfigException {
		int _ced = juguete.getCedulaNinio();
		int _ultimoJuguete = 0;
		String _descripcion = juguete.getDescripcion();

		IConexion icon = pool.obtenerConexion(false);
		try {
			if(daoNiños.member(icon, _ced)) {
				Niño n = daoNiños.find(icon, _ced);
				
				List<VOJuguete> juguetes = n.listarJuguetes(icon);
				_ultimoJuguete = juguetes.size() + 1;
				
				Juguete jug = new Juguete(_ultimoJuguete, _descripcion);
				
				n.addJuguete(icon, jug);
			} else {
				throw new NiñosException(2);
			}
		} catch(PersistenciaException e) {			
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
	}	
	
	public void BajaNiño(VONiño niño) throws NiñosException, PersistenciaException, ConfigException {
		int _cedula =  niño.getCedula();

		IConexion icon = pool.obtenerConexion(false);
		try {
			if(daoNiños.member(icon, _cedula)) {
				Niño n = daoNiños.find(icon ,_cedula);
				if(n.cantidadJuguetes(icon) != 0) {
					n.borrarJuguetes(icon);
				}
				daoNiños.delete(icon, _cedula);
			} else {
				throw new NiñosException(2);
			}
		} catch(PersistenciaException e) {			
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
	}
	
	public ArrayList<VONiño> ListarNiños() throws NiñosException, PersistenciaException, ConfigException {
		ArrayList<VONiño> listNiños;
		IConexion icon = pool.obtenerConexion(false); 
		try {
			List<VONiño> lsta = daoNiños.listarNiños(icon);
			listNiños = new ArrayList<>();
			if(!lsta.isEmpty()) {
				Iterator<VONiño> iterador = lsta.iterator();
				while(iterador.hasNext()) {
					VONiño n = iterador.next();
					VONiño von = new VONiño(n.getCedula(), n.getNombre(), n.getApellido());
					listNiños.add(von);
				}
				pool.liberarConexion(icon, true);
				return listNiños;
			} else {
				throw new NiñosException(3);
			}
		} catch(PersistenciaException e) {			
			pool.liberarConexion(icon, false);
			throw e;
		}
	}
	
	public ArrayList<VOJuguete> ListarJuguetes(int _ced) throws NiñosException, PersistenciaException, JuguetesException, ConfigException {
		ArrayList<VOJuguete> listJuguetes = null;
		IConexion icon = pool.obtenerConexion(false);
		try {
			if(daoNiños.member(icon, _ced)) {
				Niño n = daoNiños.find(icon, _ced);
				listJuguetes = n.listarJuguetes(icon);
				if(listJuguetes.isEmpty()) {
					throw new JuguetesException(3);
				}
			} else {
				throw new NiñosException(2);
			}
		} catch(PersistenciaException e) {			
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
		return listJuguetes;
	}
	
	public String DarDescripcion(int _ced, int _num) throws NiñosException, PersistenciaException, JuguetesException, ConfigException {
		String retorno = "";
		IConexion icon = pool.obtenerConexion(false);
		try {
			if(daoNiños.member(icon, _ced)) {
				Niño n = daoNiños.find(icon, _ced);
				boolean tieneJuguete = n.tieneJuguete(icon, _num);
				if(tieneJuguete) {
					Juguete j = n.obtenerJuguete(icon, _num);
					retorno = j.getDescripcion();
				} else {
					throw new JuguetesException(4);
				}
			} else {
				throw new NiñosException(2);
			}
		} catch(PersistenciaException e) {			
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
		return retorno;
	}
	
	public void BajaJuguetes(VONiño niño) throws NiñosException, PersistenciaException, RemoteException, ConfigException {
		int _cedula =  niño.getCedula();
		IConexion icon = pool.obtenerConexion(false);
		try {
			if(daoNiños.member(icon, _cedula)) {
				Niño n = daoNiños.find(icon, _cedula);
				if(n.cantidadJuguetes(icon) != 0) {
					n.borrarJuguetes(icon);
				}
			} else {
				throw new NiñosException(2);
			}
		} catch(PersistenciaException e) {			
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
	}
}
