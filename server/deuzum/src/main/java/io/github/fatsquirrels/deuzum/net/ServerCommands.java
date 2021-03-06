package io.github.fatsquirrels.deuzum.net;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import  java.lang.reflect.Method;

import org.json.*;

import io.github.fatsquirrels.deuzum.log.ArchivoLog;

/**
 * Interfaz especifica para guardar metodos dentro del HashMap de comandos del servidor.
 *	
 */
interface Command{

	String runCommand(JSONObject data) throws Exception;
}

/**
 * Contenedor del mapa de comandos.
 * 
 * @see #createMethodArray()
 * @see #serverCommands
 */
public class ServerCommands {

	/**
	 * Mapa que contiene los commandos y sus respectivas funciones.
	 */
	public static Map<String, Command> serverCommands;

	final private static ArchivoLog logger = new ArchivoLog("ServerCommands");
	
	/**
	 * Abre el mapa que contiene una lista de direcciones de metodos y las guarda en un HashMap. 
	 */
	public static void createMethodArray() {
		serverCommands = new HashMap<String, Command>();
		File file = new File("./data/methodList"); 
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String fileLine; 
			while ((fileLine = br.readLine()) != null) {
				// Este if comprueba si la linea es un comentario
				if(fileLine.startsWith("#")) 
					continue;
				  
				
				//Cambiamos multiples espacios y tabuladores
				final String[] fileLineSplit = fileLine.trim().replaceAll("[ \\t]+", " ").split(" ");
				  
				// Si solo son espacios o tabulacions
				if(fileLineSplit.length != 3) 
					continue;
				  
				serverCommands.put(fileLineSplit[0], new Command() {
					public String runCommand(JSONObject data) {
						try {
							Class<?> temp = Class.forName(fileLineSplit[1]);
							
							Method method = temp.getMethod(fileLineSplit[2], (new JSONObject()).getClass());
							System.out.println(method.getName());
							System.out.println(temp.getName());
							for(Class<?> a : method.getParameterTypes())
								System.out.println(a.getName());
							return (String) method.invoke(null, new Object[] {data});
							
							// TODO hacer algo con las excepciones aqui
							
						} catch (ClassNotFoundException | NoSuchMethodException | SecurityException 
								| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							logger.addLineError(Level.SEVERE, e.getMessage(), e);
							e.printStackTrace();
						} 
						return null;
					}
				  });
			  }
			  br.close();
			  
		} catch (IOException e) {
			logger.addLineError(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
		  
		  
	}
	
	
	
}
