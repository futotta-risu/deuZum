package deustoZumServer;

import java.sql.SQLException;
import java.util.Date;

import org.json.JSONObject;

import java.sql.Connection;

import deustoZumServer.Database.GeneralSQLFunctions;

public class ServerUserFunctionality {

	/**
	 * Esta clase crea un usuario dentro de la base de datos dada en la conexion
	 * @params Connection connection, String[] data = {user, pass, pregSeg, resp}
	 */
	public static void createUser(Connection connection, String[] data){
		String[] columnNamesUsuarios = {"users","pass","pregSeguridad","respuesta"};
		String[] columnNamesCuentas = {"user_id", "dinero"};
		// Create User
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "usuarios", columnNamesUsuarios, data);
			String new_user_id = GeneralSQLFunctions.getEntryFromDatabase(connection, "usuarios", "user_id", " WHERE user='"+data[0]+"'");
			// Create Account
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "cuentas", columnNamesCuentas,new String[]{new_user_id, "0"});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void logUser(JSONObject data) {
		
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		
		
	}
	
	
	public static void createUserC(JSONObject data) {
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		createUser(conn, new String[] {data.getString("user"), data.getString("pass"), data.getString("pregSegu"),data.getString("resp")});
	}
	
	public static void createTransaction(Connection connection, String userID_A, String userID_B, int value, Date fecha) {
		// TODO Crear esta funcion createTransaction y cambiar lo de user por account
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
	
	public static void addToGroup(Connection connection, String userID, String groupID) {
		// TODO Crear esta funcion addToGroup(Connection connection, String userID, String groupID)
	}
	
	public static void createGroup(Connection connection, String groupName) {
		// TODO Crear esta funcion createGroup(Connection connection, String groupName)
	}
	
	public static void createAccount(Connection connection, String userID, String accountName) {
		// TODO Crear esta funcion createAccount(Connection connection, String userID, String accountName)
	}
	public static void updateAccountInfo(Connection connection, String[] columns, String[] data) {
		// TODO Crear esta funcion updateAccountInfo(Connection connection, String[] columns, String[] data)
	}
	
	public static void addMoneyToAccount(Connection connection, String account_ID, int money) {
		// TODO Crear esta funcion updateAccountInfo(Connection connection, String[] columns, String[] data)
	}
}
