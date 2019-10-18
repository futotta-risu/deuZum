package deustoZumServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

import deustoZumServer.Database.GeneralSQLFunctions;
import deustoZumServer.IA.Bots.*;

public class Server implements Runnable{

	public static boolean isRunning = false;
	
	ArrayList<BotBase> bots;
	Properties properties;
	private ServerSocket serverSocket;
	private Connection connection;
	
	
	public Server() {
		try{
			execute();
		}catch(Exception e) {
			System.err.println("El server no ha podido ejecutarse");
			shutdown();
		}
		
		
	}
	
	public void openProperties() {
		this.properties = new Properties();
		try(FileInputStream f = new FileInputStream("./src/deustoZumServer/server.properties")){
			properties.load(f);
			
		}catch(FileNotFoundException e1) {
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Server Execution Functions
	
    public void start() {
        try {
			serverSocket = new ServerSocket(Integer.valueOf(this.properties.getProperty("server.port")));
			while (true) 
				new ServerSocketHandler(serverSocket.accept(), connection).start();
	            
		} catch (IOException e) {
			System.err.println("The Server was closed");
			//e.printStackTrace();
		}
         
    }

    public void stop() {
    	shutdown();  
    }
    
    // Server Bot Functionality
    
    public void createBotList() {
    	
    	for(int i = 0; i < Integer.parseInt(this.properties.getProperty("server.botCount"));i++) 
    		this.bots.add(BotGenerator.generateBot(BotType.CleaningBot, "Bot-"+i));
    	
    }

	@Override
	public void run() {
		
		
	}


	public Connection getConnection() {
		return connection;
	}
	
	public void shutdown() {
		isRunning = false;
		this.connection = null;
		this.properties = null;
		this.bots.clear();
		ServerCommands.serverCommands.clear();
		try{
			this.serverSocket.close();
		}catch(IOException e) {
			System.err.println("Se ha cerrado la conexion con uno o varios sockets");
		}
		
	}
	
	public void execute() {
		ServerCommands.createMethodArray();
		openProperties();
		createBotList();
		this.connection = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		// TODO Add to the database the user status
		// El servidor tiene que tener una base de datos hosteando el estado de los usuarios
		// activos e inactivos
		
		isRunning = true;
	}
	public void restart() {
		try{
			shutdown();
			execute();
		}catch(Exception e) {
			shutdown();
		}
		
		
	}
    
}
