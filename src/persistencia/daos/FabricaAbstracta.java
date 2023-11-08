package persistencia.daos;

import config.ConfigException;

public interface FabricaAbstracta {
	
	IDAOJuguetes crearDAOJuguetes(int cedula) throws ConfigException;
	
	IDAONiños crearDAONiños() throws ConfigException;

}
