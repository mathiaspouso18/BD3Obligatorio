package persistencia.poolConexiones;

import config.ConfigException;
import logica.excepciones.PersistenciaException;

public interface IPoolConexiones {

	IConexion obtenerConexion(boolean modifica) throws PersistenciaException, ConfigException;

	void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException;

}
