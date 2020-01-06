package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;

public class TransactionBot extends BotBase{
	
	@SuppressWarnings("unused")
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
					ResultSet ids = GeneralSQLFunctions.getExecQuery(conn, "SELECT numero_cuenta FROM cuenta");
					ResultSet accountCount = GeneralSQLFunctions.getExecQuery(conn, "SELECT count(numero_cuenta) FROM cuenta");
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
					GeneralSQLFunctions.insertEntryIntoDatabase(conn, "transaccion", new String[] {"codigo","source", "destino","dinero"},
					new String[] {tempId+"",randomId1,randomId2, cantidad});
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
			Thread.sleep(tiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloTransactions.interrupt();
	}
	
	public int getLastId() {
		int result = 0;
		try {
			Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT codigo FROM transaccion ORDER BY codigo DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("codigo"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		result = result +1;
		return result;
	}
	
	/*public static void main(String[] args) {
		TransactionBot tb1 = new TransactionBot("TransactionBot1", 10000);
		tb1.execute();
	}
	*/
}
