package persistencia.poolConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.ConfigException;
import config.ConfigManager;
import logica.excepciones.PersistenciaException;

public class PoolConexiones implements IPoolConexiones{

    private static PoolConexiones instancia;

    private int nivelTransaccionalidad;
    private Conexion conexiones[];
    private int tamanio;
    private int creadas;
    private int tope;

    private PoolConexiones(){
        nivelTransaccionalidad = Connection.TRANSACTION_SERIALIZABLE;
        conexiones = new Conexion[3];
        tope = -1;
        creadas = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException cne){
            cne.printStackTrace();
        }

    }

    public static PoolConexiones getInstancia(){
        if (instancia == null){
            instancia = new PoolConexiones();
        }
        return instancia;
    }

    @Override
    public IConexion obtenerConexion(boolean modifica) throws PersistenciaException, ConfigException{
        try{
            synchronized (this) {
                if (tope >= 0){//Tengo conexiones disponibles
                    tope--;
                    return comenzarTransaccion(conexiones[tope+1]);
                }else if (creadas<tamanio){//Puedo crear conexiones
                    creadas++;
                    return comenzarTransaccion(new Conexion(crearConnection()));
                }else{
                    wait();
                    return obtenerConexion(modifica);
                }
            }
        }catch(InterruptedException e){
            //TODO: hacer nada
        }
        return null;
    }

    @Override
    public void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException{
        synchronized (this){
        	try {
        		Conexion con =  (Conexion) conexion;
        		if (ok){
        			con.getCon().commit();
        		}else{
        			con.getCon().rollback();
        		}
        		tope++;
        		conexiones[tope] = con;
        		notify();
        	}catch(SQLException se) {
        		throw new PersistenciaException(4);
        	}
        }

    }
    
    private Conexion comenzarTransaccion(Conexion conexion) throws PersistenciaException{
    	try {
    		conexion.getCon().setTransactionIsolation(nivelTransaccionalidad);
    		conexion.getCon().setAutoCommit(false);
    		return conexion;
    	}catch(SQLException e) {
    		throw new PersistenciaException(4);
    	}
    }
    
    private Connection crearConnection() throws PersistenciaException, ConfigException { 
        try {
            return DriverManager.getConnection(
            		ConfigManager.getInstance().getProperty("url"),
            		ConfigManager.getInstance().getProperty("user"),
            		ConfigManager.getInstance().getProperty("password")
            );
        }catch (SQLException e){
        	throw new PersistenciaException(3);
        }
    }
}

