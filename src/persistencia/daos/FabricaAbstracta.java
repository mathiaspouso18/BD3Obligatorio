package persistencia.daos;

import config.ConfigException;
import persistencia.poolConexiones.IPoolConexiones;

public interface FabricaAbstracta {
	
	IDAOJuguetes crearDAOJuguetes(int cedula);
	
	IDAONiños crearDAONiños();

	IPoolConexiones crearPool() throws ConfigException;
}
