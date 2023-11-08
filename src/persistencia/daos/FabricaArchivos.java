package persistencia.daos;

import config.ConfigException;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesSQL;

public class FabricaArchivos implements FabricaAbstracta {
	@Override
	public IDAOJuguetesArchivo crearDAOJuguetesArchivo(int cedula) throws ConfigException{
		return new DAOJuguetesArchivo(cedula);
	}

	@Override
	public IDAONiñosArchivo crearDAONiñosArchivo() throws ConfigException{
		return new DAONiñosArchivo();
	}
	
	public IPoolConexiones crearPool() {
		return new PoolConexionesSQL();
	}

	@Override
	public IDAOJuguetes crearDAOJuguetes(int cedula) throws ConfigException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDAONiños crearDAONiños() throws ConfigException {
		// TODO Auto-generated method stub
		return null;
	};
}
