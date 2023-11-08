package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.SQLException;

import logica.Juguete;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import persistencia.consultas.consultas;
import persistencia.poolConexiones.Conexion;
import persistencia.poolConexiones.IConexion;

public class DAOJuguetes extends IDAOJuguetes{
	
    public DAOJuguetes(int cedula) {
		super(cedula);
	}
    
	public void insback(IConexion _con, Juguete juguete) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			PreparedStatement statement = con.prepareStatement(consultas.insertarJuguete());
			statement.setInt(1, juguete.getNumero());
			statement.setString(2, juguete.getDescripcion());
			statement.setInt(3, this.cedulaNiño);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public int largo(IConexion _con) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			int largo = 0;
			PreparedStatement statement = con.prepareStatement(consultas.cantidadDeJuguetes());
			statement.setInt(1, this.cedulaNiño);
			ResultSet response = statement.executeQuery();
			if (response.next()) {
				largo = response.getInt("cantidad");
			}
			return largo;
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public Juguete k_esimo(IConexion _con, int k) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			Juguete j = null;
			PreparedStatement statement = con.prepareStatement(consultas.k_esimoJuguete());
			statement.setInt(1, this.cedulaNiño);
			statement.setInt(2, k);
			ResultSet response = statement.executeQuery();
			if (response.next()) {
				j = new Juguete(response.getInt("numero"), response.getString("descripcion"));
			}
			return j;
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public ArrayList<VOJuguete> listarJuguetes(IConexion _con) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			ArrayList<VOJuguete> lista = new ArrayList<VOJuguete>();
			PreparedStatement statement = con.prepareStatement(consultas.listarJuguetes());
			statement.setInt(1, this.cedulaNiño);
			ResultSet response = statement.executeQuery();
			while (response.next()) {
				lista.add(new VOJuguete(response.getInt("numero"), response.getString("descripcion"), this.cedulaNiño));
			}
			return lista;
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}

	public void borrarJuguetes(IConexion _con) throws PersistenciaException {
		try {
			Connection con = ((Conexion) _con).getCon();
			PreparedStatement statement = con.prepareStatement(consultas.borrarJuguetes());
			statement.setInt(1, this.cedulaNiño);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(3);
		}
	}
}
