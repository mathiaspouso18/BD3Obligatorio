package persistencia.daos;

import persistencia.poolConexiones.IPoolConexiones;

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
		// TODO Auto-generated method stub
		return null;
	}
}
