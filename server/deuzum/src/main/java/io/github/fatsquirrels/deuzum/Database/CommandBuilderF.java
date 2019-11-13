package io.github.fatsquirrels.deuzum.Database;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum StatementType{
	// Remember table contains INNER JOIN
	INSERT("INSERT INTO {TABLE} ({COLUMNS}) VALUES ({VALUES})"), 
	SELECT("SELECT {COLUMNS} FROM {TABLE} {WHERE} {GROUP} {HAVING} {ORDER} {LIMIT}"), 
	UPDATE("UPDATE {TABLE} SET {EXPRESSION} {WHERE} {ORDER} {LIMIT}"), 
	DELETE("DELETE FROM {TABLE} {WHERE} {ORDER} {LIMIT}");
	
	public String CommandFormat;
	
	private StatementType(String CommandFormat) {
		this.CommandFormat = CommandFormat;
	}
}

public class CommandBuilderF {
	
	// Global
	private StatementType SQLType;
	
	private String table;
	private HashMap<String, String> columns;
	
	public CommandBuilderF() {
		
	}
	public CommandBuilderF(StatementType SQLType) {
		this.setSQLType(SQLType);
	}
	
	
	public StatementType getSQLType() {
		return SQLType;
	}
	public CommandBuilderF setSQLType(StatementType sQLType) {
		this.SQLType = sQLType;
		return this;
	}
	
	public String getTable() {
		return table;
	}
	public CommandBuilderF setTable(String table) {
		// Check if table is correct (Doesnt have space or whatsoever)
		this.table = table;
		return this;
	}
	
	public CommandBuilderF addInnerJoin(String table) {
		// TODO Check what to do in case of empty table. Maybe tell the constructor.
		if(!this.table.isEmpty()) 
			this.table += " INNER JOIN " + table;
		
		return this;
	}
	
	public CommandBuilderF setColumns(HashMap<String,String> columns) {
		this.columns = columns;
		return this;
	}
	
	public CommandBuilderF addColumns(String[] columns) {
		// TODO do something on error
		for(int i= 0; i < columns.length; i++)
			this.columns.put(columns[i], "NULL");
		return this;
	}
	
	public CommandBuilderF addColumns(String[] columns, String[] values) {
		// TODO do something on error
		if(columns.length!=values.length) return this;
		for(int i= 0; i < columns.length; i++)
			this.columns.put(columns[i], values[i]);
		return this;
	}
	
	public CommandBuilderF addColumns( HashMap<String,String> columns) {
		for(Map.Entry<String, String> entry : columns.entrySet())
			this.columns.put(entry.getKey(), entry.getValue());
		return this;
	}
	
	public CommandBuilderF addColumn(String column) {
		this.columns.put(column, "NULL");
		return this;
	}
	public CommandBuilderF addColumn(String column, String value) {
		this.columns.put(column, value);
		return this;
	}
	
	
	
}


/**
 * CommandBuilderF cmd = new CommandBuilderF(StatementType.INSERT).setTable("Hospital").addInnerJoin("Empleado")
 * 							.addColumn("columna").setValue("columna","drogas")
 */

