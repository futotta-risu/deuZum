package io.github.fatsquirrels.deuzum.utils.text;

import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

// Esta clase era util cuando trabajabamos en el codigo con los tipos de texto. 
// Ahora trabajamos haciendo peticiones a la base de datos y ella nos indica si el texto
// es valido o no
@Deprecated
/**
 * Estrucura de Datos que combina un String junto con una lista de formatos posibles.
 * @see #isValid()
 * @see io.github.fatsquirrels.deuzum.utils.text.TextTypes
 */
@Tested(tested = true)
public class ConcreteText {

	private TextTypes type;
	private String value;
	public ConcreteText(String value, TextTypes type) {
		this.type = type;
		this.value = value;
	}
	
	public void setTextType(TextTypes tType) {
		this.type = tType;
	}
	
	public TextTypes getTextType() {
		return this.type;
	}
	
	
	/**
	 * Metodo que comprueba si un objeto de ConcreteText contiene valor acorde a su tipo
	 * @return Devuelve un booleano indicando si la estructura es correcta
	 */
	public boolean isValid() {
		if(this.value.matches(this.type.regexCondition)) return true;
		else return false;
	}
	
	/**
	 * Metodo que comprueba si el String recibido es acorde al tipo de texto indicado
	 * @param toCheck String a comprobar
	 * @param type Tipo de TextTypes
	 * @return Devuelve un booleano indicando si el String recibido cumple el tipo de texto
	 */
	public static boolean isValid(String toCheck, TextTypes type) {
		if(toCheck.matches(type.regexCondition)) return true;
		else return false;
	}
	
	public String toString() {
		return this.value;
	}
	
}
