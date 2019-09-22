package deustoZumServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class serverFunctionHandler extends Thread{

	Socket tempSocket;
    private PrintWriter out;
    private BufferedReader in;
	
	
	public serverFunctionHandler(Socket tempSocketi) {
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
	        
	        String[] arr = in.readLine().split(" ");
	        
	        
	        out.println(ServerFunctionTreeHolder.serverCommands.get(command).runCommand(arr));
	        

			in.close();
			out.close();
	        tempSocket.close();
		} catch (IOException e) {
			// TODO Ajustar tema de errores
			e.printStackTrace();
		}
        
	}
	
}
