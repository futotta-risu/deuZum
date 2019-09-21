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

public class ServerFunctionTreeHolder {

	public static Map<String, Command> metodos;
	
	public ServerFunctionTreeHolder() {
		// TODO Rename this file
	}
	
	public static void createMethodArray() {
		metodos = new HashMap<String, Command>();
		File file = new File("./data/methodList"); 
		System.out.println(file.getAbsolutePath());
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st; 
			  while ((st = br.readLine()) != null) {
				  String[] splitted = st.split(" ");
				  metodos.put(splitted[0], new Command() {

					public String runCommand(String[] arr) {
						try {
							Class<?> temp = Class.forName(splitted[1]);
							System.out.println(temp.getName());
							
							java.lang.reflect.Method method;
							method = temp.getMethod(splitted[2], splitted.getClass());
							System.out.println(method.getParameterCount());
							Object[] params = {arr};
							return (String) method.invoke(null, params);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
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
