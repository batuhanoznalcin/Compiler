package compilerC;

public class IdentifierToken extends Token {
	 IdentifierToken(ProgramText source){
		 super(source);
		 extract();
	 }
	 protected void extract () {
		StringBuffer buffer = new StringBuffer();
		buffer.append(curChar());
		char curChar = nextChar();
		while(Character.isLetterOrDigit(curChar)) {
			buffer.append(curChar);
			curChar = nextChar();
		}
		text =buffer.toString();
		if(TokenType.KEYWORDS.contains(text.toLowerCase()))
			type = TokenType.valueOf(text.toUpperCase());
		else 
			type = TokenType.IDENTIFIER;
	 }
}
