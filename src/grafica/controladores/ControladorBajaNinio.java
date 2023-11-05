package grafica.controladores;

import grafica.ventanas.VentanaPrincipal;
import logica.Fachada;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;

public class ControladorBajaNinio{
	private Fachada cap;
	public ControladorBajaNinio(VentanaPrincipal vp) throws Exception {
		
		//Properties p = new Properties();
		//String nomArch = "src/config/config.properties";
		//p.load (new FileInputStream (nomArch));
		//String ip = p.getProperty("ipServidor");
		//String puerto = p.getProperty("puertoServidor");
		//String ruta = "//" + ip + ":" + puerto + "/fachada";
		
		//cap = (ICapaLogica) Naming.lookup(ruta);

		cap = new Fachada();
	}
	
	public void BajaNinio(int _cedula) throws NiñosException, PersistenciaException {
		try {
			VONiño von = new VONiño(_cedula, null, null);
			cap.BajaNiño(von);
			
		}catch(NiñosException ne) {
			throw ne;
		}
	}
}
