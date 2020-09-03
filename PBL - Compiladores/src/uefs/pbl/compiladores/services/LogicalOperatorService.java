package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.InvalidOperatorToken;
import uefs.pbl.compiladores.model.LogicalOperatorToken;
import uefs.pbl.compiladores.util.FileManipulator;

public class LogicalOperatorService {
	public Token logicalOperatorMachine (int character) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = "";
		int firstValue = character;
		int tokenLine = fileManipulator.getLineCounter();
		int tokenColumn = fileManipulator.getColumnCounter();
		Token token = null;
		
		tokenValue += (char) character;
		character = fileManipulator.getNextChar();
		
		if(firstValue == character) {
			tokenValue += (char) character;
			token = new LogicalOperatorToken(tokenValue, tokenLine, tokenColumn);
		} else { // se não for igual, configura que é um token mal formado e reanaliza a entrada
			LexiconController lexiconController = LexiconController.getInstance();
			lexiconController.setReanalyzeInputFlag(true);
			token = new InvalidOperatorToken(tokenValue, tokenLine, tokenColumn);
		}
		return token;
	}
}
