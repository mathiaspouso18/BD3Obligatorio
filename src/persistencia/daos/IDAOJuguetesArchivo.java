package persistencia.daos;

import java.util.ArrayList;
import logica.Juguete;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;

public abstract class IDAOJuguetesArchivo {
	
	protected int cedulaNiño;
	
	public IDAOJuguetesArchivo(int cedula) {
    	this.cedulaNiño = cedula;
    }
	
    public abstract void insback(Juguete juguete) throws PersistenciaException;
        
    public abstract int largo() throws PersistenciaException;

    public abstract Juguete k_esimo(int k) throws PersistenciaException;

    public abstract ArrayList<VOJuguete> listarJuguetes() throws PersistenciaException;

    public abstract void borrarJuguetes() throws PersistenciaException;

}
