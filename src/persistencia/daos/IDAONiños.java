package persistencia.daos;


import java.util.ArrayList;

import config.ConfigException;
import logica.Niño;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;
import persistencia.poolConexiones.IConexion;


public abstract class IDAONiños {

	   public abstract boolean member(IConexion _con, int cedula) throws PersistenciaException;

	    public abstract void insert(IConexion _con, Niño niño) throws PersistenciaException;

	    public abstract Niño find(IConexion _con, int cedula) throws PersistenciaException;

	    public abstract void delete(IConexion _con, int cedula) throws PersistenciaException;
	    
	    public abstract ArrayList<VONiño> listarNiños(IConexion _con) throws PersistenciaException;
}
