package io.github.fatsquirrels.deuzum.Database;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import io.github.fatsquirrels.deuzum.Algorithms.TextFunctions;
import io.github.fatsquirrels.deuzum.Algorithms.Math.APair;

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


enum OrderType{
	DEF, DESC, ASC, RANDOM;

}


public class CommandBuilderF {
	
	// Global
	private StatementType SQLType;
	
	private class Order{
		private boolean isRandom = false;
		private OrderType order;
		
		ArrayList<String> keys;
		
		public Order(OrderType order) {
			this.order = order;
			this.keys = new ArrayList<String>();
		}
		
		public void addKeys(String[] keys) {
			if(this.isRandom) return;
			for(String i : keys) 
				this.keys.add(i);
		}
		
		public String pack() {
			String result = "ORDER BY";
			if(this.isRandom) return result+" RAND()";
			else {
				result = result+" "+String.join(",", this.keys)+" ";
				switch(this.order) {
					case DESC:
						return result+"DESC";
					case ASC:
						return result+"ASC";
					default:
						return result;
				}
			}
		}
	}
	private final APair[] variables = {
			new APair("{TABLE}","getTable"), // Implemented
			new APair("{COLUMNS}","getColumnNames"), // Implemented 
			new APair("{VALUES}","getColumnValues"), // Implemented 
			new APair("{WHERE}","where"), // Implemented
			new APair("{GROUP}","getGroup"),// Implemented
			new APair("{HAVING}","getColumnValues"),
			new APair("{EXPRESSION}","getColumnValues"),
			new APair("{ORDER}","getOrder"), // Implemented
			new APair("{LIMIT}","getColumnValues")};
	
	
	private String table;
	private HashMap<String, String> columns;
	private ArrayList<String> group;
	private WhereAST where;
	private Order order;
	private int limit= -1;
	public CommandBuilderF() {
		initialize();
	}
	public CommandBuilderF(StatementType SQLType) {
		this.setSQLType(SQLType);
		initialize();
	}
	
	public void initialize() {
		this.columns = new HashMap<String, String>();
	}
	
	
	public StatementType getSQLType() {
		return SQLType;
	}
	public CommandBuilderF setSQLType(StatementType sQLType) {
		this.SQLType = sQLType;
		return this;
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
	
	public CommandBuilderF addWhere(WhereAST whereT) {
		this.where = whereT;
		return this;
	}
	public CommandBuilderF setOrder(OrderType order, String[] keys) {
		this.order = new Order(order);
		if(keys.length >= 0 && order!=OrderType.RANDOM)
			this.order.addKeys(keys);
		return this;
	}
	public CommandBuilderF setGroupBy(String[] keys) {
		this.group = new ArrayList<String>();
		for(String i : keys)
			this.group.add(i);
		return this;
	}
	public CommandBuilderF setLimit(int limit) {
		// Implementar error
		if(limit<0) return this;
	}
	
	// String Getters
	public String getTable() {
		return table;
	}
	public String getColumnNames() {
		String[] resultL = new String[columns.size()];
		int count = 0;
		for(Entry<String, String> i : columns.entrySet()) 
			resultL[count++] = i.getKey();
		return String.join(",", resultL);
	}
	public String getColumnValues() {
		String[] resultL = new String[columns.size()];
		int count = 0;
		for(Entry<String, String> i : columns.entrySet()) 
			resultL[count++] = i.getValue();
		return String.join(",", TextFunctions.surroundText(resultL, "'"));
	}
	public String getWhere() {
		return this.where.pack();
	}
	
	public String getOrder() {
		return this.order.pack();
	}
	public String getGroup() {
		return "GROUP BY "+String.join(",", this.group);
	}
	
	
	
	public String pack() {
		String result = this.SQLType.CommandFormat;
		for(APair i : variables) {
			try {
				Method tempMethod = this.getClass().getMethod(String.valueOf(i.getValue()));
				String tempVal = String.valueOf(tempMethod.invoke(this));
				if(tempVal.isEmpty()) continue;
				result = result.replace(String.valueOf(i.getIndex()), tempVal);
			}catch(Exception e) {
				System.err.println("Hubo algun tipo de error al conseguir los resultados");
			}
		}
		
		return result;
		
	}
	
}




