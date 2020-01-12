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
 * Esta clase permite crear un objeto de AccountBot que introduce en la BD la cantidad 
 * de cuentas introucidas.
 * @see BotBase
 * @see BotFunctions
 */
public class AccountBot extends BotBase implements BotFunctions{
	@SuppressWarnings("unused")
	private String name;
	private long cantidad;
	private Connection connection;
	private Thread hiloCuentas;
	
	/**
	 * Contructor de la clase, crea un AccountBot con los parametros recibidos.
	 * @param name Nombre del bot
	 * @param cantidad Cantidad de cuentas a introducir;
	 */
	public AccountBot(String name, long cantidad){
		this.name = name;
		this.cantidad = cantidad;
		this.connection = Server.getDefaultServerConnection();
	}
	
	/**
	 * Metodo que ejecuta el AccountBot en un hilo.
	 * Se crean la cantidad de cuentas selecionadas asociadas a un usuario aleatorio.
	 */
	@Override
	public void execute() {
		hiloCuentas = new Thread(new Runnable() {

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
					//(int)Math.random()*(users-1)+1);
					int counter = 0;
					while(ids.next()){
						String id = ids.getString("id");
						arrIds[counter] = id;
						counter++;
					}
					for (int i = 0; i < cantidad; i++) {
						int tempId = getLastId();
						Random r = new Random(i);
						String randomId = arrIds[r.nextInt(users)];
						String cantidad = Integer.toString(r.nextInt(999));
					GeneralSQLFunctions.insertEntryIntoDatabase(connection, "cuenta", new String[] {"numero_cuenta","id_usuario", "dinero","tipo_cuenta", "estado"},
					new String[] {tempId+"",randomId, cantidad, "cuenta Bot", 0+""});
					}
						
				} catch (SQLException | CommandBuilderBuildException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha habido un error con el bot. Cerrando el hilo.");
					return;
				} 
			}
		});
		hiloCuentas.run();
	}		
	
	/**
	 * Metodo que detiene el AccountBot durante los segundos introducidos
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
	 * Metodo que elimina el AccountBot
	 */
	@Override
	public void kill() {
		hiloCuentas.interrupt();
	}

	/**
	 * Metodo que obtiene de la BD el numeroCuenta mas alto
	 * @return numeroCuenta mas alto + 1
	 */
	public int getLastId() {
		int result = 0;
		try {
	
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT numero_cuenta FROM cuenta ORDER BY numero_cuenta DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("numero_cuenta"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result +1;
	}
}
