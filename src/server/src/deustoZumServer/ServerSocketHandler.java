package deustoZumServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

import org.json.JSONObject;

public class ServerSocketHandler extends Thread{

	Socket tempSocket;
    private PrintWriter out;
    private BufferedReader in;
	
	
	public ServerSocketHandler(Socket tempSocketi, Connection conn) {
		this.tempSocket = tempSocketi;
	}
	
	public void run() {
		try {
			out = new PrintWriter(tempSocket.getOutputStream(), true);
	        in = new BufferedReader(
	          new InputStreamReader(tempSocket.getInputStream()));
	        
	        // TODO Add a function to check whether a Hash exist for the user
	        
	        String command = in.readLine();
	        
	        // Dechiper the Command
	        JSONObject data = new JSONObject(in.readLine());
	        out.println(ServerCommands.serverCommands.get(command).runCommand(data));
	        

			in.close();
			out.close();
	        tempSocket.close();
		} catch (IOException e) {
			// TODO Ajustar tema de errores
			e.printStackTrace();
		}
        
	}
	
}
