package customExceptions;

public class AccountAlreadyExistException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Account Already Exists";
	}
}
