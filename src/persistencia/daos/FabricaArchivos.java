package persistencia.daos;

import config.ConfigException;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesSQL;

public class FabricaArchivos implements FabricaAbstracta {
	@Override
	public IDAOJuguetes crearDAOJuguetes(int cedula) throws ConfigException{
		return new DAOJuguetesArchivo(cedula);
	}

	@Override
	public IDAONiños crearDAONiños() throws ConfigException{
		return new DAONiñosArchivo();
	}

	@Override
	public IPoolConexiones crearPool() {
		// TODO Auto-generated method stub
		return null;
	}
}
