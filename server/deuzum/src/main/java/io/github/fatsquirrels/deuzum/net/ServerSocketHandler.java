package io.github.fatsquirrels.deuzum.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Level;

import org.json.JSONObject;

import io.github.fatsquirrels.deuzum.log.ArchivoLog;


/**
 * Thread encargado de leer y responder a los sockets.
 *
 */
public class ServerSocketHandler extends Thread{

	Socket socket;
    private PrintWriter out;
    private BufferedReader in;
	

	final private static ArchivoLog logger = new ArchivoLog("ServerSocketHandler");
	/**
	 * Crea un hilo que contiene un socket recibido y una conexi�n para la ejecuci�n de comandos del cliente.
	 * @param Socket socket
	 * @param Connection conn
	 */
	public ServerSocketHandler(Socket socket, Connection conn) {
		System.out.println("Conectado");
		this.socket = socket;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream());
	        in = new BufferedReader(
	          new InputStreamReader(socket.getInputStream()));
	        // TODO Add a function to check whether a Hash exist for the user
	        String command = in.readLine();
	        // Dechiper the Command
	        JSONObject data = new JSONObject(in.readLine());
	        
	        // Process the command
	        String result = "";
	        try{
	        	result = ServerCommands.serverCommands.get(command).runCommand(data);
	        }catch(Exception e) {
	        	result = "&" + e.getMessage();
	        }
	        
	        out.println(result+"\n");
	        out.flush();
			in.close();
			out.close();
	        socket.close();
		} catch (IOException e) {
			logger.addLineError(Level.SEVERE, e.getMessage(), e);
		}
        
	}
	
}
