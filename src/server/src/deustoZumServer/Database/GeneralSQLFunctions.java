package deustoZumServer.Database;

import java.sql.SQLException;

import deustoZumServer.Algorithms.TextFunctions;

import java.sql.Connection;
import java.sql.DriverManager;

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
	
	public static void execQuery(Connection connection, String query) throws SQLException {
		java.sql.Statement statement = connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public static final String getEntryFromDatabase(Connection connection, String table, String column, String conditions) throws SQLException {
		
		java.sql.Statement sta = connection.createStatement();
		java.sql.ResultSet result = sta.executeQuery("SELECT * FROM `"+table+"` "+conditions);
		if(result.next()) return result.getString(column);
		else return null;
		
	}
	
	public static final void insetEntryIntoDatabase(Connection connection, String table, String[] columnNames, String[] values) throws SQLException {
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		// TODO check if table exist
		
		String insert_SQL_query = "INSERT INTO '"+table+"' ("+String.join(",", columnNames)+") "
				+ "VALUES ("+String.join(",", values)+")";
		GeneralSQLFunctions.execQuery(connection, insert_SQL_query);
	}
	
	public static final void updateEntryFromDatabase(Connection connection, String table, String[] columnNames, String[] values, String conditions) throws SQLException {
		
		if(columnNames.length != values.length || columnNames.length == 0)
			return;
		
		String update_SQL_query = "UPDATE '"+table+"' SET  ";
		for(int i = 0; i < columnNames.length-1; i++) 
			update_SQL_query+=columnNames[i] + "='" + values[i] + "',";
		update_SQL_query+=columnNames[columnNames.length-1] + "='" + values[columnNames.length-1] + " WHERE " + conditions;
		GeneralSQLFunctions.execQuery(connection, update_SQL_query);
	}
	
	public static final String getWhereEqualsClause(String[] columnNames, String[] data) {
		if(columnNames.length != data.length || columnNames.length==0) return "";
		
		String whereClause = "WHERE ";
		data = TextFunctions.surroundText(data, "'", "'");
		String[] concatenatedText = TextFunctions.concatenateAlternative(columnNames, data, "=");
		return whereClause+String.join(" AND ", concatenatedText);
	}
	 
	
}
