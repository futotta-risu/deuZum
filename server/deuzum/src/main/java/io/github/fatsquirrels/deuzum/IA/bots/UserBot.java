package io.github.fatsquirrels.deuzum.IA.bots;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;

public class UserBot extends BotBase{

	private Thread hiloUsuario;
	private Connection conn;
	private long cantidad;
	private String name = "UserBot";
	private static int BotIdCounter = 0;
	
	public UserBot(long cantidad) {
		this.name = this.name + BotIdCounter++;
		this.cantidad = cantidad;
		this.conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
		}
	

	public void execute() {
		
		hiloUsuario = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int tempId;
				for (long i = 0; i < cantidad; i++) {
					tempId = getLastId();
					try {
						GeneralSQLFunctions.insertEntryIntoDatabase(conn, "usuario", new String[] {"id","usuario", "contraseña", "preg_seguridad", "resp_seguridad"}, 
							new String[] {tempId+"",name + tempId*7, tempId+"", "1", "respuesta" });
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				
			}
		});
		hiloUsuario.run();
	}

	@Override
	public void stop(long tiempo) {
		try {
			hiloUsuario.sleep(tiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloUsuario.interrupt();
	}
	
	public int getLastId() {
		int result = 0;
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM usuario ORDER BY id DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("id"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		result = result +1;
		return result;
	}
	
	/*
	public static void main(String[] args) {
		UserBot ub1= new UserBot(10000);
		ub1.execute();
	*/
}



