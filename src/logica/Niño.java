package logica;

import java.util.ArrayList;

import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import persistencia.daos.DAOJuguetes;
import persistencia.poolConexiones.IConexion;

public class Niño {
    private int cedula;
    private String nombre;
    private String apellido;
    private DAOJuguetes secuencia;

    public Niño(int cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.secuencia = new DAOJuguetes(cedula);
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public boolean tieneJuguete(IConexion con, int numero) throws PersistenciaException {
        return secuencia.k_esimo(con, numero) != null;
    }

    public int cantidadJuguetes(IConexion con) throws PersistenciaException {
        return secuencia.largo(con);
    }

    public void addJuguete(IConexion con, Juguete juguete) throws PersistenciaException {
        secuencia.insback(con, juguete);
    }

    public Juguete obtenerJuguete(IConexion con, int num) throws PersistenciaException {
        return secuencia.k_esimo(con, num);
    }

    public ArrayList<VOJuguete> listarJuguetes(IConexion con) throws PersistenciaException {
        return secuencia.listarJuguetes(con);
    }

    public void borrarJuguetes(IConexion con) throws PersistenciaException {
        secuencia.borrarJuguetes(con);
    }
}
