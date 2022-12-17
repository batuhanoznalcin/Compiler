package compilerC;

public class InvalidTypeException extends Exception{
	public InvalidTypeException() {
		super("Type Error. The block should continue with an 'Identifier' ");
	}
	public InvalidTypeException(String message) {
		System.out.println(message);
	}
}
