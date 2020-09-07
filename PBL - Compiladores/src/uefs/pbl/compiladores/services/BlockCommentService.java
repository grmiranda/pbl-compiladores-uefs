package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.InvalidCommentToken;
import uefs.pbl.compiladores.util.FileManipulator;

public class BlockCommentService {
	public Token blockCommentMachine (String value) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = value;
		int tokenLine = fileManipulator.getLineCounter();
		int tokenColumn = fileManipulator.getColumnCounter();
		boolean endOfComment = false;
		int character = 0;
		
		do {
			character = fileManipulator.getNextChar();
			if(character == -1) { // verifica se é fim de arquivo
				LexiconController lexiconController = LexiconController.getInstance();
				lexiconController.setReanalyzeInputFlag(true);
				return new InvalidCommentToken(tokenValue, tokenLine, tokenColumn);
			}
			tokenValue += character == 10 ? "" : (char) character;
			if(character == 42) { // verifica se é "*"
				character = fileManipulator.getNextChar();
				if(character == -1) { // verifica se é fim de arquivo
					LexiconController lexiconController = LexiconController.getInstance();
					lexiconController.setReanalyzeInputFlag(true);
					return new InvalidCommentToken(tokenValue, tokenLine, tokenColumn);
				}
				tokenValue += (char) character;
				if(character == 47) { // verifica se é "/" caracterizando fim do comentário de bloco
					endOfComment = true;
				}
			}
		} while (!endOfComment);
		return null;
	}
}
