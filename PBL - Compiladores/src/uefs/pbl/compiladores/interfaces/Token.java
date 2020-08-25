package uefs.pbl.compiladores.interfaces;

public interface Token {
	public String toString();
	public boolean isErrorToken();
	public String getTokenCode();
	public String getTokenValue();
	public int getTokenLine();
	public int getTokenColumn();
}
