package de.rechnertechnik.picsim.prozessor;

/**
 * Interface was Methoden bereitstellt, um Ports am Prozessor zu ändern
 * 
 * @author michael
 * 
 */
public interface IPorts {

	//Bitoperationen
	public void setBitPortA(Integer bitNr);
	public void clearBitPortA(Integer bitNr);
	
	public void setPortA(Integer value);
	public Integer getPortA();

	public void setTrisA(Integer value);
	public Integer getTrisA();

	//Bitoperationen
	public void setBitPortB(Integer value);
	public void clearBitPortB(Integer bitNr);
	
	public void setPortB(Integer value);
	public Integer getPortB();

	public void setTrisB(Integer value);
	public Integer getTrisB();

	
	
}
