package persistencia.consultas;

public class consultas {
	public String nuevoNinio () {
		return "INSERT INTO Ninios (cedula, nombre, apellido) VALUES (?, ?, ?);";
	}
	
	public String nuevoJuguete () {
		return "INSERT INTO Juguetes (numero, descripcion, cedulaNinio) VALUES (?, ?, ?);";
	}
	
	public String listarNinios () {
		return "SELECT * FROM Ninios ORDER BY cedula;";
	}
	
	public String listarJuguetes () {
		return "SELECT * FROM Juguetes WHERE cedulaNinio = ? ORDER BY numero;";
	}
	
	public String darDescripcion () {
		return "SELECT descripcion FROM Juguetes WHERE numero = ? AND cedulaNinio = ?;";
	}
	
	public String borrarJuguetes () {
		return "DELETE FROM Juguetes WHERE cedulaNinio = ?;";
	}
	
	public String borrarNinios () {
		return "DELETE FROM Ninios WHERE cedula = ?;";
	}
	
	public String existeNinio () {
		return "SELECT * FROM Ninios WHERE cedula = ?;";
	}
	
	public String existeJugueteNinio () {
		return "SELECT * FROM Jueguetes WHERE numero = ? AND cedulaNinio = ?;";
	}
}
