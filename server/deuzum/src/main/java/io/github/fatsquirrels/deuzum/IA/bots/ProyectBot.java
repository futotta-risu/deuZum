package io.github.fatsquirrels.deuzum.IA.bots;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.Server;

public class ProyectBot extends BotBase{
	private String name;
	private Connection conn;
	private Thread hiloProyecto;
	private long cantidad;
	
	
	public ProyectBot(String name, long cantidad) {
		this.name = name;
		this.conn = Server.createConnection();
		this.cantidad = cantidad;
	}
	
	
	@Override
	public void execute() {
		hiloProyecto = new Thread(new Runnable() {

			public void run() {
				int groups = 0;
				String[] arrIds = null;
				
				
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM grupo");
					ResultSet groupCount = GeneralSQLFunctions.getExecQuery(conn, "SELECT count(id) FROM grupo");
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
					GeneralSQLFunctions.insertEntryIntoDatabase(conn, "proyecto", new String[] {"id","id_grupo", "nombre","descripcion", "id_deuda"},
					new String[] {tempId+"",randomId, "Proyecto " + tempId, "Proyecto creado por un bot", cantidad+""});
					}
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		hiloProyecto.run();		
	}

	@Override
	public void stop(long tiempo) {
		try {
			hiloProyecto.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloProyecto.interrupt();
	}
	
	public int getLastId() {
		int result = 0;
		try {
	
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM proyecto ORDER BY id DESC");
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

}
