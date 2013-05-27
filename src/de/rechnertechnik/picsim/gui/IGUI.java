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
	public void show_W_Register(String hexvalue);
	public void show_Register(String adresse, String hexvalue);
	public void show_PC(String value);
	public void show_PortA(Integer value);
	public void show_TrisA(Integer value);
}
