package persistencia.daos;

import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesArchivo;

public class FabricaArchivos implements FabricaAbstracta {
	@Override
	public IDAOJuguetes crearDAOJuguetes(int cedula) {
		return new DAOJuguetesArchivo(cedula);
	}

	@Override
	public IDAONiños crearDAONiños() {
		return new DAONiñosArchivo();
	}

	@Override
	public IPoolConexiones crearPool() {
		return PoolConexionesArchivo.getInstancia();
	}
}
