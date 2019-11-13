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
	
	public static final String getEntryFromDatabase(Connection connection, String table, String column, String conditions) throws SQLException {
		
		ResultSet result = getExecQuery(connection, "SELECT * FROM `"+table+"` "+conditions);
		if(result.next()) return result.getString(column);
		else return null;
		
	}
	
	public static final void insertEntryIntoDatabase(Connection connection, String table, String[] columnNames, String[] values) throws SQLException {
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		// TODO check if table exist
		GeneralSQLFunctions.execUpdate(connection, 
				CommandBuilder.getInsertQuery(table, columnNames, values));
		
	}
	
	public static final void updateEntryFromDatabase(Connection connection, String table, String[] columnNames, String[] values, String conditions) throws SQLException {
		
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		
		GeneralSQLFunctions.execUpdate(connection, 
				CommandBuilder.getUpdateQuery(table, columnNames, values, conditions));
	}
	
	public static final void deleteEntryFromDatabase(Connection connection, String table, String conditions) throws SQLException {
		GeneralSQLFunctions.execUpdate(connection, 
					CommandBuilder.getDeleteQuery(table, conditions));
		
	}
	
	 
	
}
