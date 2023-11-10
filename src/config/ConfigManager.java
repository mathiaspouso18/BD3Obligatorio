package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static ConfigManager instance;
	//Se usa en entornos de programación multihilo, 
	//donde varios hilos pueden obtener la instancia al mismo tiempo
	//lo vi buscando como hacer el singleton, si no sirve lo pelamos
	private static final Object lock = new Object();
	private Properties properties;

	private ConfigManager() throws ConfigException {
		properties = new Properties();
		cargarConfig();
	}

	public static ConfigManager getInstance() throws ConfigException {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new ConfigManager();
				}
			}
		}
		return instance;
	}

	//Se carga el archivo de configuración
	private void cargarConfig() throws ConfigException {
		String nomArch = "src/config/Config.properties";
		try (InputStream input = new FileInputStream(nomArch)) {
			properties.load(input);
		} catch (IOException ex) {
			throw new ConfigException(1);
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int getIntProperty(String key) throws ConfigException {
		String value = getProperty(key);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new ConfigException(2);
		}
	}
}
