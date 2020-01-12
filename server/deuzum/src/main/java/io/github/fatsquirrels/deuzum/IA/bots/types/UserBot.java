package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotFunctions;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.exceptions.CommandBuilderBuildException;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

/**
 * Esta clase permite crear un objeto de UserBot que introduce la cantidad recibida
 * de usuario en la BD.
 * @see BotBase
 * @see BotFunctions
 */
@Tested(tested=true)
public class UserBot extends BotBase implements BotFunctions{

	private Thread hiloUsuario;
	private Connection connection;
	private Integer cantidad;
	
	/**
	 * Contructor de la clase, crea un UserBot con los parametros recibidos
	 * @param namet Nombre del bot
	 * @param cantidad Cantidad de usuarios que se quieren introducir en la BD
	 */
	public UserBot(String namet, Integer cantidad) {
		this.name = namet;
		this.cantidad = cantidad;
		this.connection = Server.getDefaultServerConnection();
		}
	

	/**
	 * Metodo que ejecuta el UserBot en un hilo
	 */
	public void execute() {
		
		hiloUsuario = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int tempId;
				for (long i = 0; i < cantidad; i++) {
					tempId = getLastId();
					try {
						GeneralSQLFunctions.insertEntryIntoDatabase(connection, "usuario", new String[] {"id","usuario", "pass", "preg_seguridad", "resp_seguridad","permisos"}, 
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

	/**
	 * Metodo que detiene el UserBot durante los segundos introducidos
	 */
	@Override
	public void stop(long tiempo) {
		try {
			Thread.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que elimina el UserBot
	 */
	@Override
	public void kill() {
		hiloUsuario.interrupt();
	}
	
	/**
	 * Metodo que obtiene de la BD el IdUsuario mas alto
	 * @return IdUsuario mas alto + 1
	 */
	public int getLastId() {
		int result = 0;
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM usuario ORDER BY id DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("id"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result +1;
	}
	
}



