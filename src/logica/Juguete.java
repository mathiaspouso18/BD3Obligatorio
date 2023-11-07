package logica;

public class Juguete {
    private int numero;
    private String descripcion;

    public Juguete(int numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
