package compilerC;

public class InvalidOperatorException extends Exception{
	public InvalidOperatorException() {
		super("Syntax Error. The program should continue with special symbols (+,-,*,/) ");
	}
	public InvalidOperatorException(String message) {
		super(message);
	}
}