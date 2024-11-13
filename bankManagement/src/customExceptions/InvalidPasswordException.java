package customExceptions;

public class InvalidPasswordException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Password Doesn't Match...Please try again!!";
	}
}
