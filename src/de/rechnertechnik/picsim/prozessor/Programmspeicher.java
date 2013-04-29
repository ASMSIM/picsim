package de.rechnertechnik.picsim.prozessor;

import java.util.ArrayList;

import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;

public class Programmspeicher extends Speicher {
	
	
	private Parser parser;
	
	public Programmspeicher(Parser parser) {
		super();				//Init mit 0
		this.parser = parser;

		loadProgramm();
		PIC_Logger.LOGGER.info("Program loaded successfully!");
	}
	
	
	/**
	 * Load Program file from Parser into Memory
	 */
	private void loadProgramm() {
		ArrayList<Integer> asmProg = parser.getAsmProg();
		
		if(asmProg.size() >= SPEICHERGROESSE){
			PIC_Logger.LOGGER.fine("Speicher zu klein!!");
			System.exit(-1);
		}
		
		
		for(int i = 0; i < asmProg.size(); i++) {
			try {
				speicherZellen[i].setWert(asmProg.get(i));
			}
			catch(MemoryOutOfRangeException e) {
				System.err.println("Programmladefehler!");
				e.printStackTrace();
			}
		}

	}
	


	
	

}
