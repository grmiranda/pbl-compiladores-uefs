package uefs.pbl.compiladores.services;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.interfaces.Token;
import uefs.pbl.compiladores.model.ArithmeticOperatorToken;
import uefs.pbl.compiladores.util.FileManipulator;
import uefs.pbl.compiladores.util.Helpers;

public class ArithmeticOperatorService {
	public Token arithmeticOperatorMachine (int character) throws IOException {
		// Setando o gerenciador de arquivos
		FileManipulator fileManipulator = FileManipulator.getInstance();
		// Variáveis de apoio
		String tokenValue = "";
		int tokenLine = fileManipulator.getLineCounter();
		int tokenColumn = fileManipulator.getColumnCounter();
		Token token = null;
		
		tokenValue += (char) character;
		if(character == 47) {// se for "/" verifica a próxima entrada
			character = fileManipulator.getNextChar();
			if(character == 47) { // se for "/" define como comentário e chama o Helper
				Helpers.lineCommentSkip();
				return null;
			} else {
				LexiconController lexiconController = LexiconController.getInstance();
				lexiconController.setReanalyzeInputFlag(true);
				token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
			}
		} else if(character == 42) { // se for "*"
			token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
		} else if(character == 43) { // se for "+"
			character = fileManipulator.getNextChar();
			if(character == 43) {
				tokenValue += (char) character;
				token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
			} else {
				LexiconController lexiconController = LexiconController.getInstance();
				lexiconController.setReanalyzeInputFlag(true);
				token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
			}
		} else if(character == 45) { // se for "-"
			character = fileManipulator.getNextChar();
			// verifica se o ultimo token salvo foi um numero
			LexiconController lexiconController = LexiconController.getInstance();
			if(lexiconController.lastToken().getTokenCode() == "NRO" && lexiconController.lastToken().getTokenLine() == tokenLine) {
				if(character == 9 || character == 32 || character == -1) { // se for um espaço configura um operador
				} else if (character == 45){ // se for outro "-" configura operador
					tokenValue += (char) character;
				} else { // qualquer outra entrada configura operador e reanaliza a entrada
					lexiconController.setReanalyzeInputFlag(true);
				}
				token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
			} else {
				if(character == 9 || character == 32 || Helpers.isDigit(character)) { // se for um espaço ou dígito entra na maquina de numero
					while(character == 9 || character == 32) { // limpa os espaços subsequentes
						character = fileManipulator.getNextChar();
					}
					if(Helpers.isDigit(character)) { // se o próximo caracter lido for um dígito chama maquina de número
						NumberService numberService = new NumberService();
						token = numberService.numberMachine(character, tokenValue);
					} else {
						lexiconController.setReanalyzeInputFlag(true);
						tokenValue += (char) character;
						token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
					}
				} else if (character == 45){ // se for outro "-" configura operador
					tokenValue += (char) character;
					token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
				} else {
					lexiconController.setReanalyzeInputFlag(true);
					tokenValue += (char) character;
					token = new ArithmeticOperatorToken(tokenValue, tokenLine, tokenColumn);
				}
			}
		}
		return token;
	}
}
