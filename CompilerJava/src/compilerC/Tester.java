package compilerC;

public class Tester {
	public static void main(String[] args) {
	Scanner scanner = new Scanner(new ProgramText());
	Parser parser = new Parser(scanner);
	parser.parse();
	parser.ord();
	}
}