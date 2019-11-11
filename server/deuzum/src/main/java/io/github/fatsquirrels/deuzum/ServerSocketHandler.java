package io.github.fatsquirrels.deuzum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

import org.json.JSONObject;

public class ServerSocketHandler extends Thread{

	Socket socket;
    private PrintWriter out;
    private BufferedReader in;
	
	/**
	 * Crea un hilo que contiene un socket recibido y una conexi�n para la ejecuci�n de comandos del cliente.
	 * @param Socket socket
	 * @param Connection conn
	 */
	public ServerSocketHandler(Socket socket, Connection conn) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
	        in = new BufferedReader(
	          new InputStreamReader(socket.getInputStream()));
	        
	        // TODO Add a function to check whether a Hash exist for the user
	        
	        String command = in.readLine();
	        
	        // Dechiper the Command
	        JSONObject data = new JSONObject(in.readLine());
	        out.println(ServerCommands.serverCommands.get(command).runCommand(data));
	        

			in.close();
			out.close();
	        socket.close();
		} catch (IOException e) {
			// TODO Ajustar tema de errores
			e.printStackTrace();
		}
        
	}
	
}
