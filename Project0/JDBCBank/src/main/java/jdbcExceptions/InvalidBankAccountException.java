package jdbcExceptions;

public class InvalidBankAccountException extends Exception {
	public InvalidBankAccountException(String message) {
		super(message);
	}
}
