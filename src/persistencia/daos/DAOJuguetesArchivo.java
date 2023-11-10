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


import logica.Juguete;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOJuguete;
import persistencia.poolConexiones.IConexion;

public class DAOJuguetesArchivo extends IDAOJuguetes {
	//Formato del archivo juguetes: numero=1,descripcion=Autito;numero=2,descripcion=Muñeca

	public DAOJuguetesArchivo(int cedula) {
		super(cedula);
	}

	private String generarRutaArchivo(int cedula) {
		return UtilArchivo.obtenerRuta() + "juguetes-" + Integer.toString(cedula) + ".txt";
	}

	public List<Map<String, String>> parseFile(String rutaArchivo) {
		List<Map<String, String>> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea = br.readLine(); // Se asume que todo está en una sola línea
			if (linea != null && !linea.isEmpty()) {
				// Divide cada juguete delimitado por ";"
				String[] juguetes = linea.split(";");
				for (String juguete : juguetes) {
					Map<String, String> map = new HashMap<>();
					// Divide el juguete en pares clave-valor
					String[] atributos = juguete.split(",");
					for (String atributo : atributos) {
						// Divide el par clave-valor
						String[] keyValue = atributo.split("=");
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

	public void insback(IConexion icon, Juguete juguete) throws PersistenciaException {
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

	public int largo(IConexion icon) throws PersistenciaException {

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

	public Juguete k_esimo(IConexion icon, int k) throws PersistenciaException {
		List<Map<String, String>> juguetes = parseFile(generarRutaArchivo(this.cedulaNiño));
		Juguete j = null;
		if (k >= 0 && k < juguetes.size()) {
			Map<String, String> jugueteKesimo = juguetes.get(k);
			j = new Juguete(Integer.parseInt(jugueteKesimo.get("numero")), jugueteKesimo.get("descripcion"));
		}
		return j;
	}

	public ArrayList<VOJuguete> listarJuguetes(IConexion _con) throws PersistenciaException {
		ArrayList<VOJuguete> lista = new ArrayList<VOJuguete>();

		List<Map<String, String>> juguetes = parseFile(generarRutaArchivo(this.cedulaNiño));
		
		if (!juguetes.isEmpty()) {
			System.out.println("juguetes: " + juguetes);
			for (Map<String, String> juguete : juguetes) {
				System.out.println("juguete: " + juguete);
				lista.add(new VOJuguete(Integer.parseInt(juguete.get("numero")), juguete.get("descripcion"), this.cedulaNiño));
			}
		}
		return lista;
	}

	public void borrarJuguetes(IConexion icon) throws PersistenciaException {
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