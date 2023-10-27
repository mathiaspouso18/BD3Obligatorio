package persistencia.consultas;

public class consultas {
	public static String nuevoNinio() {
		return "INSERT INTO Ninios (cedula, nombre, apellido) VALUES (?, ?, ?)";
	}
	
	public static String nuevoJuguete() {
		return "INSERT INTO Juguetes (numero, descripcion, cedulaNinio) VALUES (?, ?, ?)";
	}
	
	public static String listarNinios() {
		return "SELECT * FROM Ninios ORDER BY cedula";
	}
	
	public static String listarJuguetes() {
		return "SELECT * FROM Juguetes WHERE cedulaNinio = ? ORDER BY numero";
	}
	
	public static String darDescripcion() {
		return "SELECT descripcion FROM Juguetes WHERE numero = ? AND cedulaNinio = ?";
	}
	
	public static String borrarJuguetes() {
		return "DELETE FROM Juguetes WHERE cedulaNinio = ?";
	}
	
	public static String borrarNinios() {
		return "DELETE FROM Ninios WHERE cedula = ?";
	}
	
	public static String existeNinio() {
		return "SELECT * FROM Ninios WHERE cedula = ?";
	}
	
	public static String existeJugueteNinio() {
		return "SELECT * FROM Jueguetes WHERE numero = ? AND cedulaNinio = ?";
	}
	
	public static String cantidadDeJuguetes() {
		return "SELECT COUNT(numero) AS cantidad FROM Juguetes WHERE cedulaNinio = ?";
	}
	
	public static String k_esimoJuguete() {
		return "SELECT * FROM Juguetes WHERE cedulaNinio = ? AND numero = ?";
	}
}
