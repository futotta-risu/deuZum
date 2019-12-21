package io.github.fatsquirrels.deuzum.IA.bots;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;

public class TransactionBot extends BotBase{
	
	private String name;
	private Thread hiloTransactions;
	private Connection conn;
	private long cantidad;
	
	
	public TransactionBot(String name, long cantidad){
		this.name = name;
		this.conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
		this.cantidad = cantidad;
	}
	

	@Override
	public void execute() {
		hiloTransactions = new Thread(new Runnable() {

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
					//(int)Math.random()*(users-1)+1);
					int counter = 0;
					while(ids.next()){
						String id = ids.getString("id");
						arrIds[counter] = id;
						counter++;
					}
					for (int i = 0; i < cantidad; i++) {
						Random r = new Random(i);
						String randomId1 = arrIds[r.nextInt(users)];
						String randomId2 = arrIds[r.nextInt(users)];
						String cantidad = Integer.toString(r.nextInt(9999));
					GeneralSQLFunctions.insertEntryIntoDatabase(conn, "transaccion", new String[] {"source", "destino","dinero"},
					new String[] {randomId1,randomId2, cantidad});
					}
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		hiloTransactions.run();
	}

	@Override
	public void stop(long tiempo) {
		try {
			hiloTransactions.sleep(tiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloTransactions.interrupt();
	}
	
	/*public static void main(String[] args) {
		TransactionBot tb1 = new TransactionBot("TransactionBot1", 10000);
		tb1.execute();
	}
	*/
}
