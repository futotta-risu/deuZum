package io.github.fatsquirrels.deuzum.database.exceptions;

public class SQLEmptyIsNullableException extends Exception{
	
	private static final long serialVersionUID = -7891876336057786258L;

	public SQLEmptyIsNullableException(String errorMessage) {
        super(errorMessage);
    }
}
