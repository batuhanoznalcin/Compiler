package compilerC;

abstract public class Token {
	protected TokenType type;
	protected String text;
	private ProgramText source;
	
	Token(ProgramText source){
		this.source = source;
	}
	public TokenType getTokenType() {
		return type;
	}
	public String getText() {
		return text;
	}
	protected char curChar() {
		return source.curChar();
	}
	protected char nextChar() {
		return source.nextChar();
	}
	protected char peekChar() {
		return source.peekChar();
	}
	abstract protected void extract();
}
