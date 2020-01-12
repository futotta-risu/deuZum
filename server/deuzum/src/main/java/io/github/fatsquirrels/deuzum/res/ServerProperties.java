package io.github.fatsquirrels.deuzum.res;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import io.github.fatsquirrels.deuzum.log.ArchivoLog;

public class ServerProperties {

	public static Properties properties;

	final private static ArchivoLog logger = new ArchivoLog("ServerProperties");
	
	/**
	 * Abre y carga el archivo properties.
	 */
	public static void openProperties() {
		properties = new Properties();
		try(FileInputStream f = new FileInputStream(Paths.PropertiesPath)){
			properties.load(f);
			properties.toString();
			logger.addLine(Level.INFO, "El archivo properties se ha cargado correctamente");
			
		}catch(FileNotFoundException e1) {
			System.err.println("El archivo no se encuentra en el lugar indicado.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateProperty(String property, String value) {
		properties.setProperty(property, value);
	}
	
	public static void storeProperties() {
		try {
			properties.store(new FileOutputStream(Paths.PropertiesPath), null);
		} catch (IOException e) {
			System.err.println("Error a la hora de guardar el archivo de Properties");
			e.printStackTrace();
		}
	}
}
