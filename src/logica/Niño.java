package logica;

import java.util.List;

import persistencia.secuencia.DAOJuguetes;

public class Niño {
    private int cedula;
    private String nombre;
    private String apellido;
    private DAOJuguetes secuencia;

    public Niño(int cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
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
        // Implementar lógica
        return false;
    }

    public void addJuguete(Juguete juguete) {
        // Implementar lógica
    }

    public Juguete obtenerJuguete(int num) {
        // Implementar lógica
        return null;
    }

    public List<Juguete> listarJuguetes() {
        // Implementar lógica
        return null;
    }

    public void borrarJuguetes() {
        // Implementar lógica
    }
}
