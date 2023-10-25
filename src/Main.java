import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";

        Connection conn = DriverManager.getConnection(url, user, password);
        try{
        	Statement stmt = conn.createStatement();

            // Crear la base de datos Bedelia
            String sqlCrearBaseDatos = "CREATE DATABASE Guarderia";
            stmt.executeUpdate(sqlCrearBaseDatos);
            
            // Usar la base de datos Bedelia
            String sqlUsarBaseDatos = "USE Guarderia";
            stmt.executeUpdate(sqlUsarBaseDatos);

            // Crear la tabla Ninios 
            String sqlCrearTablaNinios = "CREATE TABLE Ninios ("
			            		+ "    cedula INT PRIMARY KEY,"
			            		+ "    nombre VARCHAR(45),"
			            		+ "    apellido VARCHAR(45)"
			            		+ ");";
            stmt.executeUpdate(sqlCrearTablaNinios);
            
         // Crear la tabla Juguetes  
            String sqlCrearTablaJuguetes  = "CREATE TABLE Juguetes ("
            		+ "    numero INT,"
            		+ "    descripcion VARCHAR(45),"
            		+ "    cedulaNinio INT,"
            		+ "    FOREIGN KEY (cedulaNinio) REFERENCES Ninios(cedula),"
            		+ "	   PRIMARY KEY (numero, cedulaNinio)"
            		+ ");";
            stmt.executeUpdate(sqlCrearTablaJuguetes);
            
         // Insertar datos  
            String sqlInsertarDatos  = "INSERT INTO Ninios (cedula, nombre, apellido) VALUES"
            		+ "(1234567, 'Kevin', 'McCallister'),"
            		+ "(2345678, 'Matilda', 'Wormwood'),"
            		+ "(3456789, 'Harry', 'Potter'),"
            		+ "(4567890, 'Merlina', 'Adams'),"
            		+ "(6789012, 'Jose', 'Perez');";
            stmt.executeUpdate(sqlInsertarDatos);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
        	conn.close();
        }   
        
	}

}