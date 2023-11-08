package persistencia.daos;

import java.util.ArrayList;
import logica.Juguete;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import persistencia.poolConexiones.IConexion;


public abstract class IDAOJuguetes {
	
	protected int cedulaNiño;
	
	public IDAOJuguetes(int cedula) {
    	this.cedulaNiño = cedula;
    }
	
    public abstract void insback(IConexion _con, Juguete juguete) throws PersistenciaException;
        
    public abstract int largo(IConexion _con) throws PersistenciaException;

    public abstract Juguete k_esimo(IConexion _con,int k) throws PersistenciaException;

    public abstract ArrayList<VOJuguete> listarJuguetes(IConexion _con) throws PersistenciaException;

    public abstract void borrarJuguetes(IConexion _con) throws PersistenciaException;

}
