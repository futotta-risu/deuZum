package io.github.fatsquirrels.deuzum.IA.bots;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;

public class TransactionBot extends BotBase{
	
	private String name;
	private Thread hiloTransactions;
	private Connection conn;
	
	
	public TransactionBot(String name){
		this.name = name;
		this.conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
	}
	

	@Override
	public void execute() {
		hiloTransactions = new Thread(new Runnable() {

			public void run() {
				int users = 0;
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM usuario");
					ResultSet userCount = GeneralSQLFunctions.getExecQuery(conn, "SELECT count(id) FROM usuario");
					if(userCount.next()) {
					users = userCount.getInt("count(id)");
					
					}
					//TODO ACCEDER AL RESULTSET DE MANERA ALEATORIA
					//(int)Math.random()*(users-1)+1);
					while(ids.next()){
						String idFrom = ids.getString("id");
						String idTo = "";
						if (ids.next()) {
							idTo = ids.getString("id");
							int random = (int) Math.random()*(100-0)+0;
							String StrRandom = Integer.toString(random);
							GeneralSQLFunctions.insertEntryIntoDatabase(conn, "transaccion", new String[] {"source", "destino", "dinero"}, 
									new String[] {idFrom, idTo, StrRandom});
						}
						ids.close();
						//ids = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM usuario");
						
	
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
	
	public static void main(String[] args) {
		TransactionBot tb1 = new TransactionBot("TransactionBot1");
		tb1.execute();
	}
}
