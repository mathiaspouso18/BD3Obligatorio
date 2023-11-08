package persistencia.daos;

import config.ConfigException;
import persistencia.poolConexiones.IPoolConexiones;

public interface FabricaAbstracta {
	
	IDAOJuguetes crearDAOJuguetes(int cedula) throws ConfigException;
	
	IDAONiños crearDAONiños() throws ConfigException;
	
	IDAOJuguetesArchivo crearDAOJuguetesArchivo(int cedula) throws ConfigException;
	
	IDAONiñosArchivo crearDAONiñosArchivo() throws ConfigException;	

	IPoolConexiones crearPool();
}
