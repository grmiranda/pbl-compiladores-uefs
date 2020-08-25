package uefs.pbl.compiladores.util;

public class Helpers {
	public static boolean isLetter (int ascii) {
		return (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122); 
	}
	
	public static boolean isDigit (int ascii) {
		return (ascii >= 48 && ascii <= 57);
	}
	
	public static boolean isDelimiter (int ascii) {
		return 	ascii == 59 ||
				ascii == 44 ||
				ascii == 40 ||
				ascii == 41 ||
				ascii == 91 ||
				ascii == 93 ||
				ascii == 123 ||
				ascii == 125 ||
				ascii == 46;
	}
	
	public static boolean isReservedWord (String word) {
		return word.compareTo("var") == 0 ||
			   word.compareTo("const") == 0 ||
			   word.compareTo("procedure") == 0 ||
			   word.compareTo("function") == 0 ||
			   word.compareTo("if") == 0 ||
			   word.compareTo("else") == 0 ||
			   word.compareTo("for") == 0 ||
			   word.compareTo("read") == 0 ||
			   word.compareTo("write") == 0 ||
			   word.compareTo("int") == 0 ||
			   word.compareTo("float") == 0 ||
			   word.compareTo("boolean") == 0 ||
			   word.compareTo("string") == 0 ||
			   word.compareTo("true") == 0 ||
			   word.compareTo("false") == 0;
				
	}
}
