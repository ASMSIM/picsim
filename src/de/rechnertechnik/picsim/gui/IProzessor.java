package de.rechnertechnik.picsim.gui;

import java.io.File;

/**
 * Schnittstelle für Methoden, die auf dem Prozessor aufrufbar sein müssen!
 * 
 * @author michael
 *
 */
public interface IProzessor {
	
	public void nextStep();
	public void go();
	public void reset();
	public void change_RAM_Value(Integer adresse, Integer to_Value);
	public Integer get_RAM_Value(Integer adresse);
	public Integer getQuartzTakt();
	public void openFile(File file);
	
}
