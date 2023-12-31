package persistencia.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import logica.Niño;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VONiño;
import persistencia.poolConexiones.IConexion;

public class DAONiñosArchivo extends IDAONiños {
	private String generarRutaArchivo(int cedula) {
		return UtilArchivo.obtenerRuta() + "niño-" + Integer.toString(cedula) + ".txt";
	}

	private static Map<String, String> readFileIntoHashMap(String rutaArchivo) {
		Map<String, String> map = new HashMap<>();
		BufferedReader lectura = null;

		try {
			lectura = new BufferedReader(new FileReader(rutaArchivo));
			String lineaActual;

			while ((lineaActual = lectura.readLine()) != null) {
				String[] datos = lineaActual.split(";");
				for (String dato : datos) {
					String[] keyValue = dato.split("=");
					map.put(keyValue[0], keyValue.length > 1 ? keyValue[1] : null);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (lectura != null) lectura.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return map;
	}

	public boolean member(IConexion _con, int cedula) {
		Path ruta = Paths.get(generarRutaArchivo(cedula));
		return (Files.exists(ruta));
	}

	public void insert(IConexion _con, Niño niño) throws PersistenciaException {
		//Formato del contenido del archivo: "cedula=12345678;nombre=Juan;apellido=Perez"
		
		String cedula = Integer.toString(niño.getCedula());
		String nombre = niño.getNombre();
		String apellido = niño.getApellido();

		String ruta = generarRutaArchivo(niño.getCedula());

		try {
			FileWriter nuevoNiño = new FileWriter(ruta);
			nuevoNiño.write("cedula=" + cedula + ";nombre=" + nombre + ";apellido=" + apellido);
			nuevoNiño.close();

			FileWriter juguetesNiño = new FileWriter(ruta.replace("niño", "juguetes"));
			juguetesNiño.close();
		} catch (IOException e) {
			throw new PersistenciaException(0);
		}
	}

	public Niño find(IConexion _con, int cedula) throws PersistenciaException {
		String ruta = generarRutaArchivo(cedula);
		Niño n = null;
		if (member(_con, cedula)) {
			Map<String, String> niñoMap = readFileIntoHashMap(ruta);
			String nombre = niñoMap.get("nombre");
			String apellido = niñoMap.get("apellido");
			n = new Niño(cedula, nombre, apellido);
		}
		return n;
	}

	public void delete(IConexion _con, int cedula) throws PersistenciaException {
		File archivoNiño = new File(generarRutaArchivo(cedula));
		archivoNiño.delete();
		File archivoJuguetes = new File(generarRutaArchivo(cedula).replace("niño", "juguetes"));
		archivoJuguetes.delete();
	}

	public ArrayList<VONiño> listarNiños(IConexion _con) throws PersistenciaException {
		ArrayList<VONiño> lista = new ArrayList<VONiño>();
		File dir = new File(UtilArchivo.obtenerRuta());
		// Filtro que determina si el nombre del archivo comienza con "niño" y es un archivo (no una carpeta)
		FilenameFilter filtroNiños = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("niño") && new File(dir, name).isFile();
			}
		};
		File[] listaNiños = dir.listFiles(filtroNiños);
		if (listaNiños != null) {
			for (File niño : listaNiños) {
				Map<String, String> niñoMap = readFileIntoHashMap(niño.getPath());
				int cedula = Integer.parseInt(niñoMap.get("cedula"));
				String nombre = niñoMap.get("nombre");
				String apellido = niñoMap.get("apellido");
				lista.add(new VONiño(cedula, nombre, apellido));
			}
		}
		return lista;
	}
}
