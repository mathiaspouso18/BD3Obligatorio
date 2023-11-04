package persistencia.poolConexiones;

import logica.excepciones.PersistenciaException;

public interface IPoolConexiones {

    IConexion obtenerConexion(boolean modifica) throws PersistenciaException;

    void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException;

}
