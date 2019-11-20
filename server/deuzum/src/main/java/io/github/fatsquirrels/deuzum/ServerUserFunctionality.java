package io.github.fatsquirrels.deuzum;

import java.sql.SQLException;

import org.json.JSONObject;

import java.sql.Connection;


import static io.github.fatsquirrels.deuzum.Algorithms.ArrayFunctions.getReducedArrayString;

import io.github.fatsquirrels.deuzum.Algorithms.Math.APair;
import io.github.fatsquirrels.deuzum.Database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.Database.WhereAST;

/**
 * Funciones comunes para la gestion de usuarios
 *
 */
public class ServerUserFunctionality {

	/**
	 * Genera una conexion el servidor SQL y ordena la informacion en un array para llamar a la funcion createUser
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
		String[] columnNamesUsuarios = {"usuario","contraseña","preg_seguridad","resp_seguridad"};
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
		
		createUserInf(conn, new String[] {data.getString("nombre"), data.getString("apellido"), data.getString("telefono"), 
				data.getString("email"), data.getString("direccion"), data.getString("fecha_nacimiento"), data.getString("sexo")});	
	}
		
	/**
	 * Crea la informacion de un usuario dentro de la base de datos dada en la conexion. Tabla infousuario.
	 * @param connection Conexión de SQL
	 * @param data Array que contiene la información de usuario (Nombre, Apellidos, Telefono, Email, Direccion, F_Nacimiento, Sexo).
	 */	
	public static void createUserInf(Connection connection, String[] data) {
		// TODO esto deberia obtenerse pidiendoselo a la base de datos para hacerlo automatico y que el cambiar la db no afecte
		String[] columnNamesUserInf = {"nombre", "apellido", "telefono", "email", "direccion", "sexo"};
		// Create UserInf
		APair<String[],String[]> reducedInfo = getReducedArrayString(columnNamesUserInf, data);
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "infousuario", reducedInfo.getIndex(), reducedInfo.getValue());
		}catch(SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Actualiza los datos de un usuario elegido por el administrador mediante su ID
	 * @param connection Conexion de SQL
	 * @param userID ID del usuario a modificar
	 * @param data Array que contiene los datos del usuario (Usuario, Contraseña, Pregunta de Seguridad, Respuesta, Permisos)
	 */
	public static void updateUser(Connection connection, String userID, String[] data) {
		String[] columnNamesUsuarios = {"usuario","contrase�a","preg_seguridad","resp_seguridad", "permisos"};
		APair<String[],String[]> reducedInfo = getReducedArrayString(columnNamesUsuarios, data);
		try {
			WhereAST where = new WhereAST().addValue("id='"+userID+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "usuario", reducedInfo.getIndex(), reducedInfo.getValue(), where);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Actualiza la informacion de un usuario elegido por el administrador mediate su ID
	 * @param conn
	 * @param userID
	 * @param data
	 */
	public static void updateUserInf(Connection conn, String userID, String[] data) {
		String[] columnNamesUserInf = {"nombre", "apellidos", "telefono", "email", "direccion", "fecha_nacimiento", "sexo"};
		WhereAST where = new WhereAST().addValue("id='"+userID+"'");
		try {
			GeneralSQLFunctions.updateEntryFromDatabase(conn, "usuario", columnNamesUserInf, data, where);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Devuelve un booleano que indica si se ha introducido una combinacion de usuario/clave correcta
	 */
	public static boolean logUser(JSONObject data) {
		
		String realPass;
		
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzum", "root", "");
		String user = data.getString("user");
		String pass = data.getString("pass");
		try {
			realPass = GeneralSQLFunctions.getEntryFromDatabase(conn, "usuarios",  "pass", 
					new WhereAST().addColumValueLO(
							new String[]{"usuario"}, new String[]{user}, 
							WhereAST.logicOP.AND, WhereAST.ariOP.EQ).pack());
					
		} catch (SQLException e) {
			System.err.println("Ha habido un problema al intentar recopilar la verdadera password");
			return false;
		}
		if(realPass == pass) 
			return true;
		return false;
		
	}
	
	
	
	/**
	 * Realiza una transaccion entre dos usuarios.
	 * @param connection
	 * @param userID_A
	 * @param userID_B
	 * @param value
	 * @return
	 */
	public static String createTransaction(Connection connection, String userID_A, String userID_B, int value) {
		// TODO Hacer las comprobaciones de SQL
		String dinero_A, dinero_B;
		try {
			dinero_A = GeneralSQLFunctions.getEntryFromDatabase(connection, "cuentas", "dinero", " WHERE user_id='"+userID_A+"'");
			dinero_B = GeneralSQLFunctions.getEntryFromDatabase(connection, "cuentas", "dinero", " WHERE user_id='"+userID_B+"'");
			
			if(Integer.valueOf(dinero_A)<value) 
				return "No hay dinero suficinete como para realizar la transacci�n";
			
			int actdinero_A = Integer.valueOf(dinero_A)-value;
			WhereAST whereA = new WhereAST().addValue("user_id='"+userID_A+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuentas", 
					new String[] {"dinero"}, new String[] {Integer.toString(actdinero_A)},whereA);
			int actdinero_B = Integer.valueOf(dinero_B)+value;
			WhereAST whereB = new WhereAST().addValue("user_id='"+userID_B+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuentas", 
					new String[] {"dinero"}, new String[] {Integer.toString(actdinero_B)},whereB);
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
			WhereAST where = new WhereAST().addValue("id='"+userID+"'");
			GeneralSQLFunctions.deleteEntryFromDatabase(connection, "usuario", where);
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
			WhereAST where = new WhereAST().addValue("id='"+userID+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuenta", columns, data, where);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addMoneyToAccount(Connection connection, String account_ID, int money) {
		// TODO Crear esta funcion updateAccountInfo(Connection connection, String[] columns, String[] data)
	}
}
