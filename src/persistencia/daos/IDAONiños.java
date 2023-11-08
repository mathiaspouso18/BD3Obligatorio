package persistencia.daos;


import java.util.List;
import logica.Niño;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;


public abstract class IDAONiños {

	   public abstract boolean member(int cedula) throws PersistenciaException;

	    public abstract void insert(Niño niño) throws PersistenciaException;

	    public abstract Niño find(int cedula) throws PersistenciaException;

	    public abstract void delete(int cedula) throws PersistenciaException;
	    
	    public abstract List<VONiño> listarNiños() throws PersistenciaException;
	
}
