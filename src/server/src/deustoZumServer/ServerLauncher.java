package deustoZumServer;

import java.sql.*; 

public class ServerLauncher {

	
	public static void main(String[] args) {
		// TODO añadir una ventana para controlar el servidor
		ServerCommands.createMethodArray();
		Server server = new Server();
		server.start(1254);
		
		
	}
	
}
