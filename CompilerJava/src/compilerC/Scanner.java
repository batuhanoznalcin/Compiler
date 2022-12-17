package compilerC;

public class Scanner {
	private Token token;
	private ProgramText source;
	public int limit;
	char ch;
	
	Scanner(ProgramText source){
		this.source = source;
		limit = source.progText.length();
	}
	Token nextToken() {
		Token token = null;
		char ch = source.curChar();
		
		while(Character.isWhitespace(ch))
			ch = source.nextChar();
		
		if(ch == ProgramText.EOF) {
			return new EOFToken(null);
		}
		else if (Character.isDigit(ch)) {
			token = new NumberToken(source);
		}
		else if (Character.isLetter(ch)) {
			token = new IdentifierToken(source);	
		}
		else if (TokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(ch)) ) {
			token = new SpecialToken(source);
		}
		return token;
	}
}
