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
	
	
}
