package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.InvalidStringToken;
import uefs.pbl.compiladores.model.StringToken;
import uefs.pbl.compiladores.util.FileManipulator;
import uefs.pbl.compiladores.util.Helpers;

public class StringService {
	public Token stringMachine (int character) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = "";
		int tokenLine = fileManipulator.getLineCounter();
		int tokenColumn = fileManipulator.getColumnCounter();
		Token token = null;
		boolean invalidString = false;
		
		do {
			tokenValue += (char) character;
			character = fileManipulator.getNextChar();
			if(character > 126 || (character < 32 && character != 9)) {
				invalidString = true;
			}
			if(character == 92) { // se for \ (barra invertida)
				tokenValue += (char) character;
				character = fileManipulator.getNextChar();
				if(character == -1 ) { // verifica se é fim de arquivo
					LexiconController lexiconController = LexiconController.getInstance();
					lexiconController.setReanalyzeInputFlag(true);
					return new InvalidStringToken(tokenValue, tokenLine, tokenColumn);
				} else if(character == 10) { // verifica se é fim de linha
					return new InvalidStringToken(tokenValue, tokenLine, tokenColumn);
				}
				tokenValue += (char) character;
				character = fileManipulator.getNextChar();
			}
			if(character == -1 ) { // verifica se é fim de arquivo
				LexiconController lexiconController = LexiconController.getInstance();
				lexiconController.setReanalyzeInputFlag(true);
				return new InvalidStringToken(tokenValue, tokenLine, tokenColumn);
			} else if(character == 10) { // verifica se é fim de linha
				return new InvalidStringToken(tokenValue, tokenLine, tokenColumn);
			}
		} while (!Helpers.isDoubleQuotes(character));
		tokenValue += (char) character;
		token = invalidString ? new InvalidStringToken(tokenValue, tokenLine, tokenColumn) : new StringToken(tokenValue, tokenLine, tokenColumn);
		return token;
	}
}
