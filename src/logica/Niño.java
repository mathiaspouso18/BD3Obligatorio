package logica;

import java.util.ArrayList;
import java.util.List;

import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import persistencia.daos.DAOJuguetes;

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

    public boolean tieneJuguete(int numero) throws PersistenciaException {
    	return secuencia.largo() > 0;
    }

    public void addJuguete(Juguete juguete) throws PersistenciaException {
        secuencia.insback(juguete);
    }

    public Juguete obtenerJuguete(int num) throws PersistenciaException {
    	return secuencia.k_esimo(num);
    }

    public ArrayList<VOJuguete> listarJuguetes() throws PersistenciaException {
    	return secuencia.listarJuguetes();
    }

    public void borrarJuguetes() {
        
    }
}
