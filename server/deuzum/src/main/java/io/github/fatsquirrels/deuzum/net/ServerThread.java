package io.github.fatsquirrels.deuzum.net;

import io.github.fatsquirrels.deuzum.res.ServerProperties;

public class ServerThread extends Thread{

	private Server server;

	@Override
	public void run() {
		// Checks if the server is alredy running
		if(Server.isRunning) 
			return;
		
		server = new Server();
		server.setDBName(ServerProperties.properties.getProperty("server.dbName"));
		server.setPort(Integer.valueOf(ServerProperties.properties.getProperty("server.port")));
		server.runServer();
		server.start();
		
	}
	
	public void stopServer() {
		server.stop();
		interrupt();
	}
	
}
