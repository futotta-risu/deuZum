package io.github.fatsquirrels.deuzum.database;

public enum tableName {
	
	USUARIO("usuario","id",2), CUENTA("cuenta","numero_cuenta",3)
	, GRUPO("grupo","id",2),PROYECTO("proyecto","id",3),TRANSACCION("transaccion","codigo",4);
	
	private String name;
	private String id;
	private int vals;
	/**
	 * 
	 * @param name Nombre de la tabla
	 * @param id Nombre de la columna id
	 * @param vals Numero de columnas a escoger
	 */
	tableName(String name, String id, int vals) {
		this.name = name;
		this.id = id;
		this.vals = vals;
	}
	
	public String getName() {
		return this.name;
	}
	public String getID() {
		return this.id;
	}
	public int getVals() {
		return this.vals;
	}

}
