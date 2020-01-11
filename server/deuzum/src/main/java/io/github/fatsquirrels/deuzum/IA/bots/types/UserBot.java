package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotFunctions;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.exceptions.CommandBuilderBuildException;
import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

@Tested(tested=true)
public class UserBot extends BotBase implements BotFunctions{

	private Thread hiloUsuario;
	private Connection conn;
	private long cantidad;
	private static int BotIdCounter = 0;
	
	public UserBot(String namet, long cantidad) {
		this.name = namet + BotIdCounter++;
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
						GeneralSQLFunctions.insertEntryIntoDatabase(conn, "usuario", new String[] {"id","usuario", "pass", "preg_seguridad", "resp_seguridad","permisos"}, 
							new String[] {tempId+"",name + tempId*7, tempId+"", "1", "respuesta","1" });
					} catch (SQLException | CommandBuilderBuildException e) {
						JOptionPane.showMessageDialog(null, "Ha habido un error con el bot. Cerrando el hilo.");
						e.printStackTrace();
						return;
					}
				}
				
				
			}
		});
		hiloUsuario.run();
	}

	@Override
	public void stop(long tiempo) {
		try {
			Thread.sleep(tiempo);
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
	
}



