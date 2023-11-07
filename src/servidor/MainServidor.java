package servidor;

import java.io.IOException;
import java.net.BindException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import config.ConfigException;
import config.ConfigManager;
import logica.Fachada;
import logica.excepciones.PersistenciaException;

public class MainServidor {
	public static void main(String[] args) throws ClassNotFoundException, IOException, PersistenciaException, ConfigException{
		try
		{ 
			String ip = ConfigManager.getInstance().getProperty("ipServidor");
			int port = ConfigManager.getInstance().getIntProperty("puertoServidor");
		
			//pongo a correr el rmiregistry
			LocateRegistry.createRegistry(port);
		
			//publico el objeto remoto en dicha ip y puerto
			String ruta = "//" + ip + ":" + Integer.toString(port) + "/fachada";
			Fachada fachada = new Fachada();
			System.out.println("Antes de publicar");
			Naming.rebind(ruta, fachada);
			System.out.println("Luego de publicar");
		}
		catch (RemoteException e){ 
			e.printStackTrace(); 
		}
		catch(BindException b) {
			b.printStackTrace();
		}
		catch (MalformedURLException e){ 
			e.printStackTrace(); 
		}
	}
}