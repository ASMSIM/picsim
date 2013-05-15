package de.rechnertechnik.picsim.gui;

import java.util.ArrayList;

public interface IGUI {

	public void showSourcecode(ArrayList<String> sourceLine, Integer fokus);
	public void setFocus(Integer fokus);
	public void show_W_Register(String hexvalue);
	public void show_Register(String adresse, String hexvalue);
	public void show_PC(String value);
	
}
