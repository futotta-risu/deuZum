package socketsJava;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import socketsJava.Conexion;
import sun.applet.Main;
public class Servidor extends Conexion{
	public Servidor() throws IOException{	
		super("sercidor");
	}
	
	public void startServer() {
		try {
			System.out.println("esperando...");
			cs= ss.accept();
			
			System.out.println("cliente en linea");
			
			salidaCliente = new DataOutputStream(cs.getOutputStream());
			salidaCliente.writeUTF("petición recibida y aceptada");
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			
			while ((mesajeServidor = entrada.readLine())!= null) {
			System.out.println("Fin de la conexion");
			
			ss.close();
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	public static void main(String[] args) throws IOException {
	
		Servidor serv = new Servidor();
		
		System.out.println("iniciando servidor\n");
		serv.startServer();
	}
}
