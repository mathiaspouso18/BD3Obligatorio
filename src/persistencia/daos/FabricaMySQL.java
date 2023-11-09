package persistencia.daos;

import config.ConfigException;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesSQL;

public class FabricaMySQL implements FabricaAbstracta {
	@Override
	public IDAOJuguetes crearDAOJuguetes(int cedula){
		return new DAOJuguetes(cedula);
	}

	@Override
	public IDAONiños crearDAONiños(){
		return new DAONiños();
	}
	
	public IPoolConexiones crearPool() throws ConfigException {
		return new PoolConexionesSQL();
	}
}
