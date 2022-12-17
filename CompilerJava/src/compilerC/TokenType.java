package compilerC;
import java.util.HashMap;
import java.util.HashSet;

public enum TokenType {
	LEFT_CURLY("{"), RIGHT_CURLY("}"), LEFT_PAR("("), RIGHT_PAR(")"),
	EQUAL("="),EQUAL_EQUAL("=="),NOT_EQUAL("!="), PLUS("+"), SUBSTRACT("-"),
	MULTIPLY("*"), DIVIDE("/"), SEMI_COLON(";"), LESS_THAN("<"),
	LESS_THAN_EQUAL("<="), GREATER_THAN(">"),GREATER_THAN_EQUAL(">="),
	
	WHÝLE, ÝF, BEGÝN, THEN, ELSE, 
	
	INTEGER, END_OF_FILE, IDENTIFIER;
	private String text;
	TokenType(String text){
		this.text = text;
	}
	TokenType(){
		this.text = this.toString().toLowerCase();
	}
	static final int SPECIAL_SYMBOL_BEG_INDEX = LEFT_CURLY.ordinal();
	static final int SPECIAL_SYMBOL_END_INDEX = LESS_THAN.ordinal();
	
	static final int KEYWORD_BEG_INDEX = WHÝLE.ordinal();
	static final int KEYWORD_END_INDEX = ELSE.ordinal();
	
	public static HashSet <String> KEYWORDS = new HashSet <String>();
	static {
		TokenType [] values = TokenType.values();
		for(int i = KEYWORD_BEG_INDEX; i <= KEYWORD_END_INDEX; i++)
			KEYWORDS.add(values[i].text);
	}
	public static HashMap <String,TokenType> SPECIAL_SYMBOLS = new HashMap <String,TokenType>();
	static {
		TokenType [] values2 = TokenType.values();
		for(int i = SPECIAL_SYMBOL_BEG_INDEX; i<=SPECIAL_SYMBOL_END_INDEX;i++)
			SPECIAL_SYMBOLS.put(values2[i].text,values2[i]);
		
 	}
	
}
