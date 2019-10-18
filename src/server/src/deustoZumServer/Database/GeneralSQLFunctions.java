package deustoZumServer.Database;

import java.sql.SQLException;

import deustoZumServer.Algorithms.TextFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class GeneralSQLFunctions {
	
	public static Connection connectToDatabase(String direction, String user, String pass) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
	
	public static void execUpdate(Connection connection, String query) throws SQLException {
		connection.createStatement().executeUpdate(query);
	}
	
	public static ResultSet getExecQuery(Connection connection, String query) throws SQLException{
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
		
		String insert_SQL_query = "INSERT INTO '"+table+"' ("+String.join(",", columnNames)+") "
				+ "VALUES ("+String.join(",", values)+")";
		GeneralSQLFunctions.execUpdate(connection, insert_SQL_query);
	}
	
	public static final void updateEntryFromDatabase(Connection connection, String table, String[] columnNames, String[] values, String conditions) throws SQLException {
		
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		
		String update_SQL_query = "UPDATE '"+table+"' SET " +
				String.join(",", TextFunctions.concatenateAlternative(columnNames, TextFunctions.surroundText(values, "'"),"=")) 
				+" WHERE " + conditions;
		GeneralSQLFunctions.execUpdate(connection, update_SQL_query);
	}
	
	
	 
	
}
