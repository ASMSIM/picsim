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

	public Integer getPortA();

	public void setTrisA(Integer value);

	public Integer getTrisA();

}
