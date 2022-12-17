package compilerC;

public class InvalidSyntaxException extends Exception{
	public InvalidSyntaxException() {
		super("Syntax Error. Invalid Symbol");
	}
	public InvalidSyntaxException(String message) {
		super(message);
	}
}