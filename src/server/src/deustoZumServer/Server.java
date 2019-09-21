package deustoZumServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.util.Properties;

public class Server {

	Properties properties;
	private ServerSocket serverSocket;

	public Server() {
		//TODO Add the propierties file checker
		// TODO Add to the database the user status
		// El servidor tiene que tener una base de datos hosteando el estado de los usuarios
		// activos e inactivos
	}
	
    public void start(int port) {
        try {
			serverSocket = new ServerSocket(port);
			while (true) 
				new serverFunctionHandler(serverSocket.accept()).start();
	            
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
    }

    public void stop() {
    	try {
    		serverSocket.close();

    	}catch(IOException ex1) {
    		System.err.println("Ha habido un error al cerrar el servidor");
    	}
    	        
    }
    
}
