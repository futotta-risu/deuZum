package io.github.fatsquirrels.deuzum.Database;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public final class GeneralSQLFunctions {
	
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
	
	public static final void execUpdate(Connection connection, String query) throws SQLException {
		connection.createStatement().executeUpdate(query);
	}
	
	public static final ResultSet getExecQuery(Connection connection, String query) throws SQLException{
		return connection.createStatement().executeQuery(query);
	}
	
	
	public static final ResultSet getResultSetEntryFromDatabase(Connection connection, String table, String conditions) throws SQLException {
		
		ResultSet result = getExecQuery(connection, "SELECT * FROM `"+table+"` WHERE "+conditions);
		return result;
		
	}

	public static final String getEntryFromDatabase(Connection connection, String table, String column, String conditions) throws SQLException {
		
		System.out.println("SELECT * FROM `"+table+"` "+conditions);
		ResultSet result = getExecQuery(connection, "SELECT * FROM `"+table+"` WHERE "+conditions);
		
		if(result.next()) return result.getString(column);
		else return null;
		
	}
	
	public static final void insertEntryIntoDatabase(Connection connection, String table, String[] columnNames, String[] values) throws SQLException {
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		String insertQ = (new CommandBuilderF(StatementType.INSERT).setTable(table).addColumns(columnNames, values)).pack();
		// TODO check if table exist
		GeneralSQLFunctions.execUpdate(connection, insertQ);
		
	}
	
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
