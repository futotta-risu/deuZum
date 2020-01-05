package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;

public class AccountBot extends BotBase{
	private String name;
	private long cantidad;
	private Connection conn;
	private Thread hiloCuentas;
	
	public AccountBot(String name, long cantidad){
		this.name = name;
		this.cantidad = cantidad;
		this.conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
	}
	

	@Override
	public void execute() {
		hiloCuentas = new Thread(new Runnable() {

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
						int tempId = getLastId();
						Random r = new Random(i);
						String randomId = arrIds[r.nextInt(users)];
						String cantidad = Integer.toString(r.nextInt(999));
					GeneralSQLFunctions.insertEntryIntoDatabase(conn, "cuenta", new String[] {"numero_cuenta","id_usuario", "dinero","tipo_cuenta", "estado"},
					new String[] {tempId+"",randomId, cantidad, "cuenta Bot", 0+""});
					}
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		hiloCuentas.run();
	}		
	

	@Override
	public void stop(long tiempo) {
		try {
			hiloCuentas.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloCuentas.interrupt();
	}

	
	public int getLastId() {
		int result = 0;
		try {
	
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT numero_cuenta FROM cuenta ORDER BY numero_cuenta DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("numero_cuenta"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		result = result +1;
		return result;
	}
}
