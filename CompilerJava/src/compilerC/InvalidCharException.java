package compilerC;

public class InvalidCharException extends Exception{
	public InvalidCharException() {
		super("Syntax Error. The block should continue with ('or')");
	}
	public InvalidCharException(String message) {
		super(message);
	}
}