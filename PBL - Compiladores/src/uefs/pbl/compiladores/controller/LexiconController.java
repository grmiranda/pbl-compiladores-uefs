package uefs.pbl.compiladores.controller;

import java.io.IOException;

import uefs.pbl.compiladores.util.FileManipulator;

public class LexiconController {
	
	private FileManipulator fileManipulator = null;	
	
	public boolean mainLoop() throws IOException {
		
		// Setando o gerenciador de arquivos
		this.fileManipulator = FileManipulator.getInstance();
		
		// LOOP principal do analizador léxico
		int character = fileManipulator.getNextChar();
		
		while(character != -1) {
			// TODO implementar loop da maquina de estados do analisador léxico
			System.out.printf("%d:%d - %c\n", fileManipulator.getLineCounter(), fileManipulator.getColumnCounter(), character);
			// Continua a leitura para o prox caracter (após a definição do token)
			character = fileManipulator.getNextChar();
		}
		
		System.out.println("-- Leitura do arquivo finalizada, verifique o arquivo de saída --");
		
		return true;
	}

}
