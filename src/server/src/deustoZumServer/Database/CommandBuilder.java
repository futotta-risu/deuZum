package deustoZumServer.Database;

import deustoZumServer.Algorithms.TextFunctions;

public class CommandBuilder {

	public static final String getWhereEqualsClause(String[] columnNames, String[] data) {
		if(columnNames.length != data.length || columnNames.length==0) return "";
		
		String whereClause = "WHERE ";
		data = TextFunctions.surroundText(data, "'", "'");
		String[] concatenatedText = TextFunctions.concatenateAlternative(columnNames, data, "=");
		return whereClause+String.join(" AND ", concatenatedText);
	}
	
	
	public static final String getUpdateQuery(String table, String[] columnNames, String[] values) {
		// TODO Implementar getUpdateQuery(String table, String[] columnNames, String[] values)
		return null;
	}
	public static final String getUpdateQuery(String table, String[] columnNames, String[] values, String conditions) {
		// TODO Implementar getUpdateQuery(String table, String[] columnNames, String[] values, String conditions)
		return null;
	}
	
	public static final String getInsertQuery(String table, String[] columnNames, String[] values) {
		// TODO Implementar getInsertQuery(String table, String[] columnNames, String[] values)
		return null;
	}
	
	public static final String getInsertQuery(String table, String[] columnNames, String[] values, String conditions) {
		// TODO Implementar getInsertQuery(String table, String[] columnNames, String[] values String conditions)
		return null;
	}
	
	public static final String getSelectQuery(String table, String[] columnNames) {
		// TODO Implementar getSelectQuery(String table, String[] columnNames)
		return null;
	}
	public static final String getSelectQuery(String table, String[] columnNames, String conditions) {
		// TODO Implementar getSelectQuery(String table, String[] columnNames, String conditions)
		return null;
	}
	public static final String getSelectAllQuery(String table) {
		// TODO Implementar getSelectAllQuery(String table)
		return null;
	}
	public static final String getSelectAllQuery(String table, String conditions) {
		// TODO Implementar getSelectAllQuery(String table, String conditions)
		return null;
	}
	
	public static final String getDeleteQuery(String table, String conditions) {
		// TODO Implementar getDeleteQuery(String table, String conditions)
		return null;
	}
	
}
