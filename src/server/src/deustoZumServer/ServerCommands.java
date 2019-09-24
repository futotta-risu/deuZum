package deustoZumServer;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

interface Command{

	String runCommand(String[] arr);
}

public class ServerCommands {

	public static Map<String, Command> serverCommands;
	
	public ServerCommands() {
		// TODO Rename this file
	}
	
	public static void createMethodArray() {
		serverCommands = new HashMap<String, Command>();
		File file = new File("./data/methodList"); 
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String socketLine; 
			  while ((socketLine = br.readLine()) != null) {
				  String[] socketLineSplit = socketLine.split(" ");
				  serverCommands.put(socketLineSplit[0], new Command() {

					public String runCommand(String[] arr) {
						try {
							Class<?> temp = Class.forName(socketLineSplit[1]);
							System.out.println(temp.getName());
							
							java.lang.reflect.Method method;
							method = temp.getMethod(socketLineSplit[2], socketLineSplit.getClass());
							System.out.println(method.getParameterCount());
							Object[] params = {arr};
							return (String) method.invoke(null, params);
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
			  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  
		  
	}
	
	
	
}