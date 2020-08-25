package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.InvalidSymbolToken;
import uefs.pbl.compiladores.util.FileManipulator;

public class SymbolService {
	public Token symbolMachine (int character) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = "";
		tokenValue += (char) character;
		return new InvalidSymbolToken(tokenValue, fileManipulator.getLineCounter(), fileManipulator.getColumnCounter());
	}
}
