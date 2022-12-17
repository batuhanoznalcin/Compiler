package compilerC;

public class InvalidSpecialSymbolException extends Exception{
	public InvalidSpecialSymbolException() {
		super("Syntax Error. The block should finish with ';' ");
	}
	public InvalidSpecialSymbolException(String message) {
		super(message);
	}
}