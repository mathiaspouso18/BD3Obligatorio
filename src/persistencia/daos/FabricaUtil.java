package persistencia.daos;

public class FabricaUtil {
	
	public static FabricaAbstracta buildFabrica() {
		
        String nombreFabrica = "FabricaSQL";//Esto hay que tomarlo de un archivo de configuración
        FabricaAbstracta fab = null;
        try {
        	fab = (FabricaAbstracta) Class.forName(nombreFabrica).getConstructor().newInstance();
        }catch(Exception e) {
        	//TODO: Lanzar excepcion personalizada
        }
        return fab;
	}

}
