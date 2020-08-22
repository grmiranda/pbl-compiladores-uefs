package uefs.pbl.compiladores.view;

import java.io.IOException;

import uefs.pbl.compiladores.controller.LexiconController;
import uefs.pbl.compiladores.util.FileManipulator;

/**
 * Classe Main da aplicação.
 * @author Gabriel Reis Miranda
 *
 */
public class App {

	private static FileManipulator fileManipulator = null;	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Setando o gerenciador de arquivos
		fileManipulator = FileManipulator.getInstance();
		// Setando nome inicial do arquivo de entrada
		fileManipulator.setInputFileURL("entrada.txt");
		
		try {
			//Iniciando Analizador Léxico
			LexiconController  lexicon = new LexiconController();
			if(lexicon.mainLoop()) {
				System.out.println("FIM");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
