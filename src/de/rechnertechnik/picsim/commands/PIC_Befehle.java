package de.rechnertechnik.picsim.commands;

public class PIC_Befehle {

	
	/**
	 * GOTO Befehl
	 */
	public static Integer asm_goto(Integer arg, Integer pc){
		return pc+arg;
	}
	
}
