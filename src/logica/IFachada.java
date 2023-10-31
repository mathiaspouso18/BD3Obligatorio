package logica;

import java.rmi.Remote;
import java.util.ArrayList;

import logica.valueObjects.VONiño;

public interface IFachada extends Remote {
	public void AltaNiño(VONiño niño) throws Exception;
	public void BajaNiño(VONiño niño) throws Exception;
	public ArrayList<VONiño> ListarNiños() throws Exception;
}


