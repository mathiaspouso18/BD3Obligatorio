package persistencia.daos;

import config.ConfigException;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesSQL;

public class FabricaMySQL implements FabricaAbstracta {
	@Override
	public IDAOJuguetes crearDAOJuguetes(int cedula) throws ConfigException{
		return new DAOJuguetes(cedula);
	}

	@Override
	public IDAONiños crearDAONiños() throws ConfigException{
		return new DAONiños();
	}
	
	public IPoolConexiones crearPool() {
		return new PoolConexionesSQL();
	}
}
