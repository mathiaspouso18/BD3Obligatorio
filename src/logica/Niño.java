package logica;

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

    public boolean tieneJuguete(int numero) {
    	return false;
    }

    public void addJuguete(Juguete juguete) throws PersistenciaException {
        this.secuencia.insback(juguete);
    }

    public Juguete obtenerJuguete(int num) throws PersistenciaException {
       
        return this.secuencia.k_esimo(num);
    }

    public List<VOJuguete> listarJuguetes() throws PersistenciaException {
        
        return this.secuencia.listarJuguetes();
    }

    public void borrarJuguetes() {
        
    }
}
