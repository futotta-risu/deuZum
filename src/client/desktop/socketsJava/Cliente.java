package socketsJava;

import java.io.DataOutputStream;
import java.io.IOException;
import socketsJava.Conexion;

public class Cliente extends Conexion{
	
	
	public Cliente() throws IOException {
		super("cliente");
	}
	
	public void starClient() {
		try {
			salidaServidor = new DataOutputStream(cs.getOutputStream());
			
			for (int i = 0; i < 2; i++) {
				salidaServidor.writeUTF("hola " + (i+1)+ "\n");
			}
			cs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) throws IOException {
		Cliente cli= new Cliente();
		
		System.out.println("Iniciando cliente\n");
		cli.starClient();
	}

}
