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
		try {
			properties.load(new FileInputStream("server.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
