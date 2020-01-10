package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotFunctions;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.exceptions.CommandBuilderBuildException;

public class GroupBot extends BotBase implements BotFunctions{
	@SuppressWarnings("unused")
	private String name;
	private Connection conn;
	private long cantidad;
	private Thread hiloGrupo;
	
	public GroupBot(String name, long cantidad) {
		this.name = name;
		this.cantidad = cantidad;
		this.conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
	}
	

	public void execute() {
		hiloGrupo = new Thread(new Runnable() {

			public void run() {
				int users = 0;
				String[] arrIds = null;
				
				
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM usuario");
					ResultSet userCount = GeneralSQLFunctions.getExecQuery(conn, "SELECT count(id) FROM usuario");
					if(userCount.next()) {
						users = userCount.getInt("count(id)");
						arrIds = new String[users];
						userCount.close();
					}
					int counter = 0;
					while(ids.next()){
						String id = ids.getString("id");
						arrIds[counter] = id;
						counter++;
					}
					for (int i = 0; i < cantidad; i++) {
						int tempId = getLastId();
						GeneralSQLFunctions.insertEntryIntoDatabase(conn, "grupo", new String[] {"id","nombre", "descripcion"},
								new String[] {tempId+"", "BotGroup"+tempId, "Grupo creado por un bot"} );
						Random r = new Random(i);
						for (int j = 0; j < r.nextInt(8); j++) {
							String randomId = arrIds[r.nextInt(users)];
							int tempMemberId = getLastIdGroup();
							GeneralSQLFunctions.insertEntryIntoDatabase(conn, "grupomiembro", new String[] {"id", "id_grupo", "id_miembro", "permisos"}, 
									new String[] {tempMemberId+"", tempId+"", randomId, 0+""});
						}
					}						
				} catch (SQLException | CommandBuilderBuildException e) {
					JOptionPane.showMessageDialog(null, "Ha habido un error con el bot. Cerrando el hilo.");
					return;
				}
			}
		});
		hiloGrupo.run();		
	}

	public void stop(long tiempo) {
		try {
			Thread.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void kill() {
		hiloGrupo.interrupt();
	}
	
	public int getLastId() {
		int result = 0;
		try {
			Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM grupo ORDER BY id DESC");
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
	
	public int getLastIdGroup() {
		int result = 0;
		try {
			Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM grupomiembro ORDER BY id DESC");
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

}
