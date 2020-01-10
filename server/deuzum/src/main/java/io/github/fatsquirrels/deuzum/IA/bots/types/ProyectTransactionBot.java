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

public class ProyectTransactionBot extends BotBase implements BotFunctions{
	
	@SuppressWarnings("unused")
	private String name;
	private Thread hiloProyectTransactions;
	private Connection conn;
	private long cantidad;
	
	
	public ProyectTransactionBot(String name, long cantidad){
		this.name = name;
		this.conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
		this.cantidad = cantidad;
	}
	

	@Override
	public void execute() {
		hiloProyectTransactions = new Thread(new Runnable() {

			public void run() {
				int proyects = 0;
				String[] arrIdsProyecto = null;
				
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM proyecto");
					ResultSet proyectCount = GeneralSQLFunctions.getExecQuery(conn, "SELECT count(id) FROM proyecto");
					if(proyectCount.next()) {
						proyects = proyectCount.getInt("count(id)");
						arrIdsProyecto = new String[proyects];
						proyectCount.close();
					}
					int counter = 0;
					while(ids.next()){
						String id = ids.getString("id");
						arrIdsProyecto[counter] = id;
						counter++;
					}
					ids.close();
					for (int i = 0; i < cantidad; i++) {
						int tempId = getLastId();
						Random r = new Random(i);
						int users = 0;
						String randomId;
						String[] idsUser = null;
						
						do {
							randomId = arrIdsProyecto[r.nextInt(proyects)];
							
							//Obtener el id aleatorio de un participante	
							ResultSet idsUsuario = GeneralSQLFunctions.getExecQuery(conn, "SELECT id_miembro FROM proyectomiembro WHERE id_proyecto = " + randomId);
							ResultSet userCount = GeneralSQLFunctions.getExecQuery(conn, "SELECT count(id_miembro) FROM proyectomiembro WHERE id_proyecto = " + randomId);
							if(userCount.next()) {
								users = userCount.getInt("count(id_miembro)");
								idsUser = new String[users];
								userCount.close();
							}
							int counter2 = 0;
							while(idsUsuario.next()) {
								String id = idsUsuario.getString("id_miembro");
								idsUser[counter2] = id;
								counter2++;
							}
							idsUsuario.close();
							}while(users==0);
						
						//Obtener deuda del proyecto
						ResultSet deuda = GeneralSQLFunctions.getExecQuery(conn, "SELECT deuda FROM proyecto WHERE id = " + randomId);
						int deudaInt = 0;
						if(deuda.next()) {
							deudaInt = deuda.getInt("deuda");
							deuda.close();
						}
						
						int cantidad = r.nextInt(deudaInt+1);
						System.out.println(users);
						System.out.println(cantidad +" " +tempId + " "+ randomId+ " " +idsUser[r.nextInt(users)]);
						
						String memberId = idsUser[r.nextInt(users)];
						while(memberId.equals(null)) {
							memberId = idsUser[r.nextInt(users)];
						}
						if(deudaInt == 0 ) {
							GeneralSQLFunctions.insertEntryIntoDatabase(conn, "proyectotransaccion", new String[] {"id","id_proyecto", "id_miembro","tipo", "cantidad", "razon"},
							new String[] {tempId+"",randomId,idsUser[r.nextInt(users)], 1+"", 0+"", "El proyecto no tiene ninguna deuda"});							
						}else {
							GeneralSQLFunctions.insertEntryIntoDatabase(conn, "proyectotransaccion", new String[] {"id","id_proyecto", "id_miembro","tipo", "cantidad", "razon"},
							new String[] {tempId+"",randomId,idsUser[r.nextInt(users)], 1+"", cantidad+"", "Transaccion realizada por un bot"});	
							//Actualizar deuda
							GeneralSQLFunctions.execUpdate(conn, "UPDATE proyecto SET deuda = "+ (deudaInt-cantidad) + " WHERE id = " + randomId);
						}
					
					}
						
				} catch (SQLException | CommandBuilderBuildException e) {
					JOptionPane.showMessageDialog(null, "Ha habido un error con el bot. Cerrando el hilo.");
					return;
				}
			}
		});
		hiloProyectTransactions.run();
	}

	@Override
	public void stop(long tiempo) {
		try {
			Thread.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloProyectTransactions.interrupt();
	}
	
	public int getLastId() {
		int result = 0;
		try {
			Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM proyectotransaccion ORDER BY id DESC");
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
