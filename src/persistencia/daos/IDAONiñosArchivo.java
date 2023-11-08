package persistencia.daos;

import java.util.ArrayList;

import config.ConfigException;
import logica.Niño;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public abstract class IDAONiñosArchivo {

	public abstract boolean member(int cedula) throws PersistenciaException, ConfigException ;

    public abstract void insert(Niño niño) throws PersistenciaException, ConfigException ;

    public abstract Niño find(int cedula) throws PersistenciaException, ConfigException ;

    public abstract void delete(int cedula) throws PersistenciaException, ConfigException ;
    
    public abstract ArrayList<VONiño> listarNiños() throws PersistenciaException, ConfigException ;
}
