package customExceptions;

public class AccountNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Incorrect Account Number, Please Check Your Account Number or Create a New Account";
	}
}
