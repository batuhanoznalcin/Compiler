package compilerC;

public class InvalidWordException extends Exception{
	
	public InvalidWordException() {
		super("Syntax Error : If program continuous with 'if', the program should keep going with 'then'; "
				+"If program continuous with 'while', the program should keep going with 'begin'.");
	}
	public InvalidWordException(String message) {
		super(message);
	}
}
