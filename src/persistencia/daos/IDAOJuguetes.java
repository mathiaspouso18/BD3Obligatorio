package persistencia.daos;

import java.util.List;
import logica.Juguete;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;


public abstract class IDAOJuguetes {
	
	protected int cedulaNiño;
	
	public IDAOJuguetes(int cedula) {
    	this.cedulaNiño = cedula;
    }
	
    public abstract void insback(Juguete juguete) throws PersistenciaException;
        

    public abstract int largo() throws PersistenciaException;

    public abstract Juguete k_esimo(int k) throws PersistenciaException;

    public abstract List<VOJuguete> listarJuguetes() throws PersistenciaException;

    public abstract void borrarJuguetes() throws PersistenciaException;

}
