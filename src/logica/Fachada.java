package logica;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import config.ConfigException;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.daos.IDAONiños;
import persistencia.daos.FabricaUtil;

public class Fachada extends UnicastRemoteObject implements IFachada {
	private static final long serialVersionUID = 1L;

	private IDAONiños daoNiños;

	private static IPoolConexiones pool;

	public Fachada() throws ClassNotFoundException, IOException, ConfigException, PersistenciaException {
		pool = FabricaUtil.buildFabrica().crearPool();
		daoNiños = FabricaUtil.buildFabrica().crearDAONiños();
	}

	public void AltaNiño(VONiño niño) throws PersistenciaException, NiñosException {
		int _cedula = niño.getCedula();
		IConexion icon = pool.obtenerConexion(false);
		try {
			if (!daoNiños.member(icon, _cedula)) {
				String nom = niño.getNombre();
				String ape = niño.getApellido();
				Niño n = new Niño(_cedula, nom, ape);
				daoNiños.insert(icon, n);
				pool.liberarConexion(icon, true);
			} else {
				pool.liberarConexion(icon, false);
				throw new NiñosException(1);
			}
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			throw e;
		}
	}

	public void AltaJuguete(VOJuguete juguete) throws NiñosException, PersistenciaException, JuguetesException {
		int _ced = juguete.getCedulaNinio();
		IConexion icon = pool.obtenerConexion(false);
		try {
			if (!daoNiños.member(icon, _ced)) {
				pool.liberarConexion(icon, false);
				throw new NiñosException(2);
			}
			Niño n = daoNiños.find(icon, _ced);
			List<VOJuguete> juguetes = n.listarJuguetes(icon);
			Juguete jug = new Juguete(juguetes.size() + 1, juguete.getDescripcion());
			n.addJuguete(icon, jug);
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			throw e;
		}
	}

	public void BajaNiño(VONiño niño) throws NiñosException, PersistenciaException {
		int _cedula = niño.getCedula();
		IConexion icon = pool.obtenerConexion(false);
		try {
			if (daoNiños.member(icon, _cedula)) {
				Niño n = daoNiños.find(icon, _cedula);
				if (n.cantidadJuguetes(icon) != 0) {
					n.borrarJuguetes(icon);
				}
				daoNiños.delete(icon, _cedula);
				pool.liberarConexion(icon, true);
			} else {
				pool.liberarConexion(icon, false);
				throw new NiñosException(2);
			}
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			throw e;
		}
	}

	public ArrayList<VONiño> ListarNiños() throws NiñosException, PersistenciaException {
		ArrayList<VONiño> listNiños = null;
		IConexion icon = pool.obtenerConexion(false);
		try {
			listNiños = daoNiños.listarNiños(icon);
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
		if (listNiños.isEmpty()) throw new NiñosException(3);
		return listNiños;
	}

	public ArrayList<VOJuguete> ListarJuguetes(int _ced) throws NiñosException, PersistenciaException, JuguetesException {
		ArrayList<VOJuguete> listJuguetes = null;
		IConexion icon = pool.obtenerConexion(false);
		try {
			if (!daoNiños.member(icon, _ced)) {
				pool.liberarConexion(icon, false);
				throw new NiñosException(2);
			}
			Niño niño = daoNiños.find(icon, _ced);
			listJuguetes = niño.listarJuguetes(icon);
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
		if (listJuguetes.isEmpty()) throw new JuguetesException(3);
		return listJuguetes;
	}

	public String DarDescripcion(int _ced, int _num) throws NiñosException, PersistenciaException, JuguetesException {
		String descripcion;
		IConexion icon = pool.obtenerConexion(false);
		try {
			if (!daoNiños.member(icon, _ced)) {
				pool.liberarConexion(icon, false);
				throw new NiñosException(2);
			}
			Niño niño = daoNiños.find(icon, _ced);
			if (!niño.tieneJuguete(icon, _num)) {
				pool.liberarConexion(icon, false);
				throw new JuguetesException(4);
			}
			descripcion = niño.obtenerJuguete(icon, _num).getDescripcion();
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, true);
			throw e;
		}
		pool.liberarConexion(icon, true);
		return descripcion;
	}

	public void BajaJuguetes(VONiño _niño) throws NiñosException, PersistenciaException, RemoteException, JuguetesException {
		int _cedula = _niño.getCedula();
		IConexion icon = pool.obtenerConexion(false);
		try {
			if (!daoNiños.member(icon, _cedula)) {
				pool.liberarConexion(icon, false);
				throw new NiñosException(2);
			}
			Niño niño = daoNiños.find(icon, _cedula);
			if (niño.cantidadJuguetes(icon) == 0) {
				pool.liberarConexion(icon, false);
				throw new JuguetesException(3);
			}
			niño.borrarJuguetes(icon);
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			throw e;
		}
		pool.liberarConexion(icon, true);
	}
}
