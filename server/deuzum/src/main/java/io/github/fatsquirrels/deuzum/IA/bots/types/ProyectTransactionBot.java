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
 * Esta clase permite crear un objeto de ProyectTransactionBot que introduce la cantidad recibida
 * de transacciones de proyecto en la BD.
 * @see BotBase
 * @see BotFunctions
 */
public class ProyectTransactionBot extends BotBase implements BotFunctions{
	
	@SuppressWarnings("unused")
	private String name;
	private Thread hiloProyectTransactions;
	private Connection connection;
	private long cantidad;
		
	/**
	 * Contructor de la clase, crea un ProyectTransactionBot con los parametros recibidos
	 * @param namet Nombre del bot
	 * @param cantidad Cantidad de transacciones de proyecto que se quieren introducir en la BD
	 */
	public ProyectTransactionBot(String name, long cantidad){
		this.name = name;
		this.connection = Server.getDefaultServerConnection();
		this.cantidad = cantidad;
	}
	
	/**
	 * Metodo que ejecuta el ProyectTransactionBot en un hilo.
	 * Se elige un proyecto aleatorio en la BD, ademas de un usuario participante del proyecto
	 * y transfiere una aportacion.
	 */
	@Override
	public void execute() {
		hiloProyectTransactions = new Thread(new Runnable() {

			public void run() {
				int proyects = 0;
				String[] arrIdsProyecto = null;
				
				try {
					ResultSet ids = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM proyecto");
					ResultSet proyectCount = GeneralSQLFunctions.getExecQuery(connection, "SELECT count(id) FROM proyecto");
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
							ResultSet idsUsuario = GeneralSQLFunctions.getExecQuery(connection, "SELECT id_miembro FROM proyectomiembro WHERE id_proyecto = " + randomId);
							ResultSet userCount = GeneralSQLFunctions.getExecQuery(connection, "SELECT count(id_miembro) FROM proyectomiembro WHERE id_proyecto = " + randomId);
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
						ResultSet deuda = GeneralSQLFunctions.getExecQuery(connection, "SELECT deuda FROM proyecto WHERE id = " + randomId);
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
							GeneralSQLFunctions.insertEntryIntoDatabase(connection, "proyectotransaccion", new String[] {"id","id_proyecto", "id_miembro","tipo", "cantidad", "razon"},
							new String[] {tempId+"",randomId,idsUser[r.nextInt(users)], 1+"", 0+"", "El proyecto no tiene ninguna deuda"});							
						}else {
							GeneralSQLFunctions.insertEntryIntoDatabase(connection, "proyectotransaccion", new String[] {"id","id_proyecto", "id_miembro","tipo", "cantidad", "razon"},
							new String[] {tempId+"",randomId,idsUser[r.nextInt(users)], 1+"", cantidad+"", "Transaccion realizada por un bot"});	
							//Actualizar deuda
							GeneralSQLFunctions.execUpdate(connection, "UPDATE proyecto SET deuda = "+ (deudaInt-cantidad) + " WHERE id = " + randomId);
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
	
	
	/**
	 * Metodo que detiene el ProyectTransactionBot durante los segundos introducidos
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
	 * Metodo que elimina el ProyectTransactionBot
	 */
	@Override
	public void kill() {
		hiloProyectTransactions.interrupt();
	}
	
	/**
	 * Metodo que obtiene de la BD el codigoProyectoTransaccion mas alto
	 * @return codigoProyectoTransaccion mas alto + 1
	 */
	public int getLastId() {
		int result = 0;
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(connection, "SELECT id FROM proyectotransaccion ORDER BY id DESC");
			if(rs.next()) {
				result = Integer.parseInt(rs.getString("id"));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result +1;
	}
}
