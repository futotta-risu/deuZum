package io.github.fatsquirrels.deuzum.net;


import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.log.archivoLog;
import io.github.fatsquirrels.deuzum.res.Strings;


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
	public static boolean serverLoadFailed = false;
	

	
	
	private ServerSocket serverSocket;
	private Connection connection;
	private int port;
	/**
	 * Nombre de la base de datos. Se extrae del archivo properties.
	 */
	static String dbName,dbPort,dbUser,dbPass;
	
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
			System.err.println("El server no ha podido ejecutarse");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,Strings.server_execution_error_body,Strings.server_execution_error_tittle,1);
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
	
	
	public void setDBName(String dbName) {
		Server.dbName = dbName;
	}
	
	public void setDBPort(String dbPort) {
		Server.dbPort = dbPort;
	}
	public void setDBUser(String dbUser) {
		Server.dbUser = dbUser;
	}
	public void setDBPass(String dbPass) {
		Server.dbPass = dbPass;
	}
	
	public static Connection getDefaultServerConnection(){
		try {
			return GeneralSQLFunctions.connectToDatabase(
					"jdbc:mysql://localhost:" + Server.dbPort+"/"+Server.dbName, Server.dbUser, Server.dbPass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Server Execution Functions
	
	/**
	 * Inicia el ServerSocket y activa hilos con cada conexi�n de un Socket.
	 */
    public void start() {
    	isRunning = true;
        try {
        	InetAddress addr = InetAddress.getByName("127.0.0.1");

        	serverSocket =  new ServerSocket(this.port, 50, addr);
        	
			while (true) 
				new ServerSocketHandler(serverSocket.accept(), connection).start();
	            
		} catch (IOException e) {
			isRunning = false;
			System.err.println("The Server was closed");
		}
         
    }

    /**
     * Para el servidor.
     */
    public void stop() {
    	serverLoadFailed = false;
    	isRunning = false;
		this.connection = null;
		ServerCommands.serverCommands.clear();
		try{
			this.serverSocket.close();
		}catch(IOException e) {
			archivoLog.addLineError(Level.INFO, e.getMessage(), e);
			System.err.println("Se ha cerrado la conexion con uno o varios sockets");
		} 
    }
    
    // Server Bot Functionality
    

	public void run() {
		
		
	}


	/**
	 * Devuelve la conexion del Servidor.
	 * @return Connection 
	 */
	public Connection getConnection() {
		return connection;
	}
	
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		return GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:" + Server.dbPort+"/"+Server.dbName, Server.dbUser, Server.dbPass);
	}
	
	/**
	 * Para el servidor y resetea las variables.
	 */
	public void shutdown() {
		serverLoadFailed = true;
		isRunning = false;
		this.connection = null;
		ServerCommands.serverCommands.clear();
		try{
			this.serverSocket.close();
		}catch(IOException e) {
			System.err.println("Se ha cerrado la conexion con uno o varios sockets");
		}
		
	}
	
	/**
	 * Carga todos los modulos de ejecuci�n del servidor.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void execute() throws ClassNotFoundException, SQLException {
		ServerCommands.createMethodArray();
		this.connection = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:" + Server.dbPort+"/"+Server.dbName, Server.dbUser, Server.dbPass);
		// TODO Add to the database the user status
		// El servidor tiene que tener una base de datos hosteando el estado de los usuarios
		// activos e inactivos
		
	}
	
	/**
	 * Para y reinicia el servidor.
	 */
	public void restart() {
		try{
			shutdown();
			execute();
		}catch(Exception e) {
			archivoLog.addLineError(Level.INFO, e.getMessage(), e);
			shutdown();
		}
		
		
	}
	
	
}
