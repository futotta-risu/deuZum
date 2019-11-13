package io.github.fatsquirrels.deuzum.Database;

import io.github.fatsquirrels.deuzum.Algorithms.TextFunctions;

public class CommandBuilder {

	/**
	 * Devuelve una cadena de texto de WHERE donde todos los datos se establecen con iguales
	 * @param columnNames Nombre de las columnas
	 * @param data Datos de las columnas a comprobar
	 */
	public static final String getWhereEqualsClause(String[] columnNames, String[] data) {
		if(columnNames.length != data.length || columnNames.length==0) return "";
		
		String whereClause = "WHERE ";
		data = TextFunctions.surroundText(data, "'");
		String[] concatenatedText = TextFunctions.concatenateAlternative(columnNames, data, "=");
		return whereClause+String.join(" AND ", concatenatedText);
	}
	
	/**
	 * Cambia los valores de ciertas columnas seg�n la condici�n.
	 * @param table Nombre de la tabla
	 * @param columnNames Nombre de las columnas
	 * @param values Nombre de los valores
	 * @param conditions String con la informaci�n de la condici�n
	 */
	public static final String getUpdateQuery(String table, String[] columnNames, String[] values, String conditions) {
		
		
		String update_SQL_query = "UPDATE '"+table+"' SET " +
				String.join(",", TextFunctions.concatenateAlternative(
						columnNames, TextFunctions.surroundText(values, "'") , "=")
						) 
				+" WHERE " + conditions;
		return update_SQL_query;
	}
	
	public static final String getInsertQuery(String table, String[] columnNames, String[] values) {
		String insert_SQL_query = "INSERT INTO "+table+" ("+String.join(",", columnNames)+") "
				+ "VALUES ("+String.join(",", TextFunctions.surroundText(values, "'"))+")";
		
		return insert_SQL_query;
	}
	
	public static final String getSelectQuery(String table, String[] columnNames) {
		String get_SQL_query = "SELECT " + String.join(",", columnNames) + " FROM " + table;
		return get_SQL_query;
	}
	public static final String getSelectQuery(String table, String[] columnNames, String conditions) {
		String get_SQL_query = "SELECT " + String.join(",", columnNames) + " FROM " + table + " WHERE " + conditions;
		return get_SQL_query;
	}
	public static final String getSelectAllQuery(String table) {
		String get_SQL_query = "SELECT * FROM " + table;
		return get_SQL_query;
	}
	public static final String getSelectAllQuery(String table, String conditions) {
		String get_SQL_query = "SELECT * FROM " + table + "WHERE " + conditions;
		return get_SQL_query;
	}
	
	public static final String getDeleteQuery(String table, String conditions) {
		String get_SQL_query = "DELETE FROM " + table + " " + conditions;
		return get_SQL_query;
	}
	
}
