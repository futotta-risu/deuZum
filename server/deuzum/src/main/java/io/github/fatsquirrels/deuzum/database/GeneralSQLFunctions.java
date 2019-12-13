package io.github.fatsquirrels.deuzum.database;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
	 * @param pass Contraseña del usuario del SGDB
	 * @return Objecto Connection, que es la conexion a la BD
	 */
	public static Connection connectToDatabase(String direction, String user, String pass) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(direction,user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * Metodo que realiza una actualizacion en la Base de Datos
	 * @param connection Conexion a la BD
	 * @param query Query a realizar en la BD
	 * @throws SQLException Puede lanzar una Exception SQL
	 */
	public static final void execUpdate(Connection connection, String query) throws SQLException {
		System.out.println(query);
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
		
		System.out.println("SELECT * FROM `"+table+"` "+conditions);
		ResultSet result = getExecQuery(connection, "SELECT * FROM `"+table+"` WHERE "+conditions);
		
		if(result.next()) return result.getString(column);
		else return null;
		
	}
	
	/**
	 * Metodo para insertar datos en una tabla
	 * @param connection Conexion a la BD
	 * @param table Tabla en la que se quiere realizar la consulta
	 * @param columnNames Array de Strings que contiene las columnas a modificar
	 * @param values Array de String que contiene los valores a insertar en las columnas
	 * @throws SQLException Puede lanzar una Exception SQL
	 * @see io.github.fatsquirrels.deuzum.database.CommandBuilderF
	 */
	public static final void insertEntryIntoDatabase(Connection connection, String table, String[] columnNames, String[] values) throws SQLException {
		if(columnNames.length != values.length || columnNames.length == 0)
			return;	
		String insertQ = (new CommandBuilderF(StatementType.INSERT).setTable(table).addColumns(columnNames, values)).pack();
		System.out.println(insertQ);
		// TODO check if table exist
		GeneralSQLFunctions.execUpdate(connection, insertQ);
		
	}
	/**
	 * Metodo para actualizar valores en una tabla
	 * @param connection Conexion a la BD
	 * @param table Tabla en la que se quiere realizar la actualizacion
	 * @param columnNames Array de Strings que contiene las columnas a actualizar
	 * @param values Array de String que contiene los valores a actualizar en las columnas
	 * @param where Objeto de WhereAST, es la condicion de la actualizacion
	 * @throws SQLException Puede lanzar una Exception SQL
	 * @see io.github.fatsquirrels.deuzum.database.CommandBuilderF
	 * @see io.github.fatsquirrels.deuzum.database.WhereAST
	 */
	public static final void updateEntryFromDatabase(Connection connection, String table, String[] columnNames, String[] values, WhereAST where) throws SQLException {
		
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		String updateQ = (new CommandBuilderF(StatementType.UPDATE).setTable(table).addExpression(columnNames,values).addWhere(where)).pack();
		GeneralSQLFunctions.execUpdate(connection, updateQ);
	}
	
	public static final void deleteEntryFromDatabase(Connection connection, String table, WhereAST where) throws SQLException {
		String deleteQ = (new CommandBuilderF(StatementType.DELETE).setTable(table).addWhere(where)).pack();
		
		GeneralSQLFunctions.execUpdate(connection, deleteQ);
		
	}
	
	 
	
}
