package jdbcExceptions;

public class ExistingUserException extends Exception {
	public ExistingUserException(String message) {
		super(message);
	}
}

