package de.rechnertechnik.picsim.gui;

import java.util.ArrayList;

/**
 * Schnittstelle für Methoden, die auf der GUI aufrufbar sein müssen!
 * 
 * @author michael
 *
 */
public interface IGUI {

	public void showSourcecode(ArrayList<String> sourceLine, Integer fokus);
	public void setFocus(Integer fokus);
	public void show_W_Register(Integer value);			//HEX?
	public void show_Register(Integer adresse, Integer value);
	public void show_PC(Integer value);
	public void show_PortA(Integer value);
	public void show_TrisA(Integer value);
	public void setLaufzeitCounter(Integer microSec);
	public void showStatus(Integer value);
	public void showFSR(Integer value);
	
	public void show_PortB(Integer value);
	public void show_TrisB(Integer value);
	
}
