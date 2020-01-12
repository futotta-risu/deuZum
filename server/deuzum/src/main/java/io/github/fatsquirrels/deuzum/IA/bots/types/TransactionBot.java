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
 * Esta clase permite crear un objeto de TransactionBot que introduce la cantidad recibida
 * de transacciones en la BD.
 * @see BotBase
 * @see BotFunctions
 */
public class TransactionBot extends BotBase implements BotFunctions{
	
	@SuppressWarnings("unused")
	private String name;
	private Thread hiloTransactions;
	private Connection connection;
	private Integer cantidad;
	
	/**
	 * Contructor de la clase, crea un TransactionBot con los parametros recibidos
	 * @param namet Nombre del bot
	 * @param cantidad Cantidad de transacciones que se quieren introducir en la BD
	 */
	public TransactionBot(String name, Integer cantidad){
		this.name = name;
		this.connection = Server.getDefaultServerConnection();
		this.cantidad = cantidad;
	}
	
	/**
	 * Metodo que ejecuta el TransactionBot en un hilo.
	 * Se eligen dos usuarios aleatorios en la BD, ademas de una cuenta existente de uno de los usuarios
	 * elegidos y se transfiere una cantidad aleatoria
	 */
	@Override
	public void execute() {
		hiloTransactions = new Thread(new Runnable() {

			public void run() {
				int users = 0;
				String[] arrIds = null;
				
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(connection, "SELECT numero_cuenta FROM cuenta");
					ResultSet accountCount = GeneralSQLFunctions.getExecQuery(connection, "SELECT count(numero_cuenta) FROM cuenta");
					if(accountCount.next()) {
						users = accountCount.getInt("count(numero_cuenta)");
						arrIds = new String[users];
						accountCount.close();
					}
					//(int)Math.random()*(users-1)+1);
					int counter = 0;
					while(ids.next()){
						String id = ids.getString("numero_cuenta");
						arrIds[counter] = id;
						counter++;
					}
					for (int i = 0; i < cantidad; i++) {
						int tempId = getLastId();
						Random r = new Random(i);
						String randomId1 = arrIds[r.nextInt(users)];
						String randomId2 = arrIds[r.nextInt(users)];
						String cantidad = Integer.toString(r.nextInt(50));
					GeneralSQLFunctions.insertEntryIntoDatabase(connection, "transaccion", new String[] {"codigo","source", "destino","dinero"},
					new String[] {tempId+"",randomId1,randomId2, cantidad});
					}
						
				} catch (SQLException | CommandBuilderBuildException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha habido un error con el bot. Cerrando el hilo.");
					return;
				}
			}
		});
		hiloTransactions.run();
	}

	/**
	 * Metodo que detiene el TransactionBot durante los segundos introducidos
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
	 * Metodo que elimina el TransactionBot
	 */
	@Override
	public void kill() {
		hiloTransactions.interrupt();
	}
	
	/**
	 * Metodo que obtiene de la BD el codigoTransaccion mas alto
	 * @return codigoTransaccion mas alto + 1
	 */
	public int getLastId() {
		int result = 0;
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT codigo FROM transaccion ORDER BY codigo DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("codigo"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result +1;
	}
	
	
}
