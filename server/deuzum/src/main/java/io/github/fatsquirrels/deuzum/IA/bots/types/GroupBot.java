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
import io.github.fatsquirrels.deuzum.net.Server;

/**
 * Esta clase permite crear un objeto de GroupBot que introduce la cantidad recibida
 * de grupos en la BD.
 * @see BotBase
 * @see BotFunctions
 */
public class GroupBot extends BotBase implements BotFunctions{
	@SuppressWarnings("unused")
	private String name;
	private Connection connection;
	private long cantidad;
	private Thread hiloGrupo;
	
	/**
	 * Contructor de la clase, crea un GroupBot con los parametros recibidos
	 * @param namet Nombre del bot
	 * @param cantidad Cantidad de grupos que se quieren introducir en la BD
	 */
	public GroupBot(String name, long cantidad) {
		this.name = name;
		this.cantidad = cantidad;
		this.connection = Server.getDefaultServerConnection();
	}
	
	/**
	 * Metodo que ejecuta el GroupBot en un hilo.
	 * Se crea un grupo en la BD y se introducen en el una cantidad aleatoria de miembros
	 * (8 como maximo)
	 */
	public void execute() {
		hiloGrupo = new Thread(new Runnable() {

			public void run() {
				int users = 0;
				String[] arrIds = null;
				
				
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM usuario");
					ResultSet userCount = GeneralSQLFunctions.getExecQuery(connection, "SELECT count(id) FROM usuario");
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
						GeneralSQLFunctions.insertEntryIntoDatabase(connection, "grupo", new String[] {"id","nombre", "descripcion"},
								new String[] {tempId+"", "BotGroup"+tempId, "Grupo creado por un bot"} );
						Random r = new Random(i);
						for (int j = 0; j < r.nextInt(8); j++) {
							String randomId = arrIds[r.nextInt(users)];
							int tempMemberId = getLastIdGroup();
							GeneralSQLFunctions.insertEntryIntoDatabase(connection, "grupomiembro", new String[] {"id", "id_grupo", "id_miembro", "permisos"}, 
									new String[] {tempMemberId+"", tempId+"", randomId, 1+""});
						}
					}						
				} catch (SQLException | CommandBuilderBuildException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha habido un error con el bot. Cerrando el hilo.");
					return;
				}
			}
		});
		hiloGrupo.run();		
	}

	/**
	 * Metodo que detiene el GroupBot durante los segundos introducidos
	 */
	public void stop(long tiempo) {
		try {
			Thread.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que elimina el GroupBot
	 */
	public void kill() {
		hiloGrupo.interrupt();
	}
	
	/**
	 * Metodo que obtiene de la BD el idGrupo mas alto
	 * @return idGrupo mas alto + 1
	 */
	public int getLastId() {
		int result = 0;
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM grupo ORDER BY id DESC");
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
	
	/**
	 * Metodo que obtiene de la BD el idGrupoMiembro mas alto
	 * @return idGrupoMiembro mas alto + 1
	 */
	public int getLastIdGroup() {
		int result = 0;
		try {			
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM grupomiembro ORDER BY id DESC");
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
