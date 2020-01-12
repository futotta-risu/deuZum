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
 * Esta clase permite crear un objeto de ProyectBot que introduce la cantidad recibida
 * de proyectos en la BD.
 * @see BotBase
 * @see BotFunctions
 */
public class ProyectBot extends BotBase implements BotFunctions{
	@SuppressWarnings("unused")
	private String name;
	private Connection connection;
	private Thread hiloProyecto;
	private long cantidad;
	
	/**
	 * Contructor de la clase, crea un ProyectBot con los parametros recibidos
	 * @param namet Nombre del bot
	 * @param cantidad Cantidad de proyectos que se quieren introducir en la BD
	 */
	public ProyectBot(String name, long cantidad) {
		this.name = name;
		this.connection = Server.getDefaultServerConnection();
		this.cantidad = cantidad;
	}
	
	/**
	 * Metodo que ejecuta el ProyectBot en un hilo.
	 * Se crea un proyecto en la BD asociado a un grupo aletorio y se introducen
	 * los participantes del grupo en el proyecto.
	 */
	@Override
	public void execute() {
		hiloProyecto = new Thread(new Runnable() {

			public void run() {
				int groups = 0;
				String[] arrIds = null;
				
				
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM grupo");
					ResultSet groupCount = GeneralSQLFunctions.getExecQuery(connection, "SELECT count(id) FROM grupo");
					if(groupCount.next()) {
						groups = groupCount.getInt("count(id)");
						arrIds = new String[groups];
						groupCount.close();
					}
					int counter = 0;
					while(ids.next()){
						String id = ids.getString("id");
						arrIds[counter] = id;
						counter++;
					}
					for (int i = 0; i < cantidad; i++) {
						int tempId = getLastId();
						Random r = new Random(i);
						String randomId = arrIds[r.nextInt(groups)];
						String cantidad = Integer.toString(r.nextInt(999));
						GeneralSQLFunctions.insertEntryIntoDatabase(connection, "proyecto", new String[] {"id","id_grupo", "nombre","descripcion", "deuda"},
								new String[] {tempId+"",randomId, "Proyecto " + tempId, "Proyecto creado por un bot", cantidad+""});
						ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT id_miembro FROM grupomiembro WHERE id_grupo = " + randomId);
						while(rs.next()) {
							int tempId2 = getLastIdMember();
							
							GeneralSQLFunctions.insertEntryIntoDatabase(connection, "proyectomiembro", new String[] {"id", "id_proyecto", "id_miembro"}, 
								new String[]{tempId2+"" ,tempId +"", rs.getInt("id_miembro")+""});
						}
					}
						
				} catch (SQLException | CommandBuilderBuildException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha habido un error con el bot. Cerrando el hilo.");
					return;
				}
			}
		});
		hiloProyecto.run();		
	}

	/**
	 * Metodo que detiene el ProyectBot durante los segundos introducidos
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
	 * Metodo que elimina el ProyectBot
	 */
	@Override
	public void kill() {
		hiloProyecto.interrupt();
	}
	
	/**
	 * Metodo que obtiene de la BD el idProyecto mas alto
	 * @return idProyecto mas alto + 1
	 */
	public int getLastId() {
		int result = 0;
		try {	
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM proyecto ORDER BY id DESC");
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
	 * Metodo que obtiene de la BD el idProyectoMiembro mas alto
	 * @return idProyectoMiembro mas alto + 1
	 */
	public int getLastIdMember() {
		int result = 0;
		try {	
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM proyectomiembro ORDER BY id DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("id"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result + 1;
	}

}
