package persistencia.poolConexiones;

import java.sql.Connection;

public class Conexion implements IConexion{
    private Connection con;

    public Conexion(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
