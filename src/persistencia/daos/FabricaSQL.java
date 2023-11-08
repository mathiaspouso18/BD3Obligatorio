package persistencia.daos;

import config.ConfigException;

public class FabricaSQL implements FabricaAbstracta{

	@Override
	public IDAOJuguetes crearDAOJuguetes(int cedula) throws ConfigException{
		
		return new DAOJuguetes(cedula);
	}

	@Override
	public IDAONiños crearDAONiños() throws ConfigException{
		return new DAONiños()
	}



}
