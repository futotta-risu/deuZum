package deustoZumServer;

import java.sql.*; 

public class ServerLauncher {

	
	public static void main(String[] args) {
		// TODO a�adir una ventana para controlar el servidor
		ServerFunctionTreeHolder.createMethodArray();
		Server server = new Server();
		server.start(1254);
		
		
	}
	
}
