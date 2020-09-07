package uefs.pbl.compiladores.view;

import java.io.IOException;
import java.util.Scanner;

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
		
		// Solicitando ao usuário o nome dos arquivos de entrada e de saida do programa:
		Scanner systemInput = new Scanner(System.in);
		System.out.println("Informe o nome do arquivo de entrada:");
		String inputFileName = systemInput.nextLine();
		// String inputFileName = "entrada.txt";
		System.out.println("Informe o nome do arquivo de saida:");
		String outputFileName = systemInput.nextLine();
		// String outputFileName = "saida.txt";
		systemInput.close();
		
		// Setando o gerenciador de arquivos
		fileManipulator = FileManipulator.getInstance();
		// Setando nome do arquivo de entrada
		fileManipulator.setInputFileURL(inputFileName);
		// Setando nome do arquivo de saida
		fileManipulator.setOutputFileURL(outputFileName);
		
		try {
			//Iniciando Analizador Léxico
			LexiconController  lexicon = LexiconController.getInstance();
			lexicon.analyze(); // TODO Deve retornar um array de tokens
			System.out.println("-- Analise Léxica finalizada, verifique o arquivo de saída --");
			fileManipulator.write(lexicon.tokensToString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
