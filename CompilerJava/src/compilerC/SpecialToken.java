package compilerC;

public class SpecialToken extends Token {
	SpecialToken(ProgramText source){
		super(source);
		extract();
		
	}
	
	protected void extract() {
		char curChar = curChar();
		text = Character.toString(curChar);
		type = null;
		if(curChar == '+'||curChar =='-'|| curChar == '/'|| curChar =='*'|| curChar ==';'|| curChar == '{'|| curChar == '}'||curChar == '('||curChar ==')')
			nextChar();
		else if(curChar == '='|| curChar == '>'|| curChar == '<') {
			curChar = nextChar();
			if(curChar == '=') {
				text += curChar;
				nextChar();
				}
		}
		type = TokenType.SPECIAL_SYMBOLS.get(text);
	}
}
