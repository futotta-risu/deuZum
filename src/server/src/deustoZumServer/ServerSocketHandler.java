package deustoZumServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

public class ServerSocketHandler extends Thread{

	Socket tempSocket;
	Connection conn;
    private PrintWriter out;
    private BufferedReader in;
	
	
	public ServerSocketHandler(Socket tempSocketi, Connection conn) {
		this.tempSocket = tempSocketi;
		this.conn = conn;
	}
	
	public void run() {
		try {
			out = new PrintWriter(tempSocket.getOutputStream(), true);
	        in = new BufferedReader(
	          new InputStreamReader(tempSocket.getInputStream()));
	        
	        // TODO Add a function to check whether a Hash exist for the user
	        
	        String command = in.readLine();
	        
	        // Dechiper the Command
	        String[] arr = in.readLine().split(" ");
	        out.println(ServerCommands.serverCommands.get(command).runCommand(arr));
	        

			in.close();
			out.close();
	        tempSocket.close();
		} catch (IOException e) {
			// TODO Ajustar tema de errores
			e.printStackTrace();
		}
        
	}
	
}
