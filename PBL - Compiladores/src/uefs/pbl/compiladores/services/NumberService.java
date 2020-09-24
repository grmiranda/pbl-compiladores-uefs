package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.InvalidNumberToken;
import uefs.pbl.compiladores.model.NumberToken;
import uefs.pbl.compiladores.util.FileManipulator;
import uefs.pbl.compiladores.util.Helpers;

public class NumberService {
	public Token numberMachine (int character, String value) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = value;
		int tokenLine = fileManipulator.getLineCounter();
		int tokenColumn = fileManipulator.getColumnCounter();
		
		LexiconController lexiconController = LexiconController.getInstance();
		lexiconController.setReanalyzeInputFlag(true);
		
		do {
			tokenValue += (char) character;
			character = fileManipulator.getNextChar();
			if(character == 46) { // se for um "."
				int floatPoint = character;
				character = fileManipulator.getNextChar();
				if(Helpers.isDigit(character)) {
					tokenValue += (char) floatPoint;
					tokenValue += (char) character;
					character = fileManipulator.getNextChar();
				} else {
					tokenValue += (char) floatPoint;
					return new InvalidNumberToken(tokenValue, tokenLine, tokenColumn);
				}
			}
		} while (Helpers.isDigit(character));
		
			
		return new NumberToken(tokenValue, tokenLine, tokenColumn);
	}
}
