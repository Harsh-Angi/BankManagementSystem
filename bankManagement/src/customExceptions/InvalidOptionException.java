package customExceptions;

public class InvalidOptionException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Please Choose a Correct Option";
	}

}
