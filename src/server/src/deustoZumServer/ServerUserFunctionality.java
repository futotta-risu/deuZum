package deustoZumServer;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;

import deustoZumServer.Database.GeneralSQLFunctions;

public class ServerUserFunctionality {

	public static void createUser(Connection connection, String[] data){
		String[] columnNamesUsuarios = {"users","pass","pregSeguridad","respuesta"};
		String[] columnNamesCuentas = {"user_id", "dinero"};
		// Create User
		try {
			GeneralSQLFunctions.insetEntryIntoDatabase(connection, "usuarios", columnNamesUsuarios, data);
			String new_user_id = GeneralSQLFunctions.getEntryFromDatabase(connection, "usuarios", "user_id", " WHERE user='"+data[0]+"'");
			// Create Account
			String add_SQL_cuentas = "INSERT INTO `cuentas` (`user_id`,  `dinero`) "
					+ "VALUES ('"+new_user_id+"','"+0+"')";
			GeneralSQLFunctions.insetEntryIntoDatabase(connection, "cuentas", columnNamesCuentas,new String[]{new_user_id, "0"});
			GeneralSQLFunctions.execQuery(connection, add_SQL_cuentas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createUserC(String[] dataArr) {
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		createUser(conn, dataArr);
	}
	
	public static void createTransaction(Connection connection, String userID_A, String userID_B, int value, Date fecha) {
		String dinero_A;
		try {
			dinero_A = GeneralSQLFunctions.getEntryFromDatabase(connection, "cuentas", "dinero", " WHERE user_id='"+userID_A+"'");
		
			if(Integer.valueOf(dinero_A)<value) {
				// TODO no tiene el dinero suficiente
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	
	}
	
}
