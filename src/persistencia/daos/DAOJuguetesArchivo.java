package persistencia.daos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

	public ArrayList<Map<String, String>> parseFile(String rutaArchivo) {
		ArrayList<Map<String, String>> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea = br.readLine();
			if (linea != null && !linea.isEmpty()) {
				for (String jugueteFile : linea.split(";")) {
					Map<String, String> juguete = new HashMap<>();
					for (String atributoFile : jugueteFile.split(",")) {
						String[] keyValue = atributoFile.split("=");
						if (keyValue.length == 2) {
							juguete.put(keyValue[0].trim(), keyValue[1].trim());
						}
					}
					list.add(juguete);
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
			String linea = lectura.readLine();
			if (linea != null && !linea.isEmpty()) {
				contenido.append(linea);
			}
		} catch (IOException e) {
			throw new PersistenciaException(3);
		}
		contenido.append(";numero=" + Integer.toString(juguete.getNumero()) + ",descripcion=" + juguete.getDescripcion());
		try (FileWriter escritura = new FileWriter(archivoModificar, false)) {
			escritura.write(contenido.toString());
		} catch (IOException e) {
			throw new PersistenciaException(3);
		}
	}

	public int largo(IConexion icon) throws PersistenciaException {
		int largo = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(generarRutaArchivo(this.cedulaNiño)))) {
			String line = br.readLine();
			if (line != null) {
				for (char ch : line.toCharArray()) {
					if (ch == ';') {
						largo++;
					}
				}
				largo++;
			}
		} catch (IOException e) {
			throw new PersistenciaException(3);
		}
		return largo;

	}

	public Juguete k_esimo(IConexion icon, int k) throws PersistenciaException {
		Juguete juguete = null;
		ArrayList<Map<String, String>> juguetesFile = parseFile(generarRutaArchivo(this.cedulaNiño));
		int i = 0;
		while(juguete == null && i < juguetesFile.size()) {
			Map<String, String> jugueteFile = juguetesFile.get(i);
			i++;
			int num = Integer.parseInt(jugueteFile.get("numero"));
			if(num == k ) juguete = new Juguete(num, jugueteFile.get("descripcion"));
		}
		return juguete;
	}

	public ArrayList<VOJuguete> listarJuguetes(IConexion _con) throws PersistenciaException {
		ArrayList<VOJuguete> lista = new ArrayList<VOJuguete>();
		ArrayList<Map<String, String>> juguetes = parseFile(generarRutaArchivo(this.cedulaNiño));
		
		if (!juguetes.isEmpty()) {
			for (Map<String, String> juguete : juguetes) {
				lista.add(new VOJuguete(Integer.parseInt(juguete.get("numero")), juguete.get("descripcion"), this.cedulaNiño));
			}
		}
		return lista;
	}

	public void borrarJuguetes(IConexion icon) throws PersistenciaException {
		try {
			FileWriter fw = new FileWriter(generarRutaArchivo(this.cedulaNiño), false);
			fw.write("");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
