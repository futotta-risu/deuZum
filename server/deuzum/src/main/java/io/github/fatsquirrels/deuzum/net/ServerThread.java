package io.github.fatsquirrels.deuzum.net;

import io.github.fatsquirrels.deuzum.res.ServerProperties;

public class ServerThread extends Thread{

	private Server server;

	@Override
	public void run() {
		
		if(Server.isRunning) 
			return;
		
		server = new Server();
		server.setDBName(ServerProperties.properties.getProperty("server.dbName"));
		server.setDBPort(ServerProperties.properties.getProperty("server.dbPort"));
		server.setDBUser(ServerProperties.properties.getProperty("server.dbUser"));
		server.setDBPass(ServerProperties.properties.getProperty("server.dbPass"));
		server.setPort(Integer.valueOf(ServerProperties.properties.getProperty("server.port")));
		server.runServer();
		server.start();
		
	}
	
	public void stopServer() {
		server.stop();
		interrupt();
	}
	
}
