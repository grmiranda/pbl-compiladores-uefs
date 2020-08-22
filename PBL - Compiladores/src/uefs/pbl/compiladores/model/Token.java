package uefs.pbl.compiladores.model;

public interface Token {
	public String toString();
	public boolean isErrorToken();
	public String getTokenValue();
	public String getTokenStart();
}
