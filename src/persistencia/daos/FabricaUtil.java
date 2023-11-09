package persistencia.daos;

import config.ConfigException;
import config.ConfigManager;

public class FabricaUtil {
	public static FabricaAbstracta buildFabrica() throws ConfigException{
        String nombreFabrica = ConfigManager.getInstance().getProperty("fabrica");
        FabricaAbstracta fab = null;
       
        try {
        	fab = (FabricaAbstracta) Class.forName(nombreFabrica).getConstructor().newInstance();
        	//fab = new FabricaMySQL();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return fab;
	}
}
