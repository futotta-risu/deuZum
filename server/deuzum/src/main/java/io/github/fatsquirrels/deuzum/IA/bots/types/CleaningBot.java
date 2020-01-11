package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotFunctions;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.Server;

/**
 * Esta clase permite crear un objeto de CleaningBot que elimina de la BD los usuarios 
 * que no tienen ninguna transaccion realizada
 * @see BotBase
 * @see BotFunctions
 */
public class CleaningBot extends BotBase implements BotFunctions{
	
	private Connection connection;
	private Thread hiloLimpieza = null;
	
	/**
	 * Constructor sin argumentos
	 */
	public CleaningBot() {}
	
	/**
	 * Contructor de la clase, crea un CleaningBot con los parametros recibidos
	 * @param name Nombre del bot
	 */
	public CleaningBot(String name) {
		this.name = name;
		this.connection = Server.getDefaultServerConnection();
	}
	
	/**
	 * Metodo que ejecuta el CleaningBot en un hilo.
	 * Se revisan todos los usuario y se eliminan aquellos que no tengan ninguna transaccion
	 */
	@Override
	public void execute() {
		hiloLimpieza = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ResultSet rs= null;
				ResultSet rsTransacciones = null;
				try {
					rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT * FROM usuario");	
					while(rs.next()) {
						String id = rs.getString("id");
						ResultSet rs2 = GeneralSQLFunctions.getExecQuery(connection, "SELECT numero_cuenta FROM cuenta WHERE id_usuario = " + id);
						int acumulador = 0;
						while(rs2.next()) {
							String cuentaTemp = rs2.getString("numero_cuenta");
							rsTransacciones = GeneralSQLFunctions.getExecQuery(connection, "SELECT COUNT(source) FROM transaccion WHERE source='" + cuentaTemp + "'");
							if(rsTransacciones.next()) {
								acumulador = acumulador + rsTransacciones.getInt(1);
								rsTransacciones.close();
							}
						}
						if(acumulador == 0) {
							GeneralSQLFunctions.execUpdate(connection, "DELETE FROM usuario WHERE id='" + id + "'");
						}
						rs2.close();	
					}
					JOptionPane.showMessageDialog(null, "Los usuarios se han eliminado correctamente");
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		});
		hiloLimpieza.run();
		
	}

	/**
	 * Metodo que detiene el CleaningBot durante los segundos introducidos
	 */
	@Override
	public void stop(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que elimina el CleaningBot
	 */
	@Override
	public void kill() {
		hiloLimpieza.interrupt();
	}
	
}
