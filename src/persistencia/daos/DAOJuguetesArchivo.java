package persistencia.daos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.ConfigException;

import logica.Juguete;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;

public class DAOJuguetesArchivo {
	//Formato del archivo juguetes: numero=1,descripcion=Autito;numero=2,descripcion=Muñeca
    private int cedulaNiño;
    private String carpeta = "ruta_de_la_carpeta";
    
    private String generarRutaArchivo (int cedula) {
		return carpeta + "juguetes-" + Integer.toString(cedula) + ".txt";
	}
    
    public static List<Map<String, String>> parseFile(String rutaArchivo) {
        List<Map<String, String>> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = br.readLine(); // Se asume que todo está en una sola línea
            if (linea != null && !linea.isEmpty()) {
                // Divide cada juguete delimitado por ";"
                String[] records = linea.split(";");
                for (String record : records) {
                    Map<String, String> map = new HashMap<>();
                    // Divide el juguete en pares clave-valor
                    String[] keyValuePairs = record.split(", ");
                    for (String pair : keyValuePairs) {
                        // Divide el par clave-valor
                        String[] keyValue = pair.split("=");
                        if (keyValue.length == 2) {
                            map.put(keyValue[0].trim(), keyValue[1].trim());
                        }
                    }
                    list.add(map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public DAOJuguetesArchivo(int cedula) throws ConfigException {
    	this.cedulaNiño = cedula;
    }

    public void insback(Juguete juguete) throws PersistenciaException {
    	File archivoModificar = new File(generarRutaArchivo(this.cedulaNiño));
    	StringBuilder contenido = new StringBuilder();
    	 try (BufferedReader lectura = new BufferedReader(new FileReader(archivoModificar))) {
	        // Lee el archivo existente y guarda el contenido
	        String linea = lectura.readLine();
	        if (linea != null && !linea.isEmpty()) {
	            contenido.append(linea);
	        }
    	    } catch (IOException e) {
    	        throw new PersistenciaException(3);
    	    }

    	    // Agregar el nuevo juguete
    	    contenido.append(";numero=" + Integer.toString(juguete.getNumero()) + ",descripcion=" + juguete.getDescripcion());

    	    // Guardar
    	    try (FileWriter escritura = new FileWriter(archivoModificar, false)) { // 'false' para sobrescribir el archivo
    	        escritura.write(contenido.toString());
    	    } catch (IOException e) {
    	        throw new PersistenciaException(3);
    	    }
    }

    public int largo() throws PersistenciaException {
        
    	int largo = 0;
    	try (BufferedReader br = new BufferedReader(new FileReader(generarRutaArchivo(this.cedulaNiño)))) {
            String line = br.readLine();
            if (line != null) { // Si el archivo no está vacío
                for (char ch : line.toCharArray()) { // Convierte la línea a un array de caracteres y lo recorre
                    if (ch == ';') {
                    	largo++;
                    }
                }
                largo++; //Cada juguete es precedido por un ";" menos el primero, entonces se suma 1
            }
        } catch (IOException e) {
        	throw new PersistenciaException(3);
        }
    	return largo;
        
    }

    public Juguete k_esimo(int k) throws PersistenciaException {
    	List<Map<String, String>> juguetes = parseFile(generarRutaArchivo(this.cedulaNiño));
		Juguete j = null;
		if(k >= 0 && k < juguetes.size()) {
			Map<String, String> jugueteKesimo = juguetes.get(k);		
			j = new Juguete(Integer.parseInt(jugueteKesimo.get("numero")), jugueteKesimo.get("descripcion"));
		}
		return j;
    }

    public ArrayList<VOJuguete> listarJuguetes() throws PersistenciaException {
    	 ArrayList<VOJuguete> lista = new ArrayList<VOJuguete>();
		List<Map<String, String>> juguetes = parseFile(generarRutaArchivo(this.cedulaNiño));
		for( Map<String, String> juguete : juguetes) {				
			lista.add(new VOJuguete(Integer.parseInt(juguete.get("numero")), juguete.get("descripcion"), this.cedulaNiño));
		}
		return lista; 
    }

    public void borrarJuguetes() throws PersistenciaException {
    	 try {
             FileWriter fw = new FileWriter(generarRutaArchivo(this.cedulaNiño), false);
             //'false' indica que queremos sobrescribir todo
             fw.write("");
             fw.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}