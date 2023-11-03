package persistencia.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Juguete;
import logica.Niño;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;
import persistencia.consultas.consultas;

public class DAONiños {
    private String url = "jdbc:mysql://localhost:3306/guarderia";
    private String user = "root";
    private String password = "root";
    Connection con;
    ArrayList<Niño> secuencia;
    
    public DAONiños() {
    	secuencia = new ArrayList<Niño>();
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

    public boolean member(int cedula) throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.seleccionarNiño());
			statement.setInt(1, cedula);
			ResultSet response = statement.executeQuery();
			return response.next();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }

    public void insert(Niño niño) throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.insertarNiño());
			statement.setInt(1, niño.getCedula());
			statement.setString(2, niño.getNombre());
			statement.setString(3, niño.getApellido());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }

    public Niño find(int cedula) throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.seleccionarNiño());
			statement.setInt(1, cedula);
			ResultSet response = statement.executeQuery();
			return new Niño(response.getInt("cedula"), response.getString("nombre"), response.getString("apellido"));
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }

    public void delete(int cedula) throws PersistenciaException {
        try {
        	crearCon();
			PreparedStatement statement = con.prepareStatement(consultas.borrarNiño());
			statement.setInt(1, cedula);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }

    public List<VONiño> listarNiños() throws PersistenciaException {
        try {
        	crearCon();
        	List<VONiño> lista = new ArrayList<VONiño>();
			PreparedStatement statement = con.prepareStatement(consultas.listarNiños());
			ResultSet response = statement.executeQuery();
			while(response.next()) {				
				lista.add(new VONiño(response.getInt("cedula"), response.getString("nombre"), response.getString("apellido")));
			}
			return lista;
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		} finally {
			cerrarCon();
		}
    }
}