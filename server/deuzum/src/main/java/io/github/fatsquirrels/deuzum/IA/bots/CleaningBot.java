package io.github.fatsquirrels.deuzum.IA.bots;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.Server;

public class CleaningBot extends BotBase{
	
	private Connection conn;
	private Thread hiloLimpieza = null;
	

	public CleaningBot() {}
	
	public CleaningBot(String name) {
		this.name = name;
		this.conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
	}
	
	@Override
	public void execute() {
		hiloLimpieza = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ResultSet rs= null;
				ResultSet rsTransacciones = null;
				try {
					//TODO CAMBIAR ESTO
					rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT * FROM usuario");
					
					
					while(rs.next()) {
						String id = rs.getString("id");
						rsTransacciones = GeneralSQLFunctions.getExecQuery(conn, "SELECT COUNT(source) FROM transaccion WHERE source='" + id + "'");
						while(rsTransacciones.next()) {
						int cantidad = rsTransacciones.getInt(1);
						if(cantidad == 0) {
							GeneralSQLFunctions.execUpdate(conn, "DELETE FROM usuario WHERE id='" + id + "'");
							JOptionPane.showMessageDialog(null, "El usuario " + id +" se ha eliminado correctamente");
						}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		});
		hiloLimpieza.run();
		
	}

	@Override
	public void stop(long time) {
		try {
			hiloLimpieza.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloLimpieza.interrupt();
	}
	
	/*
	public static void main(String[] args) {
		CleaningBot cb = new CleaningBot("CleaningBot1", GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", ""));
		cb.execute();
	}
	*/

	

}
