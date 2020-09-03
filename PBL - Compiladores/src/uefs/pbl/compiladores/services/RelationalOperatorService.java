package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.LogicalOperatorToken;
import uefs.pbl.compiladores.model.RelationalOperatorToken;
import uefs.pbl.compiladores.util.FileManipulator;

public class RelationalOperatorService {
	public Token relationalOperatorMachine (int character) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = "";
		int tokenLine = fileManipulator.getLineCounter();
		int tokenColumn = fileManipulator.getColumnCounter();
		Token token = null;
		
		if(character == 33) { // Se for !
			tokenValue += (char) character;
			character = fileManipulator.getNextChar();
			if(character == 61) { // Se após o ! vier um =, montar != e retornar operador relacional
				tokenValue += (char) character;
				token = new RelationalOperatorToken(tokenValue, tokenLine, tokenColumn);
				return token;
			} else { // Qualquer outra entrada, monta operador lógico e reanaliza a entrada
				LexiconController lexiconController = LexiconController.getInstance();
				lexiconController.setReanalyzeInputFlag(true);
				token = new LogicalOperatorToken(tokenValue, tokenLine, tokenColumn);
				return token;
			}
		} else { // Se for = < >
			tokenValue += (char) character;
			character = fileManipulator.getNextChar();
			if(character == 61) {
				tokenValue += (char) character;
				token = new RelationalOperatorToken(tokenValue, tokenLine, tokenColumn);
				return token;
			} else {
				LexiconController lexiconController = LexiconController.getInstance();
				lexiconController.setReanalyzeInputFlag(true);
				token = new RelationalOperatorToken(tokenValue, tokenLine, tokenColumn);
				return token;
			}
		}
	}
}
