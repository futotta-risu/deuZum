package io.github.fatsquirrels.deuzum.net;

import java.sql.SQLException;

import org.json.JSONObject;

import static io.github.fatsquirrels.deuzum.utils.ArrayFunctions.getReducedArrayString;

import java.sql.Connection;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.WhereAST;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

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
	@Tested(tested=true)
	public static void createUser(Connection connection, String[] data){
		String[] columnNamesUsuarios = {"usuario","contraseña","preg_seguridad","resp_seguridad"};
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
	public static void createUserInfC(JSONObject data) {
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
		String[] columnNamesUserInf = {"id","nombre", "apellido", "telefono", "email", "direccion", "sexo"};
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
	 * Genera una conexion el servidor SQL y ordena la informacion en un array para llamar a la funcion updateUser
	 * @param data JSON que contiene la informaci�n de usuario
	 * @see #updateUser
	 */
	public static void updateUserC(JSONObject data) {
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzumdb", "root", "");
		updateUser(conn,data.getString("id") ,new String[] {data.getString("user"), data.getString("pass"), data.getString("pregSegu"),data.getString("resp"),"3"});
	}
	
	/**
	 * Actualiza los datos de un usuario elegido por el administrador mediante su ID
	 * @param connection Conexion de SQL
	 * @param userID ID del usuario a modificar
	 * @param data Array que contiene los datos del usuario (Usuario, Contraseña, Pregunta de Seguridad, Respuesta, Permisos)
	 */
	public static void updateUser(Connection connection, String userID, String[] data) {
		String[] columnNamesUsuarios = {"usuario","contraseña","preg_seguridad","resp_seguridad", "permisos"};
		APair<String[],String[]> reducedInfo = getReducedArrayString(columnNamesUsuarios, data);
		try {
			WhereAST where = new WhereAST().addValue("id='"+userID+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "usuario", reducedInfo.getIndex(), reducedInfo.getValue(), where);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Genera una conexión con el servidor SQL y ordena la informacion en un array para llamar a la funcion updateUserInf.
	 * @param data JSON que contiene informacion detallada de usuario.
	 * @see #updateUserInf
	 */
	public static void updateUserInfC(JSONObject data) {
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzumdb", "root", "");
		
		updateUserInf(conn,data.getString("id"), new String[] {data.getString("nombre"), data.getString("apellido"), data.getString("telefono"), 
				data.getString("email"), data.getString("direccion"), data.getString("fecha_nacimiento"), data.getString("sexo")});	
	}
	
	/**
	 * Actualiza la informacion de un usuario en la Base de datos mediate su ID
	 * @param conn Conexion con el servidor
	 * @param userID ID del usuario que se modifica
	 * @param data Parametros recibidos para la actualizacion
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
	 * @param data JSONObject con la informacion de log
	 * @return boolean Cierto si la informacion es correcta
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
	@Tested(tested=true)
	public static String createTransaction(Connection connection, String userID_A, String userID_B, int value) {
		// TODO Hacer las comprobaciones de SQL
		String dinero_A, dinero_B;
		try {
			dinero_A = GeneralSQLFunctions.getEntryFromDatabase(connection, "cuenta", "dinero", " WHERE id_usuario='"+userID_A+"'");
			dinero_B = GeneralSQLFunctions.getEntryFromDatabase(connection, "cuenta", "dinero", " WHERE id_usuario='"+userID_B+"'");
			
			if(Integer.valueOf(dinero_A)<value) 
				return "No hay dinero suficinete como para realizar la transacci�n";
			
			int actdinero_A = Integer.valueOf(dinero_A)-value;
			WhereAST whereA = new WhereAST().addValue("id_usuario='"+userID_A+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuenta", 
					new String[] {"dinero"}, new String[] {"'"+Integer.toString(actdinero_A)+"'"},whereA);
			int actdinero_B = Integer.valueOf(dinero_B)+value;
			WhereAST whereB = new WhereAST().addValue("id_usuario='"+userID_B+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "cuenta", 
					new String[] {"dinero"}, new String[] {"'"+Integer.toString(actdinero_B)+"'"},whereB);
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
	
	public static void updateGroup(Connection conn, String groupID, String[] data) {
		// TODO anyadir las funciones de verificacion de groupID, groupName
				try {
					WhereAST where = new WhereAST().addValue("id='"+groupID+"'");
					GeneralSQLFunctions.updateEntryFromDatabase(conn, "grupo", new String[] { "nombre" },data, where);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public static void updateGroupMiembros(Connection conn, String groupID, String[] userId, String[] comboPermisos) {
		WhereAST where = new WhereAST().addValue("id='"+ groupID +"'");
		try {
			for (int i = 0; i < userId.length; i++) {
				GeneralSQLFunctions.updateEntryFromDatabase(conn, "grupomiembro", new String[] {"id_grupo","id_miembro", "permisos"}, new String[] {groupID, userId[i], comboPermisos[i]}, where);

			}
		} catch (SQLException e) {
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
	
	public static void deleteGroup(Connection connection, String groupId) {
		//Borramos de la base de datos el grupo que nos ha pedido el cliente que borremos
		try {
			WhereAST where = new WhereAST().addValue("id=" + groupId + "'");
			GeneralSQLFunctions.deleteEntryFromDatabase(connection, "grupo", where);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createProyect(Connection connection, String[] data) {
		try {
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "proyecto",
					new String[] {"id_grupo", "nombre", "descripcion"}, data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteProyect(Connection connection, String proyectID) {
		try {
			WhereAST where = new WhereAST().addValue("id='"+proyectID+"'");
			GeneralSQLFunctions.deleteEntryFromDatabase(connection, "proyecto", where);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateProyect(Connection connection, String proyectID, String[] data) {
		try {
			WhereAST where = new WhereAST().addValue("id='"+proyectID+"'");
			GeneralSQLFunctions.updateEntryFromDatabase(connection, "proyecto", new String[] { "id_grupo", "nombre" },data, where);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createAccount(Connection connection, String[] data) {
		// TODO a�adir las funciones de verificacion de userId, accountName
		try {
			// TODO cambiar esto entero para que meta mediante mapas
			GeneralSQLFunctions.insertEntryIntoDatabase(connection, "cuenta", 
					new String[] {"numero_cuenta", "id_usuario","dinero"},
					data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteAccount(Connection connection, String accountID) {
		try {
			WhereAST where = new WhereAST().addValue("numero_cuenta='"+accountID+"'");
			GeneralSQLFunctions.deleteEntryFromDatabase(connection, "cuenta", where);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateAccount(Connection connection, String accountID, String[] columns, String[] data) {
		// TODO a�adir las funciones de verificacion de userId, accountName
		try {
			WhereAST where = new WhereAST().addValue("id='"+accountID+"'");
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
