package persistencia.daos;

public class FabricaUtil {
	public static FabricaAbstracta buildFabrica(){
        String nombreFabrica = FabricaSQL.class.toString();//Esto hay que tomarlo de un archivo de configuración
        FabricaAbstracta fab = null;
        
        try {
//        	fab = (FabricaAbstracta) Class.forName(nombreFabrica).getConstructor().newInstance();
        	fab = new FabricaSQL();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return fab;
	}
}
