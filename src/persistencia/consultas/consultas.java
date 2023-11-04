package persistencia.consultas;

public class consultas {
	
	/*  /---------------\
	 *  |  TABLA NIÑOS  |
	 *	\---------------/
	 */
	
	public static String insertarNiño() {
		return "INSERT INTO Ninios (cedula, nombre, apellido) VALUES (?, ?, ?)";
	}

	public static String listarNiños() {
		return "SELECT * FROM Ninios ORDER BY cedula";
	}
	
	public static String borrarNiño() {
		return "DELETE FROM Ninios WHERE cedula = ?";
	}
	
	public static String seleccionarNiño() {
		return "SELECT * FROM Ninios WHERE cedula = ?";
	}
	
	/*  /------------------\
	 *  |  TABLA JUGUETES  |
	 *	\------------------/
	 */
	
	public static String insertarJuguete() {
		return "INSERT INTO Juguetes (numero, descripcion, cedulaNinio) VALUES (?, ?, ?)";
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
	
	public static String seleccionarJugueteNiño() {
		return "SELECT * FROM Jueguetes WHERE numero = ? AND cedulaNinio = ?";
	}
	
	public static String cantidadDeJuguetes() {
		return "SELECT COUNT(numero) AS cantidad FROM Juguetes WHERE cedulaNinio = ?";
	}
	
	public static String k_esimoJuguete() {
		return "SELECT * FROM Juguetes WHERE cedulaNinio = ? AND numero = ?";
	}
}
