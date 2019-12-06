package io.github.fatsquirrels.deuzum.Algorithms;

/**
 * Lista de los formatos de texto disponibles. Actualmente se encuentran impelmentados los siguientes;
 * <ul><li>EMAIL</li><li>NAME</li><li>PASSWORD</li><li>PHONE</li></ul>
 *	@see #regexCondition
 */
public enum TextTypes {
	EMAIL("[a-zA-Z0-9._%+-]{4,32}@[a-zA-Z0-9.-]{4,20}\\.[a-zA-Z]{2,10}"),
	NAME("[a-zA-Z]{3,32}"),
	PASSWORD("[a-zA-Z0-9._%+-]{6,32}"),
	PHONE("[0-9]{0,9}"),
	USER("[a-zA-Z0-9]{4,32}"), 
	
	ACCOUNT("[0-9]{0,11}"),
	ID("[0-9]{0,11}"),
	MONEY("[0-9]{0,11}"),
	ACCOUNT_TYPE("[a-zA-Z0-9._]{0,15}"),
	DESCRIPTION("[a-zA-Z0-9._]{0,256}"),
	STATUS("[0-9]{0,11}"),
	CATEGORY("[a-zA-Z0-9._]{0,20}");
	
	/**
	 * Contiene el formato del tipo.
	 */
	public String regexCondition;
	
	private TextTypes(String regex) {
		this.regexCondition = regex;
	}
	
	
}
