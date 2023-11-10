package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logica.Niño;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;
import persistencia.consultas.consultas;
import persistencia.poolConexiones.Conexion;
import persistencia.poolConexiones.IConexion;

public class DAONiños extends IDAONiños {
	public boolean member(IConexion _con, int cedula) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			PreparedStatement statement = con.prepareStatement(consultas.seleccionarNiño());
			statement.setInt(1, cedula);
			ResultSet response = statement.executeQuery();
			return response.next();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public void insert(IConexion _con, Niño niño) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			PreparedStatement statement = con.prepareStatement(consultas.insertarNiño());
			statement.setInt(1, niño.getCedula());
			statement.setString(2, niño.getNombre());
			statement.setString(3, niño.getApellido());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public Niño find(IConexion _con, int cedula) throws PersistenciaException{
		try {
			Connection con = ((Conexion) _con).getCon();
			Niño n = null;
			PreparedStatement statement = con.prepareStatement(consultas.seleccionarNiño());
			statement.setInt(1, cedula);
			ResultSet response = statement.executeQuery();
			if (response.next()) {
				n = new Niño(response.getInt("cedula"), response.getString("nombre"), response.getString("apellido"));
			}
			return n;
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public void delete(IConexion _con, int cedula) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			PreparedStatement statement = con.prepareStatement(consultas.borrarNiño());
			statement.setInt(1, cedula);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public ArrayList<VONiño> listarNiños(IConexion _con) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			ArrayList<VONiño> lista = new ArrayList<VONiño>();
			PreparedStatement statement = con.prepareStatement(consultas.listarNiños());
			ResultSet response = statement.executeQuery();
			while (response.next()) {
				lista.add(new VONiño(response.getInt("cedula"), response.getString("nombre"), response.getString("apellido")));
			}
			return lista;
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}
}
