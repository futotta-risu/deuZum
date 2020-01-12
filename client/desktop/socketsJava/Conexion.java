package socketsJava;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class Conexion {
	private final int PUERTO=1234;
	private final String HOST="localhost";
	protected String mesajeServidor;
	protected ServerSocket ss;
	protected Socket cs;
	protected DataOutputStream salidaServidor;
	
	
	public Conexion() {
		
	}
	
	
	/**
	 *Esto es un metodo que envia un socket al servido con un mensaje	
	 * @param message Aqui va la accion a realizar por el servidor
	 */
	public void sendSocket() {
	
		try {
	cs = new Socket(HOST,PUERTO); // El server por ahora esta en un servidor local y el puerto CREO que es ese

	PrintWriter writer = new PrintWriter(cs.getOutputStream(), true);

	BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

	// Para enviar un mensaje, una vez creado todo esto, nos basta con usar 
	writer.write("hola que tal");//TODO PREGUNTAR A ERIK
	
	writer.close(); // Esto es para cerrar el escritor
		}catch(Exception e) {
			System.out.println("Error: "+ e.getMessage());
			
		}
			}
}