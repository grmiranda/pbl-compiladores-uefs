package uefs.pbl.compiladores.controller;

import java.io.IOException;
import java.util.ArrayList;

import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.services.DelimiterService;
import uefs.pbl.compiladores.services.IdentifierService;
import uefs.pbl.compiladores.services.SymbolService;
import uefs.pbl.compiladores.util.FileManipulator;
import uefs.pbl.compiladores.util.Helpers;

public class LexiconController {
	
	private static LexiconController instance; 	// Variável privada de armazenamento da instância
	
	private FileManipulator fileManipulator = null;	
	private boolean reanalyzeInputFlag = false;
	private ArrayList<Token> tokens = new ArrayList<>();
	
	private LexiconController () {}
	
	// Método principal do Singleton	
	public static synchronized LexiconController getInstance() {
		if (instance == null) { instance = new LexiconController(); }
		return instance;
	}
	
	//SETTERS
	public void setReanalyzeInputFlag (boolean value) { this.reanalyzeInputFlag = value; }
	
	public boolean analyze() throws IOException {
		// Setando o gerenciador de arquivos
		this.fileManipulator = FileManipulator.getInstance();
		// Inicializando Services
		IdentifierService indentifierService = new IdentifierService();
		DelimiterService delimiterService = new DelimiterService();
		SymbolService symbolService = new SymbolService();
		
		// LOOP principal do analizador léxico
		int character = -1;
		do {
			// Lendo o caractere do arquivo caso necessário ou usa o ultimo lido
			if(!this.reanalyzeInputFlag) {
				character = fileManipulator.getNextChar();
			} else {
				character = fileManipulator.getAtualChar();
				this.reanalyzeInputFlag = false;
			}
			
			
			if(character == 32) { // Se for espaço
				// Não faz nada			
			} else if (Helpers.isLetter(character)) { // Se for letra
				tokens.add(indentifierService.identifierMachine(character));
			} else if (Helpers.isDelimiter(character)) {
				tokens.add(delimiterService.delimiterMachine(character));
			} else if (!(character < 31)){
				tokens.add(symbolService.symbolMachine(character));
			}
			
		} while(character != -1);
		return true;
	}
	
	public String tokensToString() {
		String tokensString = "";
		String errorTokensString = "";
		for (Token token : this.tokens) {
			if(token.isErrorToken()) {
				errorTokensString += token.toString() + "\n";
			} else {
				tokensString += token.toString() + "\n";
			}
		}
		return tokensString + "\n" + errorTokensString;
	}

}
