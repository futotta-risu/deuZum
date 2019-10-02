package deustoZumServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.Connection;

import deustoZumServer.IA.Bots.*;

public class Server implements Runnable{

	ArrayList<BotBase> bots;
	Properties properties;
	private ServerSocket serverSocket;
	private java.sql.Connection connection;
	
	
	public Server() {

		properties = new Properties();
		try {
			properties.load(new FileInputStream("./src/deustoZumServer/server.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		createBotList();
		connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		// TODO Add to the database the user status
		// El servidor tiene que tener una base de datos hosteando el estado de los usuarios
		// activos e inactivos
	}
	
	
	// Server Execution Functions
	
    public void start(int port) {
        try {
			serverSocket = new ServerSocket(port);
			while (true) 
				new ServerSocketHandler(serverSocket.accept()).start();
	            
		} catch (IOException e) {
			System.err.println("The Server was closed");
			//e.printStackTrace();
			
		}
        
        
    }

    public void stop() {
    	try {
    		serverSocket.close();

    	}catch(IOException ex1) {
    		System.err.println("Ha habido un error al cerrar el servidor");
    		ex1.printStackTrace();
    		
    
    	}
    	        
    }
    
    // Server Bot Functionality
    
    public void createBotList() {
    	
    	for(int i = 0; i < Integer.parseInt(this.properties.getProperty("server.botCount"));i++) 
    		this.bots.add(BotGenerator.generateBot(BotType.CleaningBot, "Bot-"+i));
    	
    	
    }

	@Override
	public void run() {
		
		
	}
    
	// Database Functions
	public void connectToDatabase(String direction, String user, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(direction,user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public java.sql.Connection getConnection() {
		return connection;
	}
	
    
}
