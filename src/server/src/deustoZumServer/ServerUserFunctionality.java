package deustoZumServer;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;

import deustoZumServer.Database.GeneralSQLFunctions;

public class ServerUserFunctionality {

	public static void createUser(Connection connection, String user, String pass, String pSegu, String res ){
		// Create User
		try {
			// TODO cambiar esto por la nnueva funcion de connections de insert
			String add_SQL_usuarios = "INSERT INTO `usuarios` (`user`, `pass`, `pregSeguridad`, `respuesta`)"
					+ " VALUES ('"+user+"','"+pass+"' , '"+pSegu+"', '"+res+"')";
			GeneralSQLFunctions.execQuery(connection, add_SQL_usuarios);
			String new_user_id = GeneralSQLFunctions.getEntryFromDatabase(connection, "usuarios", "id", " WHERE user='"+user+"'");
			// Create Account
			String add_SQL_cuentas = "INSERT INTO `cuentas` (`user_id`,  `dinero`) "
					+ "VALUES ('"+new_user_id+"','"+0+"')";
			GeneralSQLFunctions.execQuery(connection, add_SQL_cuentas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createUserC(String[] dataArr) {
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		createUser(conn, dataArr[0], dataArr[1], dataArr[2], dataArr[3]);
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
