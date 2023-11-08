package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import config.ConfigException;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;

public interface IFachada extends Remote {
	public void AltaNiño(VONiño niño) throws PersistenciaException, NiñosException, RemoteException, ConfigException;

	public void AltaJuguete(VOJuguete juguete) throws NiñosException, PersistenciaException, JuguetesException, RemoteException, ConfigException;

	public void BajaNiño(VONiño niño) throws NiñosException, PersistenciaException, RemoteException, ConfigException;

	public ArrayList<VONiño> ListarNiños() throws NiñosException, PersistenciaException, RemoteException, ConfigException;

	public ArrayList<VOJuguete> ListarJuguetes(int _ced) throws NiñosException, PersistenciaException, JuguetesException, RemoteException, ConfigException;

	public String DarDescripcion(int _ced, int _num) throws NiñosException, PersistenciaException, JuguetesException, RemoteException, ConfigException;

	public void BajaJuguetes(VONiño niño) throws NiñosException, PersistenciaException, RemoteException, ConfigException;
}
