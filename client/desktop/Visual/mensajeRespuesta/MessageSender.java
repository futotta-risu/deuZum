package mensajeRespuesta;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

public class MessageSender implements Runnable  {


	Socket s;
	DataOutputStream dos;
	PrintWriter pw;
	BufferedReader br;
	String command;
	JSONObject data;
	
	public MessageSender(String commandt, JSONObject datat){
		data = datat;
		command = commandt;
	}
	
	@Override
	public void run() {
		
		try{
			s = new Socket("localhost",64321);
			pw = new PrintWriter(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw.write(command+"\n");
			pw.flush();
			pw.write(data.toString()+"\n");
			pw.flush();
			ServerRespuesta.result = br.readLine();
			pw.close();
			s.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
		

	
}
