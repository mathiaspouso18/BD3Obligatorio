package persistencia.daos;

public class FabricaUtil {
	public static FabricaAbstracta buildFabrica(){
        String nombreFabrica = "persistencia.daos.FabricaArchivos";//Esto hay que tomarlo de un archivo de configuración
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
