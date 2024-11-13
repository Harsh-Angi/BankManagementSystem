package customExceptions;

public class MatchedPasswordException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Old Password and New Password Cannot Be Same";
	}
}
