package io.github.fatsquirrels.deuzum.net;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONObject;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.WhereAST;
import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.utils.DataStructuresFunctions;
import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

public class ServerCommandFunctions {

	@Tested(tested = true)
	/**
	 * Crea un elemento dentro de la base de datos. 
	 * 
	 * La tabla sobre la que se crea debe venir en el JSON en "tableName".
	 * Por otro lado, el resto de los datos del JSON deben ser parejas
	 * "columna":"valor" y debe darse que todas las columnas esten en la tabla
	 * 
	 * @param data 
	 * @return String resultado de la creacion del elemento. "1" en caso de lograrlo.
	 * @throws Exception
	 */
	public static String createElement(JSONObject data) throws Exception{
		
		Connection connection = Server.createConnection();
		if(!ServerUserFunctionalityUtils.containsNeededVals(data, "tableName")) 
			throw new Exception("Faltan tablas para poder ejecutar el codigo");
		tableName tableNameT = tableName.getTableName(data.getString("tableName"));
		
		GeneralSQLFunctions.insertEntryIntoDatabase(connection, tableNameT.getName(), 
			DataStructuresFunctions.JSONtoHashMap(data));
		
		return "1";
	}
	
	@Tested(tested=true)
	/**
	 * Elimina un elemento dentro de la base de datos.
	 * 
	 * La tabla sobre la que se elimina debe venir en el JSON en "tableName".
	 * Asimismo, se debe incluir la id que se desea eliminar.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String deleteElement(JSONObject data) throws Exception{
		Connection connection = Server.createConnection();
		if(!ServerUserFunctionalityUtils.containsNeededVals(data, "tableName","id")) 
			throw new Exception("Faltan tablas para poder ejecutar el codigo");
		
		GeneralSQLFunctions.deleteEntryFromDatabase(connection, data.getString("tableName"), data.getString("id"));
		
		return "1";
	}
	
	@Tested(tested = true)
	/**
	 * Devuelve un booleano que indica si se ha introducido una combinacion de usuario/clave correcta
	 * @param data JSONObject con la informacion de log
	 * @return boolean Cierto si la informacion es correcta
	 */
	public static String logUser(JSONObject data) throws Exception{

		Connection conn = Server.createConnection();
		if(!ServerUserFunctionalityUtils.containsNeededVals(data, "tableName","user")) 
			throw new Exception("Faltan tablas para poder ejecutar el codigo");
		
		
		String realPass = GeneralSQLFunctions.getEntryFromDatabase(conn, data.getString("tableName"),  "pass", 
					new WhereAST().addColumValueLO(
							new String[]{"usuario"}, new String[]{data.getString("user")}, 
							WhereAST.logicOP.AND, WhereAST.ariOP.EQ).packW());
		
		if(realPass == null)  throw new Exception("El usuario no existe");
		if(realPass.equals(data.getString("pass")))  return "1";
		return "0";
		
	}
	
	@Tested(tested = true)
	/**
	 * Aumenta la cantidad de dinero de una cuenta en un valor dado.
	 * 
	 * Los parametros del JSON necesarios son "tableName" con el nombre de la tabla,
	 * "account" con el numero de la cuenta, "ammount" con el dinero a aumentar.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String addMoney(JSONObject data) throws Exception{
		
		Connection connection = Server.createConnection();
		if(!ServerUserFunctionalityUtils.containsNeededVals(data, "tableName","account","amount")) 
			throw new Exception("Faltan tablas para poder ejecutar el codigo");
		
		String actualAccountMoney= GeneralSQLFunctions.getEntryFromDatabase(connection, data.getString("tableName"),
				"dinero", tableName.CUENTA.getID() + "=" + data.getString("account"));
		
		String newAccountMoney = String.valueOf(Integer.valueOf(actualAccountMoney)+Integer.valueOf(Integer.valueOf(data.getString("amount"))));
		
		WhereAST condition =  new WhereAST();
		condition = condition.addColumValueLO(new String[] {tableName.CUENTA.getID()},
				new String[] {data.getString("account")}, WhereAST.logicOP.AND, WhereAST.ariOP.EQ);
		
		GeneralSQLFunctions.updateEntryFromDatabase(connection, tableName.CUENTA.getName(),
				new String[] {"dinero"},new String[] {newAccountMoney}, condition);
		
		return "1";
		
	}
	
	public static String getPreguntaSeguridad(JSONObject data) {
		String usuario = data.getString("user");
		if(usuario == null) return "-1";
		String result = "-1";
		Connection connection = Server.createConnection();
		try {
			result = GeneralSQLFunctions.getEntryFromDatabase(connection, tableName.USUARIO.getName(), "preg_seguridad", tableName.USUARIO.getID() + " = " + usuario);;
			// TODO falta que dada la id nos devuelva el String
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getValidationSecurityQuestion(JSONObject data) throws Exception {
		
		Connection connection = Server.createConnection();
		if(!ServerUserFunctionalityUtils.containsNeededVals(data, "tableName","user","resp")) 
			throw new Exception("Faltan tablas para poder ejecutar el codigo");

		String realResp = GeneralSQLFunctions.getEntryFromDatabase(connection, data.getString("tableName"),
					"resp_seguridad", tableName.USUARIO.getID() + " = " + data.getString("user"));
		
		if(realResp.equals(data.getString("resp")))
			return "1";
		return "0";
	}
	
	public static String makeTransaction(JSONObject data) throws Exception{
		Connection connection = Server.createConnection();
		
		if(!ServerUserFunctionalityUtils.containsNeededVals(data, "tableName","dest","source","value")) 
			throw new Exception("Faltan tablas para poder ejecutar el codigo");
		
		ServerUserFunctionality.createTransaction(connection,
				data.getString("source"), data.getString("dest"), Integer.valueOf(data.getString("value")));
		
		return "1";
	}
	
}
