package deustoZumServer;

import java.sql.*; 

public class ServerLauncher {

	
	public static void main(String[] args) {
		//Server server = new Server();
		//server.start(8080);
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/sonoo","root","root");
	}
	
}
