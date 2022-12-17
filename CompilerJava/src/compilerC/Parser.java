package compilerC;
import java.util.HashMap;

public class Parser {
	static Node P;
	static Node assignment,in,b,digit,equal,id,w,iff,endiff,endw,integer,ssymbol,then,o,i,endFile;
	
	HashMap<Integer, Node> parents = new HashMap<Integer, Node>();
	HashMap<Integer, Node> DFT = new HashMap<Integer, Node>();
	
	static class Node{
	    int key;
	    String token;
	    Node root,left,right;
	    
	    public Node(int k, String t) {
	    	key = k;
	    	token = t;
	    }

	}
	
	int value = 0;
	int count = 0;
	int order = 0;
	
	private Scanner scanner;
	private Token token;	
	boolean check = true;
	boolean output = false;
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}	
	public void parse() {
		token = scanner.nextToken();
		S();	
	}	
	void S() {
		if(token.getTokenType().equals(TokenType.END_OF_FILE)) {
			return;			
		}		
			S1();
	}	
	void S1() {
		P = new Node(0, "P");
		if(token.text.equals("Endoffile")){
			endFile = new Node(value, token.getText());
			parents.put(value, endFile);
			value = value+1;
			
			DFT.put(order, endFile);
	        order = order+1;
            
	        endw.left = endFile;
	        endiff.right = endFile;
	        
	        eval();
				output = true;
				if(output) {
					//System.out.println("Program is CORRECT");
					
				}
		}
		else if (token.text.equals("Endif")) {
			endiff = new Node(value, token.getText());
			parents.put(value, endiff);
	        value = value+1;
	        
	        DFT.put(order, endiff);
	        order = order+1;
	        
			then.right = endiff;
			
			token = scanner.nextToken();
			E();
		}		
		else if(token.text.equals("out")) {
			o = new Node(value, token.getText());
			DFT.put(order, o);
	        order = order+1;
	        
			then.left = o;
			
			Exp();
			
			then.left.left = id;
			
				if(token.getTokenType().equals(TokenType.SEMI_COLON)) {
					
					token = scanner.nextToken();
					S();
					
				}
				else {
					try {
						throw new InvalidSyntaxException();
					} catch(InvalidSyntaxException e1){
						System.out.println(e1.getMessage());						
					}
				}
			}
		else if(token.text.equals("in")){
			in = new Node(value, token.getText());
			DFT.put(order, in);
	        order = order+1;
	        
			if(token.getTokenType().equals(TokenType.IDENTIFIER)) {
				//System.out.println(token.text+" : "+token.getTokenType());
				token = scanner.nextToken();				
				if(token.getTokenType().equals(TokenType.SEMI_COLON)) {
					//System.out.println(token.text+" : "+token.getTokenType());
					token = scanner.nextToken();
					S();
					
				}
				else {
					try {
						throw new InvalidSyntaxException();
					} catch(InvalidSyntaxException e1){
						System.out.println(e1.getMessage());						
					}
				}
			}
			else {
				try {
					throw new InvalidTypeException();
				} catch(InvalidTypeException e2){
					System.out.println(e2.getMessage());					
				}
			}
		}
		else if(token.getTokenType().equals(TokenType.BEGÝN)) {			
			b = new Node(value, token.getText());
			DFT.put(order, b);
	        order = order+1;
	        
            w.right = b;
            b.left = equal;
			b.left.left = id;
			b.left.right = assignment;
			b.left.right.left = id;
			
			b.right = o;
			
            
			token = scanner.nextToken();			
			S();
		}				
		else if(token.text.equals("Endwhile")){
			endw = new Node(value, token.getText());
			parents.put(value, endw);
	        value = value+1;
	        
	        DFT.put(order, endw);
	        order = order+1;
	        
	        o.right = endw;
	        
	        eval();
	        
			token = scanner.nextToken();	
			E();
		}
		else if(token.getTokenType().equals(TokenType.ÝF)) {
			iff = new Node(value, token.getText());
			parents.put(value, iff);
	        value = value+1;
			
	        P.right = iff;
	        
	        DFT.put(order, iff);
	        order = order+1;
            
			token = scanner.nextToken();			
			if(token.getTokenType().equals(TokenType.LEFT_PAR)) {
				Boolean();
				
				iff.left = ssymbol;
	            
	            ssymbol.left = id;
				
				ssymbol.right = integer;
				
				
				
				if(token.getTokenType().equals(TokenType.RIGHT_PAR)) {
					
					token = scanner.nextToken();					
					if(token.getTokenType().equals(TokenType.THEN)) {
						then = new Node(value, token.getText());
			            
						DFT.put(order, then);
				        order = order+1;
				        
						iff.right = then;
						
						
						token = scanner.nextToken();
						S();
						
						
					}else {
						try {
							throw new InvalidWordException();
						} catch(InvalidWordException e4){
							System.out.println(e4.getMessage());							
						}						
					}
				}
				else {
					try {
						throw new InvalidCharException();
					} catch(InvalidCharException e3){
						System.out.println(e3.getMessage());						
					}
				}
			}
			else {
				try {
					throw new InvalidCharException();
				} catch(InvalidCharException e3){
					System.out.println(e3.getMessage());					
				}
			}			
		}
		else if(token.getTokenType().equals(TokenType.WHÝLE)) {
			w = new Node(value, token.getText());
            parents.put(value, w);
            value = value + 1;
            
            DFT.put(order, w);
	        order = order+1;
            
			token = scanner.nextToken();			
			if(token.getTokenType().equals(TokenType.LEFT_PAR)) {
				//System.out.println(token.text+" : "+token.getTokenType());				
				Boolean();
				
				endiff.left = w;
				w.left = ssymbol;
	            w.left.left = id;
				w.left.right = integer;
				
				
				if(token.getTokenType().equals(TokenType.RIGHT_PAR)) {
					//System.out.println(token.text+" : "+token.getTokenType());
					token = scanner.nextToken();					
					S();
					
					
				}
				else {
					try {
						throw new InvalidCharException();
					} catch(InvalidCharException e3){
						System.out.println(e3.getMessage());						
					}
				}
			}
			else {
				try {
					throw new InvalidCharException();
				} catch(InvalidCharException e3){
					System.out.println(e3.getMessage());					
				}
			}
		}
		else if(token.getTokenType().equals(TokenType.IDENTIFIER)) {
			id = new Node(value, token.getText());
            
			DFT.put(order, id);
	        order = order+1;
			
			token = scanner.nextToken();
			
			if(token.getTokenType().equals(TokenType.EQUAL)) {
				equal = new Node(value, token.getText());
				parents.put(value, equal);
				value = value+1;
	            
				P.left = equal;
				
				DFT.put(order, equal);
		        order = order+1;
		        
		        
		        
	            equal.left = id;
	            
	            
				Exp();
				
				eval();
				
				if(token.getTokenType().equals(TokenType.SEMI_COLON)) {
					token = scanner.nextToken();
					E();
					
				}
				else {
					try {
						throw new InvalidSpecialSymbolException();
					}catch (InvalidSpecialSymbolException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}				
		}		
	}	
	void E() {
		S1();
	}
	void Exp() {
		token = scanner.nextToken();
		Term();
		Exp_Tail();
	}	
	void Exp_Tail() {
		if(token.getTokenType().equals(TokenType.PLUS) || (token.getTokenType().equals(TokenType.SUBSTRACT))) {
			assignment = new Node(value, token.getText());
            
			DFT.put(order, assignment);
	        order = order+1;
	        
            equal.right = assignment;
            
            
			token = scanner.nextToken();
			Term();
			
			assignment.left = integer;
			
			assignment.right = id;
			
		}			
	}	
	void Term() {
		Factor();
	}	
	void Term_Tail() {
		if(token.getTokenType().equals(TokenType.MULTIPLY) || (token.getTokenType().equals(TokenType.DIVIDE))){
			assignment = new Node(value, token.getText());
			
			DFT.put(order, assignment);
	        order = order+1;
	        
			equal.right = assignment;
            
			token = scanner.nextToken();
			Factor();
			
			assignment.left = integer;
			
			assignment.right = id;
			
			}
	}	
	void Factor() {
		if(token.getTokenType().equals(TokenType.INTEGER)) {
			
			Digit();
			
			token = scanner.nextToken();
			
			Term_Tail();
		}
		else if(token.getTokenType().equals(TokenType.IDENTIFIER)) {
			id = new Node(value, token.getText());
            
			DFT.put(order, id);
	        order = order+1;
	        
			token = scanner.nextToken();
			Term_Tail();
		}
		else if (token.getTokenType().equals(TokenType.LEFT_PAR)){
			Exp();
		}
		else if (token.getTokenType().equals(TokenType.RIGHT_PAR)) {
			Term_Tail();
		}
	}
	void Digit() {
		if((token.text.equals("1"))||(token.text.equals("2"))|| (token.text.equals("3"))
				||(token.text.equals("4"))||(token.text.equals("5"))|| (token.text.equals("6"))
				||(token.text.equals("7"))||(token.text.equals("8"))|| (token.text.equals("9"))||(token.text.equals("0"))) {
			digit = new Node(value, token.getText());
			b.left.right.right = digit;
			
			DFT.put(order, digit);
	        order = order+1;
		}
		else if(token.getTokenType().equals(TokenType.INTEGER)) {
           integer = new Node(value, token.getText());
           
           DFT.put(order, integer);
	       order = order+1;
		}
	}	
	boolean Boolean() {	
		Exp();		
		if(token.getTokenType().equals(TokenType.SPECIAL_SYMBOLS.get(token.text))) {
			ssymbol = new Node(value, token.getText());
			
			DFT.put(order, ssymbol);
	        order = order+1;
	        
		Exp();
		}
		else {
			try {
				throw new InvalidSpecialSymbolException();
			} catch(InvalidSpecialSymbolException e){
				System.out.println(e.getMessage());		
			}
		}		
		return check;
	}
	
	int num=0,x=0;
	int max=0;
	
	public void ord() {
		DFT.get(0).token = equal.left.token;
		System.out.println("\nParse Tree :");
		for(int aa=0;aa<DFT.size();aa++) {
		System.out.println("Node : "+DFT.get(aa).token);
		}
	}
	
	public void eval() {
		
			if(parents.get(count).token.equals("=")) {
					String variable = equal.left.token;
					equal.left.token = "1";
					x = Integer.parseInt(equal.left.token);
					if(equal.right.token.equals("*")) {
						num = Integer.parseInt(equal.right.left.token);
						x = num*x;
					}else if(equal.right.token.equals("+")) {
						num = Integer.parseInt(equal.right.left.token);
						x = num+x;
						System.out.println(variable+" + "+num+" = "+x+"\n");
					}
					else if(equal.right.token.equals("/")) {
						num = Integer.parseInt(equal.right.left.token);
						x = num/x;
					}
					else if(equal.right.token.equals("-")) {
						num = Integer.parseInt(equal.right.left.token);
						x = num-x;
					}
					count = count + 1;
			}else if(parents.get(count).token.equals("while")) {
				
				if(w.left.token.equals("==")) {
					if(w.right.token.equals("begin")) {
						if(b.left.token.equals("=")) {
						}
					}
				}else if(w.left.token.equals("<")){
					if(w.right.token.equals("begin")) {
						if(b.left.token.equals("=")) {
							while(x < Integer.parseInt(w.left.right.token)) {
								if(b.left.right.token.equals("*")) {
									x = Integer.parseInt(b.left.right.right.token)*x;
								}else if(equal.right.token.equals("+")) {
									x = Integer.parseInt(b.left.right.right.token)+x;
								}
								else if(equal.right.token.equals("/")) {
									x = Integer.parseInt(b.left.right.right.token)/x;
								}
								else if(equal.right.token.equals("-")) {
									x = Integer.parseInt(b.left.right.right.token)-x;
								}
								
								if(w.right.right.token.equals("out")) {
									System.out.println("x : " + x);
								}
							}
						}
					}
				}
				else if(w.left.token.equals(">")){}
				else if(w.left.token.equals("<=")){}
				else if(w.left.token.equals(">=")){}
				count = count+1;
				
				if(o.right.token.equals("Endwhile")) {
					count = count+1;
					
					if(endw.left.token != null) {
						if(endiff.right.token.equals("Endoffile")) {
							System.out.println("\nProgram terminated.");
						}
					}
				}
				
			}else if(parents.get(count).token.equals("if")) {
				if(iff.left.token.equals("==")) {
					if(x == Integer.parseInt(ssymbol.right.token)) {
						if(iff.right.token.equals("then")) {
							if(then.left.token.equals("out")) {
								System.out.println("x : "+x);
							}
						}
					}
				}else if(ssymbol.token.equals("<")){
					if(x == Integer.parseInt(ssymbol.right.token)) {
						if(iff.right.token.equals("then")) {
							if(then.left.token.equals("out")) {
								System.out.println("x : "+x);
							}
						}
					}
				}else if(ssymbol.token.equals(">")){
					if(x == Integer.parseInt(ssymbol.right.token)) {
						if(iff.right.token.equals("then")) {
							if(then.left.token.equals("out")) {
								System.out.println("x : "+x);
							}
						}
					}
				}
				else if(ssymbol.token.equals("<=")){
					if(x == Integer.parseInt(ssymbol.right.token)) {
						if(iff.right.token.equals("then")) {
							if(then.left.token.equals("out")) {
								System.out.println("x : "+x);
							}
						}
					}
				}
				else if(ssymbol.token.equals(">=")){
					if(x == Integer.parseInt(ssymbol.right.token)) {
						if(iff.right.token.equals("then")) {
							if(then.left.token.equals("out")) {
								System.out.println("x : "+x);
							}
						}
					}
				}
				count = count+1;
			}else if(then.right.token.equals("Endif")) {
				count = count+1;
				
				if(endiff.left.token == null) {
					if(endiff.right.token.equals("Endoffile")) {
						System.out.println("\nProgram terminated.");
					}
				}
			}
		}
	}