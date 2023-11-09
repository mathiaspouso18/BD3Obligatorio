package persistencia.daos;

import config.ConfigException;
import config.ConfigManager;
import logica.excepciones.PersistenciaException;

public class FabricaUtil {
	public static FabricaAbstracta buildFabrica() throws PersistenciaException {
		String nombreFabrica;
		try {
			nombreFabrica = ConfigManager.getInstance().getProperty("fabrica");
		} catch (ConfigException e) {
			throw new PersistenciaException(-1);
		}
		FabricaAbstracta fab = null;
		try {
			fab = (FabricaAbstracta) Class.forName(nombreFabrica).getConstructor().newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fab;
	}
}
