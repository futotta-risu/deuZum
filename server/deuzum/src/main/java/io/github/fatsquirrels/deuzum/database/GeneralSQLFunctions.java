package io.github.fatsquirrels.deuzum.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import io.github.fatsquirrels.deuzum.database.exceptions.CommandBuilderBuildException;
import io.github.fatsquirrels.deuzum.utils.DataStructuresFunctions;
import io.github.fatsquirrels.deuzum.utils.math.APair;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Clase Funciones Generales SQL
 * Esta clase contiene las funciones para gestionar la conexion a la base de datos
 * @see #connectToDatabase
 * @see #execUpdate
 * @see #getExecQuery
 * @see #getResultSetEntryFromDatabase
 * @see #getEntryFromDatabase
 * @see #insertEntryIntoDatabase
 * @see #updateEntryFromDatabase
 * @see io.github.fatsquirrels.deuzum.database.CommandBuilderF
 */
public final class GeneralSQLFunctions {
	
	/**
	 * Metodo que devuelve una Conexion a la base de datos
	 * @param direction direccion de la base de datos
	 * @param user Nombre de usuario del SGBD
	 * @param pass Contrasenia del usuario del SGDB
	 * @return Objecto Connection, que es la conexion a la BD
	 */
	public static Connection connectToDatabase(String direction, String user, String pass) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(direction,user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static  ArrayList<APair<String,Integer>> getColumnNameType(Connection conn, tableName ttp ){
		
		ArrayList<APair<String,Integer>> result = new  ArrayList<APair<String,Integer>>();
		String getSQLSTMT = "SELECT * FROM "+ ttp.getName()+ " LIMIT 1";
		
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, getSQLSTMT);
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) 
				result.add(new APair<String, Integer>(rsmd.getColumnName(i), rsmd.getColumnType(i)));
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error al crear un dialogo general.");
		}
		return result;
	}
	
	/**
	 * Metodo que realiza una actualizacion en la Base de Datos
	 * @param connection Conexion a la BD
	 * @param query Query a realizar en la BD
	 * @throws SQLException Puede lanzar una Exception SQL
	 */
	public static final void execUpdate(Connection connection, String query) throws SQLException {
		connection.createStatement().executeUpdate(query);
	}
	
	/**
	 * Metodo que realiza una consulta en la Base de Datos
	 * @param connection Conexion a la BD
	 * @param query Query a realizar en la BD
	 * @return Devuelve un ResultSet con el resultado de la consulta
	 * @throws SQLException Puede lanzar una Exception SQL
	 */
	public static final ResultSet getExecQuery(Connection connection, String query) throws SQLException{
		return connection.createStatement().executeQuery(query);
	}
	
	/**
	 * Metodo mas estricto que realiza una consulta en la base de datos
	 * @param connection Conexion a la BD
	 * @param table Tabla en la que se quiere realizar la consulta
	 * @param conditions Condiciones de la consulta
	 * @return Devuelve un ResultSet con el resultado de la consulta
	 * @throws SQLException Puede lanzar una Exception SQL
	 */
	public static final ResultSet getResultSetEntryFromDatabase(Connection connection, String table, String conditions) throws SQLException {
		
		ResultSet result = getExecQuery(connection, "SELECT * FROM '"+table+"' WHERE "+conditions);
		return result;
		
	}

	/**
	 * Metodo que realiza una consulta en la base de datos, solo utilizar cuando el resultado sea unico (Busqueda por ID)
	 * @param connection Conexion a la BD
	 * @param table Tabla en la que se quiere realizar la consulta
	 * @param column Columna en la que se realiza la consulta
	 * @param conditions Condiciones de la consulta
	 * @return String con el resultado
	 * @throws SQLException Puede lanzar una Exception SQL
	 */
	public static final String getEntryFromDatabase(Connection connection, String table, String column, String conditions) throws SQLException {
		System.out.println("SELECT * FROM `"+table+"` WHERE "+conditions);
		ResultSet result = getExecQuery(connection, "SELECT * FROM `"+table+"` WHERE "+conditions);
		
		if(result.next()) {
			System.out.println("El valor es " + result.getString(column));
			return result.getString(column);
		}
		else {
			System.out.println("El valor es null");
			return null;
		}
		
	}
	
	/**
	 * Metodo para insertar datos en una tabla
	 * @param connection Conexion a la BD
	 * @param table Tabla en la que se quiere realizar la consulta
	 * @param columnNames Array de Strings que contiene las columnas a modificar
	 * @param values Array de String que contiene los valores a insertar en las columnas
	 * @throws SQLException Puede lanzar una Exception SQL
	 * @throws CommandBuilderBuildException 
	 * @see io.github.fatsquirrels.deuzum.database.CommandBuilderF
	 */
	public static final void insertEntryIntoDatabase(Connection connection, String table, String[] columnNames, String[] values) throws SQLException, CommandBuilderBuildException {
		if(columnNames.length != values.length || columnNames.length == 0)
			return;	
		APair<String[],String[]> tempArrs = DataStructuresFunctions.getReducedArrayString(columnNames, values);
		String insertQ = (new CommandBuilderF(StatementType.INSERT).setTable(table).addColumns(tempArrs.getIndex(), tempArrs.getValue())).pack();
		
		if(checkIfTableExist(connection, table))
			GeneralSQLFunctions.execUpdate(connection, insertQ);
		
	}
	
	public static final void insertEntryIntoDatabase(Connection connection, String table, HashMap<String,String> data) throws SQLException, CommandBuilderBuildException {
		ArrayList<String> nonNullableColumns = GeneralSQLFunctions.getNonNullableColumns(connection,table);
		for(String name : nonNullableColumns)
			if(!data.containsKey(name)) {
				System.err.println("El valor " + name + " es un valor obligatorio pero no esta contenido en los datos introducidos");
				return;
			}
		
		CommandBuilderF command = new CommandBuilderF();	
		command.setSQLType(StatementType.INSERT).setTable(table);
		for(Entry<String,String> val : data.entrySet())
			command.addColumn(val.getKey(), val.getValue());
		System.out.println(command.pack());
		GeneralSQLFunctions.execUpdate(connection, command.pack());
	}
	/**
	 * Metodo para actualizar valores en una tabla
	 * @param connection Conexion a la BD
	 * @param table Tabla en la que se quiere realizar la actualizacion
	 * @param columnNames Array de Strings que contiene las columnas a actualizar
	 * @param values Array de String que contiene los valores a actualizar en las columnas
	 * @param where Objeto de WhereAST, es la condicion de la actualizacion
	 * @throws SQLException Puede lanzar una Exception SQL
	 * @throws CommandBuilderBuildException 
	 * @see io.github.fatsquirrels.deuzum.database.CommandBuilderF
	 * @see io.github.fatsquirrels.deuzum.database.WhereAST
	 */
	public static final void updateEntryFromDatabase(Connection connection, String table, String[] columnNames, String[] values, WhereAST where) throws SQLException, CommandBuilderBuildException {
		
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		APair<String[],String[]> tempArrs = DataStructuresFunctions.getReducedArrayString(columnNames, values);
		String updateQ = (new CommandBuilderF(StatementType.UPDATE).setTable(table).addExpression(tempArrs.getIndex(),tempArrs.getValue()).addWhere(where)).pack();
		GeneralSQLFunctions.execUpdate(connection, updateQ);
	}
	
	public static final void deleteEntryFromDatabase(Connection connection, String table, WhereAST where) throws SQLException, CommandBuilderBuildException {
		String deleteQ = (new CommandBuilderF(StatementType.DELETE).setTable(table).addWhere(where)).pack();
		
		GeneralSQLFunctions.execUpdate(connection, deleteQ);
		
	}
	
	public static final boolean checkIfTableExist(Connection connection,String name) {
		DatabaseMetaData dbm;
		ResultSet tables = null;
		try {
			dbm = connection.getMetaData();
			tables = dbm.getTables(null, null, name, null);
			if (tables.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Returns the names of all the nonullable collumns
	 * @param connection
	 * @param table
	 * @return
	 */
	public static final ArrayList<String> getNonNullableColumns(Connection connection, String table) {
		ArrayList<String> nonNullableColumnNames = new ArrayList<String>();
		ResultSet tableResultSet;
		try {
			System.out.println(GeneralSQLData.defaultGetLimitStatement.replace("{tableName}",table));
			tableResultSet = GeneralSQLFunctions.getExecQuery(connection, GeneralSQLData.defaultGetLimitStatement.replace("{tableName}",table));
			ResultSetMetaData tableMetadata = tableResultSet.getMetaData();
			
			// Empezamos en 2 para saltar id
			for(int i = 2; i < tableMetadata.getColumnCount()+1; i++) 
				if(tableMetadata.isNullable(i) == 0)
					nonNullableColumnNames.add(tableMetadata.getColumnName(i));
			
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nonNullableColumnNames;
	}
	/**
	 * Returns the names of all the nonullable collumns
	 * @param connection
	 * @param table
	 * @return
	 */
	public static final Set<String> getNonNullableColumnsSet(Connection connection, String table) {
		Set<String> nonNullableColumnNames = new HashSet<String>();
		ResultSet tableResultSet;
		try {
			System.out.println(GeneralSQLData.defaultGetLimitStatement.replace("{tableName}",table));
			tableResultSet = GeneralSQLFunctions.getExecQuery(connection, GeneralSQLData.defaultGetLimitStatement.replace("{tableName}",table));
			ResultSetMetaData tableMetadata = tableResultSet.getMetaData();
			
			// Empezamos en 2 para saltar id
			for(int i = 2; i < tableMetadata.getColumnCount()+1; i++) 
				if(tableMetadata.isNullable(i) == 0)
					nonNullableColumnNames.add(tableMetadata.getColumnName(i));
			
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nonNullableColumnNames;
	}
	
	 
	
}
