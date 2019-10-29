package deustoZumServer;

import java.sql.SQLException;
import java.util.Date;

import org.json.JSONObject;

import java.sql.Connection;

import deustoZumServer.Database.CommandBuilder;
import deustoZumServer.Database.GeneralSQLFunctions;

public class ServerUserFunctionality {

	/**
	 * Genera una conexi�n con el servidor SQL y ordena la informaci�n en un array para llamar a la funcion createUser
	 * @param data JSON que contiene la informaci�n de usuario
	 * @see {@link #createUser(Connection connection, String[] data)}
	 */
	public static void createUserC(JSONObject data) {
		// TODO hacer que acceda a la base de datos segun el nobre de la base de datos
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzumdb", "root", "");
		createUser(conn, new String[] {data.getString("user"), data.getString("pass"), data.getString("pregSegu"),data.getString("resp"),"3"});
	}
	/**
	 * Crea un usuario dentro de la base de datos dada en la conexion. Tabla usuario.
	 * @param connection Conexi�n de SQL
	 * @param data Array que contiene la informaci�n de creacion del usuario (User, Pass, Pregunta Seguridad, Respuesta)
	 */
	public static void createUser(Connection connection, String[] data){
		String[] columnNamesUsuarios = {"usuario","contrase�a","preg_seguridad","resp_seguridad", "permisos"};
		// Create User
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "usuario", columnNamesUsuarios, data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Genera una conexión con el servidor SQL y ordena la informacion en un array para llamar a la funcion createUserInf.
	 * @param data JSON que contiene informacion detallada de usuario.
	 * @see {@link #createUserInf(Connection connection, String[] data)}
	 */
	public static void crerateUserInfC(JSONObject data) {
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzumdb", "root", "");
		createUserInf(conn, new String[] {data.getString("nombre"), data.getString("apellidos"), data.getString("telefono"), 
				data.getString("email"), data.getString("direccion"), data.getString("fecha_nacimiento"), data.getString("sexo")});	
	}
		
	/**
	 * Crea la informacion de un usuario dentro de la base de datos dada en la conexion. Tabla infousuario.
	 * @param connection Conexión de SQL
	 * @param data Array que contiene la información de usuario (Nombre, Apellidos, Telefono, Email, Direccion, F_Nacimiento, Sexo).
	 */	
	public static void createUserInf(Connection connection, String[] data) {
		String[] columnNamesUserInf = {"nombre", "apellidos", "telefono", "email", "direccion", "fecha_nacimiento", "sexo"};
		// Create UserInf
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "infousuario", columnNamesUserInf, data);
		}catch(SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean logUser(JSONObject data) {
		
		String realPass;
		
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		String user = data.getString("user");
		String pass = data.getString("pass");
		try {
			realPass = GeneralSQLFunctions.getEntryFromDatabase(conn, "usuarios",  "pass", 
					CommandBuilder.getWhereEqualsClause(new String[] {"user"}, new String[] {user}));
		} catch (SQLException e) {
			System.err.println("Ha habido un problema al intentar recopilar la verdadera password");
			return false;
		}
		if(realPass == pass) 
			return true;
		return false;
		
	}
	
	
	
	
	public static String createTransaction(Connection connection, String userID_A, String userID_B, int value) {
		// TODO Hacer las comprobaciones de SQL
		String dinero_A, dinero_B;
		try {
			dinero_A = GeneralSQLFunctions.getEntryFromDatabase(connection, "cuentas", "dinero", " WHERE user_id='"+userID_A+"'");
			dinero_B = GeneralSQLFunctions.getEntryFromDatabase(connection, "cuentas", "dinero", " WHERE user_id='"+userID_B+"'");
			
			if(Integer.valueOf(dinero_A)<value) 
				return "No hay dinero suficinete como para realizar la transacci�n";
			
			int actdinero_A = Integer.valueOf(dinero_A)-value;
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuentas", 
					new String[] {"dinero"}, new String[] {Integer.toString(actdinero_A)},
					" WHERE user_id='"+userID_A+"'");
			int actdinero_B = Integer.valueOf(dinero_B)+value;
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuentas", 
					new String[] {"dinero"}, new String[] {Integer.toString(actdinero_B)},
					" WHERE user_id='"+userID_B+"'");
			String[] columns = {"source","destino","dinero"};
			
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "transaccion", columns, new String[]{userID_A, userID_B,String.valueOf(value)});
		} catch (SQLException e) {
			
			e.printStackTrace();
			return "Fallo en el SQL";
		}
		return "Operacion realizada con exito";
	}
	
	public static void deleteUser(Connection connection, String userID) {
		try {
			GeneralSQLFunctions.deleteEntryFromDatabase(connection, "usuario", " WHERE id='" + userID + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addToGroup(Connection connection, String userID, String groupID, String permisos) {
		// TODO a�adir las funciones de verificacion de groupName
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "grupomiembros", new String[] {"id_grupo","miembro", "permisos"},new String[] {groupID, userID, permisos} );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createGroup(Connection connection, String groupName) {
		// TODO a�adir las funciones de verificacion de groupName
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "grupo", new String[] {"nombre"},new String[] {groupName} );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createAccount(Connection connection, String userID, String accountName) {
		// TODO a�adir las funciones de verificacion de userId, accountName
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "cuentas", new String[] {"id_usuario", "numeroCuenta","permisos"},new String[] {userID, accountName, "3"} );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateAccountInfo(Connection connection, String userID, String[] columns, String[] data) {
		// TODO a�adir las funciones de verificacion de userId, accountName
		try {
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuenta", columns, data, " WHERE user_id='"+userID+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addMoneyToAccount(Connection connection, String account_ID, int money) {
		// TODO Crear esta funcion updateAccountInfo(Connection connection, String[] columns, String[] data)
	}
}
