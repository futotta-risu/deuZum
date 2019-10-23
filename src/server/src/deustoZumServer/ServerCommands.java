package deustoZumServer;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.json.*;

interface Command{

	String runCommand(JSONObject data);
}

public class ServerCommands {

	public static Map<String, Command> serverCommands;
	
	
	public static void createMethodArray() {
		serverCommands = new HashMap<String, Command>();
		File file = new File("./data/methodList"); 
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String socketLine; 
			  while ((socketLine = br.readLine()) != null) {
				  // Este if comprueba si la linea es un comentario
				  if(socketLine.startsWith("#")) 
					  continue;
				  
				  //Cambiamos multiples espacios y tabuladores
				  String[] socketLineSplit = socketLine.trim().replaceAll("[ \\t]+", " ").split(" ");
				  
				  // Si solo son espacios o tabulacions
				  if(socketLineSplit.length == 1) 
					  continue;
				  
				  serverCommands.put(socketLineSplit[0], new Command() {

					public String runCommand(JSONObject data) {
						try {
							Class<?> temp = Class.forName(socketLineSplit[1]);
							
							java.lang.reflect.Method method;
							method = temp.getMethod(socketLineSplit[2], socketLineSplit.getClass());
							Object[] params = {data};
							return (String) method.invoke(null, params);
							
							// TODO cambiar los errores para algo más util
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						
						return null;
					}
				  });
			  }
			  br.close();
			  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  
		  
	}
	
	
	
}
