package io.github.fatsquirrels.deuzum.database;

public class GeneralSQLData {

	public final static String defaultGetLimitStatement = "SELECT * FROM {tableName} LIMIT 1";
	
	
	// Esto puede parecer redundante ya que al final nos van a enviar un identificador de la tabla a modificar, pero,
	// si por alguna razon hubiera que cambiar el nombre de las tablas, en vez de cambiar el nombre en todos los sistemas, 
	// Solo habría que cambiar el nombre de la tabla dentro de este enum
	public enum tableName{
		// TODO Terminar de añadir las tablas
		USUARIO("usuario"), CUENTA("cuenta"),GRUPO("grupo"), GRUPOMIEMBRO("grupomiembro"),GRUPOPERMISO("grupopermiso"),
		PROYECTO("proyecto"),PROYECTOTRANSACCION("proyectotransaccion"),CLIENTECATEGORIA("clientecategoria"), 
		SESSIONHANDLER("sessionhandler");
		private String tableName;
		
		private tableName(String tableNameT) {
			this.tableName = tableNameT;
		}
		public String getTableName() {
			return tableName;
		}
	}
	
	public static tableName getTableName(String tableNameT) {
		for (tableName name : tableName.values()) 
		    if(tableNameT.equals(name.getTableName()))
		    	return name;
		return null;
	}
}
