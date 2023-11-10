package persistencia.poolConexiones;

import logica.excepciones.PersistenciaException;

public class PoolConexionesArchivo implements IPoolConexiones{
	private static PoolConexionesArchivo instancia;
	private int lectores;
	private boolean alguienEscribe;
	
	private PoolConexionesArchivo(){
		lectores = 0;
		alguienEscribe = false;
	}
	
	public static PoolConexionesArchivo getInstancia() {
		if (instancia == null) {
			instancia = new PoolConexionesArchivo();
		}
		return instancia;
	}

	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		if (modifica){
			comienzoEscritura();
			return new ConexionArchivo(true);
		}
		comienzoLectura();
		return new ConexionArchivo(false);
	}

	public synchronized void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) conexion;
		if (con.getEscritura()){
			finEscritura();
		}else{
			finLectura();
		}
	}
	
	private void comienzoLectura () throws PersistenciaException {
		try {
			while (getInstancia().alguienEscribe)
				wait();
			
			getInstancia().lectores++;
			}
			
		catch (InterruptedException e) {
			throw new PersistenciaException(-1);
		}
	}

	private void finLectura () {
		getInstancia().lectores--;
		if (getInstancia().lectores == 0)
			notify();
	}
	
	private void comienzoEscritura () throws PersistenciaException {
		try {
			while (getInstancia().alguienEscribe || getInstancia().lectores > 0)
				wait();
			getInstancia().alguienEscribe = true;
		}
		catch (InterruptedException e) {
			throw new PersistenciaException(-1);
		}
	}

	private void finEscritura () {
		getInstancia().alguienEscribe = false;
		notify();
	}
}
