package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.IdentifierToken;
import uefs.pbl.compiladores.model.ReservedWordToken;
import uefs.pbl.compiladores.util.FileManipulator;
import uefs.pbl.compiladores.util.Helpers;

public class IdentifierService {
	public Token identifierMachine (int character) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = "";
		int tokenLine = fileManipulator.getLineCounter();
		int tokenColumn = fileManipulator.getColumnCounter();
		Token token = null;
		
		do {
			tokenValue += (char) character;
			character = fileManipulator.getNextChar();
		} while (Helpers.isLetter(character) || Helpers.isDigit(character) || character == 95);
		if(!Helpers.isSpace(character) && character != '\n') {
			LexiconController lexiconController = LexiconController.getInstance();
			lexiconController.setReanalyzeInputFlag(true);
		}
		
		if(Helpers.isReservedWord(tokenValue)) { // Verifica se é uma palavra reservada
			token = new ReservedWordToken(tokenValue, tokenLine, tokenColumn);
		} else { // cria o token de identificador
			token = new IdentifierToken(tokenValue, tokenLine, tokenColumn);
		}
		return token;
	}
}
