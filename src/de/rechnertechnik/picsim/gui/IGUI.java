package de.rechnertechnik.picsim.gui;

import java.util.ArrayList;

public interface IGUI {

	public void showSourcecode(ArrayList<String> sourceLine, Integer fokus);
	public void setFocus(Integer fokus);
	
}
