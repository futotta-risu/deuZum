package io.github.fatsquirrels.deuzum.database;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.fatsquirrels.deuzum.database.exceptions.CommandBuilderBuildException;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;
import io.github.fatsquirrels.deuzum.utils.text.TextFunctions;



/**
 * Lista de Tipos de ordenes de los comandos de SQL. Actualmente se encuentran las siguientes opciones:
 * <ul><li>DESC</li><li>ASC</li><li>RANDOM</li></ul>
 *
 */
enum OrderType{
	DESC, ASC, RANDOM;

}

/**
 * Generador de expresiones de SQL mediante el metodo de factoria.
 * 
 * @version 1.0.0 
 * @see StatementType
 */
@Tested(tested=true)
public class CommandBuilderF {
	
	private StatementType SQLType;
	
	/**
	 * Estructura que contiene las opciones de Orden de SQL.
	 */
	
	private class Order{
		private boolean isUp = false;
		private boolean isRandom = false;
		private OrderType order;
		
		ArrayList<String> keys;
		
		public Order(OrderType order) {
			this.isUp = true;
			this.order = order;
			this.keys = new ArrayList<String>();
		}
		/**
		 * Añade las claves de orden.
		 * 
		 * @param keys Nombre de las claves
		 */
		public void addKeys(String[] keys) {
			if(this.isRandom) return;
			Arrays.stream(keys).forEach(this.keys::add);

		}
		/**
		 * Devuelve una cadena de texto sobre los valores de Orden
		 */
		public String pack() {
			if(!this.isUp) return "";
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
		
	private final APair<?, ?>[] variables = {
			new APair<String,String>("{TABLE}","getTable"), 
			new APair<String,String>("{COLUMNS}","getColumnNames"), 
			new APair<String,String>("{VALUES}","getColumnValues"),
			new APair<String,String>("{WHERE}","getWhere"), 
			new APair<String,String>("{GROUP}","getGroup"),
			new APair<String,String>("{EXPRESSION}","getExpression"), 
			new APair<String,String>("{ORDER}","getOrder"), 
			new APair<String,String>("{LIMIT}","getLimit")}; 
	
	
	private String table = "";
	private HashMap<String, String> columns;
	private WhereAST where;
	private ArrayList<String> group;
	private String expression = "";
	private Order order = null;
	private int limit = -1;
	
	
	public CommandBuilderF() {
		initialize();
	}
	public CommandBuilderF(StatementType SQLType) {
		this.setSQLType(SQLType);
		initialize();
	}
	
	public void initialize() {
		this.columns = new HashMap<String, String>();
		this.group = new ArrayList<String>();
	}
	
	
	public StatementType getSQLType() {
		return SQLType;
	}
	public CommandBuilderF setSQLType(StatementType SQLTypeT) {
		this.SQLType = SQLTypeT;
		return this;
	}
	
	
	public CommandBuilderF setTable(String table) throws CommandBuilderBuildException {
		if(table.matches("\\s")) 
			throw new CommandBuilderBuildException("El nombre de la tabla es invalido");
		this.table = table;
		return this;
	}
	
	/**
	 * Añade la funcion de INNER JOIN para la union de tablas
	 * @param table Tabla a añadir
	 * @return Devuelve el objeto CommandBuilderF modificado
	 */
	public CommandBuilderF addInnerJoin(String table) throws CommandBuilderBuildException {
		if(!this.table.isEmpty()) 
			this.table += " INNER JOIN " + table;
		else
			throw new CommandBuilderBuildException("No se ha estipulado ningun nombre de tabla");
		return this;
	}
	
	public CommandBuilderF setColumns(HashMap<String,String> columns) {
		this.columns = columns;
		return this;
	}
	

	public CommandBuilderF addColumns(String[] columns) {
		for(int i= 0; i < columns.length; i++)
			this.columns.put(columns[i], "NULL");
		return this;
	}
	
	public CommandBuilderF addColumns(ArrayList<String> columns) {
		for(int i= 0; i < columns.size(); i++)
			this.columns.put(columns.get(i), "NULL");
		return this;
	}
	
	public CommandBuilderF addColumns(String[] columns, String[] values) throws CommandBuilderBuildException {
		if(columns.length!=values.length) 
			throw new CommandBuilderBuildException("La cantidad de columnas y valores es diferente");
		
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
	
	public CommandBuilderF addExpression(String column, String expression) {
		if(!this.expression.isEmpty()) this.expression +=",";
		this.expression += column + "=\"" + expression +"\"";
		return this;
	}
	public CommandBuilderF addExpression(String[] column, String[] expression) throws CommandBuilderBuildException {
		if(column.length != expression.length) 
			throw new CommandBuilderBuildException("La cantidad de columnas y valores es diferente");
		for(int i = 0; i < column.length; i++) {
			if(!this.expression.isEmpty()) this.expression +=",";
			this.expression += column[i] + "=\"" + expression[i] +"\"";
		}
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
		Arrays.stream(keys).forEach(this.group::add);
		return this;
	}
	public CommandBuilderF setLimit(int limit) throws CommandBuilderBuildException{
		if(limit<0) 
			throw new CommandBuilderBuildException("No se puede establecer limites negativos");
		else
			this.limit = limit;
		return this;
	}
	public CommandBuilderF setExpression(String expression) {
		this.expression = expression;
		return this;
	}
	
	
	// String Getters
	public String getTable() {
		return table;
	}
	public String getColumnNames() {
		if(this.columns.size()==0) return "";
		String[] resultL = new String[columns.size()];
		int count = 0;
		for(Entry<String, String> i : columns.entrySet()) 
			resultL[count++] = i.getKey();
		return String.join(",", resultL);
	}
	public String getColumnValues() {
		if(this.columns.size()==0) return "";
		String[] resultL = new String[columns.size()];
		int count = 0;
		for(Entry<String, String> i : columns.entrySet()) 
			resultL[count++] = i.getValue();
		return String.join(",", TextFunctions.surroundText(resultL, "'"));
	}
	public String getWhere() {
		if(this.where == null) return "";
		return this.where.pack();
	}
	
	public String getOrder() {
		if(this.order == null) return "";
		return this.order.pack();
	}
	public String getGroup() {
		if(this.group.size()==0) return "";
		return "GROUP BY "+String.join(",", this.group);
	}
	public String getLimit() {
		if(this.limit==-1) return "";
		return "LIMIT " + this.limit;
	}
	public String getExpression() {
		return this.expression;
	}
	
	public String pack() {
		String result = this.SQLType.CommandFormat;
		for(APair<?, ?> i : variables) {
			try {
				Method tempMethod = this.getClass().getMethod(String.valueOf(i.getValue()));
				String tempVal = String.valueOf(tempMethod.invoke(this));
				result = result.replace(String.valueOf(i.getIndex()), tempVal);
			}catch(Exception e) {
				System.err.println(i.index);
				System.err.println("Hubo algun tipo de error al conseguir los resultados");
			}
		}
		
		return result;
		
	}
	
}




