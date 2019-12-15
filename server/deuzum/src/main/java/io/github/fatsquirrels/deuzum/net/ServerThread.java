package io.github.fatsquirrels.deuzum.net;

import io.github.fatsquirrels.deuzum.visual.ServerHandlerFrame;

public class ServerThread extends Thread{

	private Server server;
	


	@Override
	public void run() {
		// Checks if the server is alredy running
		if(Server.isRunning) 
			return;
		
		server = new Server();
		server.setDBName(ServerHandlerFrame.properties.getProperty("server.dbName"));
		server.setBotCount(Integer.valueOf(ServerHandlerFrame.properties.getProperty("server.botCount")));
		server.setPort(Integer.valueOf(ServerHandlerFrame.properties.getProperty("server.port")));
		server.runServer();
		server.start();
		
	}
	
	
	public void stopServer() {
		server.stop();
		interrupt();
	}
	
}
