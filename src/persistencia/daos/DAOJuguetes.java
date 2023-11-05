package persistencia.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import logica.Juguete;
import logica.Niño;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import persistencia.consultas.consultas;

public class DAOJuguetes {
    private int cedulaNiño;
    private String url = "jdbc:mysql://localhost:3306/guarderia";
    private String user = "root";
    private String password = "root";
    Connection con;
    
    public DAOJuguetes(int cedula) {
    	this.cedulaNiño = cedula;
    }
    
    private void crearCon() throws PersistenciaException {
        try {
        	con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new PersistenciaException(1);
		}
    }
    
    private void cerrarCon() throws PersistenciaException {
    	try {
			con.close();
		} catch (SQLException e) {
			throw new PersistenciaException(2);
		}
    }

    public void insback(Juguete juguete) throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.insertarJuguete());
			statement.setInt(1, juguete.getNumero());
			statement.setString(2, juguete.getDescripcion());
			statement.setInt(3, this.cedulaNiño);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }

    public int largo() throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.cantidadDeJuguetes());
			statement.setInt(1, this.cedulaNiño);
			ResultSet response = statement.executeQuery();
			return response.getInt("cantidad");
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }

    public Juguete k_esimo(int k) throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.k_esimoJuguete());
			statement.setInt(1, this.cedulaNiño);
			statement.setInt(2, k);
			ResultSet response = statement.executeQuery();
			return new Juguete(response.getInt("numero"), response.getString("descripcion"));
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }

    public ArrayList<VOJuguete> listarJuguetes() throws PersistenciaException {
    	 try {
    		crearCon();
    		ArrayList<VOJuguete> lista = new ArrayList<VOJuguete>();
 			PreparedStatement statement = con.prepareStatement(consultas.listarJuguetes());
 			statement.setInt(1, this.cedulaNiño);
 			ResultSet response = statement.executeQuery();
 			while(response.next()) {				
 				lista.add(new VOJuguete(response.getInt("numero"), response.getString("descripcion"), this.cedulaNiño));
 			}
 			return lista;
 		} catch (SQLException e) {
 			throw new PersistenciaException(3);
 		} finally {
 			cerrarCon();
 		}
    }

    public void borrarJuguetes() throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.borrarJuguetes());
			statement.setInt(1, this.cedulaNiño);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }
}
