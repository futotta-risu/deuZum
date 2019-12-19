package io.github.fatsquirrels.deuzum.database;


/**
 * Enumera la lista de comandos posibles dentro de la clase CommandBuilderF.
 * Actualmente se encuentran las siguientes opciones:
 * <ul><li>Insert</li><li>Select</li><li>Update</li><li>Delete</li></ul>
 */
public enum StatementType{
	// Remember table contains INNER JOIN
	INSERT("INSERT INTO {TABLE} ({COLUMNS}) VALUES ({VALUES})"), 
	SELECT("SELECT {COLUMNS} FROM {TABLE} {WHERE} {GROUP} {ORDER} {LIMIT}"), 
	UPDATE("UPDATE {TABLE} SET {EXPRESSION} {WHERE} {ORDER} {LIMIT}"), 
	DELETE("DELETE FROM {TABLE} {WHERE} {ORDER} {LIMIT}");
	
	public String CommandFormat;
	
	private StatementType(String CommandFormat) {
		this.CommandFormat = CommandFormat;
	}
}