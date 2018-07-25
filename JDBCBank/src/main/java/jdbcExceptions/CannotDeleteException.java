package jdbcExceptions;

public class CannotDeleteException extends Exception{
	public CannotDeleteException(String message) {
		super(message);
	}
}
