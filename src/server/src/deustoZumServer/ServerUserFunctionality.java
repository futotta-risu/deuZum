package deustoZumServer;

import java.sql.SQLException;

import java.sql.Connection;

import deustoZumServer.Database.connections;

public class ServerUserFunctionality {

	public static void createUser(Connection connection, String user, String pass, String pSegu, String res ){
		// Create User
		try {
			String add_SQL_usuarios = "INSERT INTO `usuarios` (`user`, `pass`, `pregSeguridad`, `respuesta`)"
					+ " VALUES ('"+user+"','"+pass+"' , '"+pSegu+"', '"+res+"')";
			connections.execQuery(connection, add_SQL_usuarios);
			String new_user_id = connections.getEntryFromDatabase(connection, "usuarios", "id", " WHERE user='"+user+"'");
			// Create Account
			String add_SQL_cuentas = "INSERT INTO `cuentas` (`user_id`,  `dinero`) "
					+ "VALUES ('"+new_user_id+"','"+0+"')";
			connections.execQuery(connection, add_SQL_cuentas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createUser(Connection connection, String[] dataArr) {
		// TODO Pensar en como hacer para leer arrays y que la info se envie correctamente
		/*
		 * 1- Tener en un switch algunas posibles longitudes (4- la trivial, 8- la completa)
		 * 2- Meter siempre 8 datos y que algunos sean nulos
		 */
	}
	
}
