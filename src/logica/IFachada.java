package logica;

import java.rmi.Remote;
import java.util.ArrayList;

import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONiño;

public interface IFachada extends Remote {
	public void AltaNiño(VONiño niño) throws PersistenciaException, NiñosException;
	public void AltaJuguete(VOJuguete juguete) throws NiñosException, PersistenciaException, JuguetesException ;
	public void BajaNiño(VONiño niño) throws NiñosException, PersistenciaException;
	public ArrayList<VONiño> ListarNiños() throws NiñosException, PersistenciaException;
	public ArrayList<VOJuguete> ListarJuguetes(int _ced) throws NiñosException, PersistenciaException, JuguetesException ;
	public String DarDescripcion(int _ced, int _num) throws NiñosException, PersistenciaException, JuguetesException;
}


