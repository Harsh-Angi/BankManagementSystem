package customExceptions;

public class InsufficientBalanceException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Account doesn't have sufficient balance to withdraw";
	}
}
