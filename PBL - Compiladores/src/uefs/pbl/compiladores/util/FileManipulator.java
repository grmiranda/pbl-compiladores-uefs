package uefs.pbl.compiladores.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManipulator {
	
	
	private static FileManipulator instance; 	// Variável privada de armazenamento da instância

	private String inputFileURL = ""; 			// URL do arquivo de leitura
	private String outputFileURL = ""; 			// URL do arquivo de escrita
	private FileReader inputFile = null; 		// Arquivo para leitura
	private BufferedReader fileScanner = null; 	// Scanner do arquivo de entrada
	private int lineCounter = 1;				// Contador da linha de leitura
	private int columnCounter = 0;				// Contador da coluna de leitura
	private boolean newLineFlag = false;		// Flag indicadora de nova linha na leitura
	
	private FileManipulator () {}
	
	// Método principal do Singleton	
	public static synchronized FileManipulator getInstance() {
		if (instance == null) { instance = new FileManipulator(); }
		return instance;
	}
	
	// SETTERS	
	public void setInputFileURL (String url) { this.inputFileURL = url; }
	public void setOutputFileURL (String url) { this.outputFileURL = url; }
	
	// GETTERS	
	public String getInputFileUR () { return this.inputFileURL; }
	public String getOutputtFileUR () { return this.outputFileURL; }
	public int getLineCounter () { return this.lineCounter; }
	public int getColumnCounter () { return this.columnCounter; }
	
	// Método para pegar o próx caracter na leitura
	public int getNextChar() throws IOException {
		int inputChar = 0;
		
		// Verifica se a leitura do arquivo já foi iniciada	
		if (this.inputFile == null || this.fileScanner == null) {
			// TODO Verificar e disparar Exception qunado inputFileURL não estiver setado
			this.inputFile = new FileReader(this.inputFileURL); 
			this.fileScanner = new BufferedReader(this.inputFile);
		}
		
		// Atualiza os contadores (se necessário) e lê o próximo caractere
		inputChar = this.fileScanner.read();
		if(this.newLineFlag) { 
			this.lineCounter++; 
			this.newLineFlag = false;
			this.columnCounter = 0;
		}
		this.columnCounter++;
		if(inputChar == '\n') { this.newLineFlag = true; }
		
		// Verifica se chegou o fim do arquivo e fecha o arquivo
		if(inputChar == -1) {
			this.inputFile.close();
			this.inputFile = null; 
			this.fileScanner = null;
		}
		// Retorna o resultado lido
		return inputChar;
	}
}
