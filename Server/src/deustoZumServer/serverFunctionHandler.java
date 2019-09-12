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
		//add the handler funtion
		try {
			out = new PrintWriter(tempSocket.getOutputStream(), true);
	        in = new BufferedReader(
	          new InputStreamReader(tempSocket.getInputStream()));
	        
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            if (".".equals(inputLine)) {
	                out.println("bye");
	                break;
	            }
	            out.println(inputLine);
	        }

			in.close();
			out.close();
	        tempSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
}
