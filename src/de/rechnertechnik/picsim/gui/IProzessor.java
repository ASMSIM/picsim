package de.rechnertechnik.picsim.gui;

public interface IProzessor {
	
	public void nextStep();
	public void go();
	public void reset();
	public void change_RAM_Value(Integer adresse, Integer to_Value);
	public Integer get_RAM_Value(Integer adresse);
	
	
}
