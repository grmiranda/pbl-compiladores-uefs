package uefs.pbl.compiladores.model;

import uefs.pbl.compiladores.interfaces.Token;

public class InvalidStringToken implements Token {
	
	private boolean errorToken = true;
	private String tokenCode = "CMF";
	private String tokenValue = null;
	private int tokenLine = 0;
	private int tokenColumn = 0;
	
	public InvalidStringToken (String value, int line, int column) {
		this.tokenValue = value;
		this.tokenLine = line;
		this.tokenColumn = column;
	}

	@Override
	public boolean isErrorToken() { return this.errorToken; }
	@Override
	public String getTokenCode() { return this.tokenCode; }
	@Override
	public String getTokenValue() { return this.tokenValue; }
	@Override
	public int getTokenLine() { return this.tokenLine; }
	@Override
	public int getTokenColumn() { return this.tokenColumn; }
	@Override
	public String toString() {
		return this.tokenLine + ":" + this.tokenColumn + " - " + this.tokenCode + "\t" + this.tokenValue;
	}

}
