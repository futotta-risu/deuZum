package io.github.fatsquirrels.deuzum.Algorithms;

import io.github.fatsquirrels.deuzum.Annotations.Tested;

@Tested(tested = true)
/**
 * Estrucura de Datos que combina un String junto con una lista de formatos posibles.
 * 
 * @see #isValid()
 * @see io.github.fatsquirrels.deuzum.Algorithms.TextTypes
 */
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
	
	public boolean isValid() {
		if(this.value.matches(this.type.regexCondition)) return true;
		else return false;
	}
	
	public static boolean isValid(String toCheck, TextTypes type) {
		if(toCheck.matches(type.regexCondition)) return true;
		else return false;
	}
	
	public String toString() {
		return this.value;
	}
	
}
