package io.github.fatsquirrels.deuzum.visual.Dialogs.general;

public class InvalidTransactionException extends Exception {
	
	private static final long serialVersionUID = 3503293615671568966L;

	public InvalidTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
