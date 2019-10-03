package deustoZumServer.Database;

import java.sql.SQLException;

import java.sql.Connection;

public class connections {
	public static void execQuery(Connection connection, String query) throws SQLException {
		java.sql.Statement statement = connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public static String getEntryFromDatabase(Connection connection, String table, String column, String conditions) throws SQLException {
		
		java.sql.Statement sta = connection.createStatement();
		java.sql.ResultSet result = sta.executeQuery("SELECT * FROM `"+table+"` "+conditions);
		if(result.next()) return result.getString(column);
		else return null;
		
	}
	
	public static void insetEntryIntoDatabase(Connection connection, String table, String[] columnNames, String[] values) throws SQLException {
		if(columnNames.length != values.length || columnNames.length == 0 || values.length==0)
			return;
		// TODO check if table exist
		
		String insert_SQL_query = "INSERT INTO '"+table+"' ("+String.join(",", columnNames)+") "
				+ "VALUES ("+String.join(",", values)+")";
		connections.execQuery(connection, insert_SQL_query);
	}
	
	public static void updateEntryFromDatabase(Connection connection, String table, String[] columnNames, String[] values, String condition) {
		
	}
	
}
