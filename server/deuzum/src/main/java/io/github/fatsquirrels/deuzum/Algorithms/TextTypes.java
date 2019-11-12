package io.github.fatsquirrels.deuzum.Algorithms;

public enum TextTypes {
	EMAIL("[a-zA-Z0-9._%+-]{6,32}@[a-zA-Z0-9.-]{6,20}\\.[a-zA-Z]{2,10}"),
	NAME("[a-zA-Z]{6,32}"),
	PASSWORD("[a-zA-Z0-9._%+-]{6,32}"),
	PHONE("[0-9]{7,9}");
	
	public String regexCondition;
	
	private TextTypes(String regex) {
		this.regexCondition = regex;
	}
	
	
}
