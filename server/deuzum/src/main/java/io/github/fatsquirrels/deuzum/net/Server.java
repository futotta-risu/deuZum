package io.github.fatsquirrels.deuzum.net;


import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.util.ArrayList;

import io.github.fatsquirrels.deuzum.IA.bots.*;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;


/**
 * 
 * Clase encargada de gestionar los Sockets y procesar las peticiones.
 * 
 *
 */
public class Server implements Runnable{

	/**
	 * Variable que contiene el estado del servidor.
	 */
	
	public static boolean isRunning = false;
	
	/**
	 * Array que contiene los bots del servidor.
	 */
	private ArrayList<BotBase> bots;
	
	
	private ServerSocket serverSocket;
	private Connection connection;
	private int port, botCount;
	/**
	 * Nombre de la base de datos. Se extrae del archivo properties.
	 */
	private static String dbName;
	
	public Server() {
		
	}
	
	/**
	 * Intenta ejecutar el servidor. En caso de fallar hace un shutdown.
	 * @see Server#execute()
	 * @see Server#shutdown()
	 */
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
		if(port <0 || port > 65535) 
			System.err.println("El numero de puerto que ha intentado introducir no esta en el rango adecuado.");
		else
			this.port = port;
	}
	
	public void setBotCount(int botCount) {
		this.botCount = botCount;
	}
	
	public void setDBName(String dbName) {
		Server.dbName = dbName;
	}
	
	
	// Server Execution Functions
	
	/**
	 * Inicia el ServerSocket y activa hilos con cada conexi�n de un Socket.
	 */
    public void start() {
        try {
        	InetAddress addr = InetAddress.getByName("127.0.0.1");

        	// and now you can pass it to your socket-constructor
        	serverSocket =  new ServerSocket(this.port, 50, addr);
			System.out.println("Binded");
			System.out.println(serverSocket.getLocalPort());
			System.out.println(serverSocket.isBound());
			System.out.println(serverSocket.getInetAddress());
			System.out.println(serverSocket.getLocalSocketAddress());
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
	
	public static Connection createConnection() {
		return GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/"+Server.dbName, "root", "");
	}
	
	/**
	 * Para el servidor y resetea las variables.
	 */
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
		this.connection = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/"+Server.dbName, "root", "");
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
