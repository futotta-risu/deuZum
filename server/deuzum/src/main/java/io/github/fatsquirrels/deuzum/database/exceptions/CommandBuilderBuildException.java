package io.github.fatsquirrels.deuzum.database.exceptions;

public class CommandBuilderBuildException extends Exception{

	private static final long serialVersionUID = 2940301850586683385L;

	public CommandBuilderBuildException(String errorMessage) {
        super(errorMessage);
    }
}
