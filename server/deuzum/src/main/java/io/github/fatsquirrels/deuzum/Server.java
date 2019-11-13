package io.github.fatsquirrels.deuzum;


import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.util.ArrayList;

import io.github.fatsquirrels.deuzum.Database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.IA.Bots.*;


/**
 * 
 * Clase encargada de gestionar los Sockets y procesar las peticiones.
 * 
 *
 */
public class Server implements Runnable{

	public static boolean isRunning = false;
	
	ArrayList<BotBase> bots;
	private ServerSocket serverSocket;
	private Connection connection;
	private int port, botCount;
	private String dbName;
	
	public Server() {
		
		
		
	}
	
	public void runServer() {
		try{
			
			execute();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("El server no ha podido ejecutarse");
			shutdown();
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setBotCount(int botCount) {
		this.botCount = botCount;
	}
	
	public void setDBName(String dbName) {
		this.dbName = dbName;
	}
	
	
	// Server Execution Functions
	
	/**
	 * Inicia el ServerSocket y activa hilos con cada conexi�n de un Socket.
	 */
    public void start() {
        try {
			serverSocket = new ServerSocket(this.port);
			while (true) 
				new ServerSocketHandler(serverSocket.accept(), connection).start();
	            
		} catch (IOException e) {
			System.err.println("The Server was closed");
			//e.printStackTrace();
		}
         
    }

    /**
     * Para el servidor.
     */
    public void stop() {
    	shutdown();  
    }
    
    // Server Bot Functionality
    
    /**
     * Crea un array de Objetos Bot.
     */
    public void createBotList() {
    	this.bots = new ArrayList<BotBase>();
    	for(int i = 0; i <this.botCount;i++) 
    		this.bots.add(BotGenerator.generateBot(BotType.CleaningBot, "Bot-"+i));
    	
    }

	public void run() {
		
		
	}


	/**
	 * Devuelve la conexion del Servidor.
	 * @return Connection 
	 */
	public Connection getConnection() {
		return connection;
	}
	
	public void shutdown() {
		isRunning = false;
		this.connection = null;
		this.bots.clear();
		ServerCommands.serverCommands.clear();
		try{
			this.serverSocket.close();
		}catch(IOException e) {
			System.err.println("Se ha cerrado la conexion con uno o varios sockets");
		}
		
	}
	
	/**
	 * Carga todos los modulos de ejecuci�n del servidor.
	 */
	public void execute() {
		createBotList();
		ServerCommands.createMethodArray();
		this.connection = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/"+this.dbName, "root", "");
		// TODO Add to the database the user status
		// El servidor tiene que tener una base de datos hosteando el estado de los usuarios
		// activos e inactivos
		
		isRunning = true;
	}
	
	/**
	 * Para y reinicia el servidor.
	 */
	public void restart() {
		try{
			shutdown();
			execute();
		}catch(Exception e) {
			shutdown();
		}
		
		
	}
	
	
}
