package de.rechnertechnik.picsim.prozessor;

/**
 * Interface was Methoden bereitstellt, um Ports am Prozessor zu ändern
 * 
 * @deprecated
 * @author michael
 * 
 */
public interface IPorts {

	public void setPortA(Integer value);
	public void setBitPortA(Integer bitNr);
	public void clearBitPortA(Integer bitNr);
	
	public Integer getPortA();
	public void setTrisA(Integer value);

	public Integer getTrisA();

}
