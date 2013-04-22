package de.rechnertechnik.picsim.prozessor;

import java.util.ArrayList;

import javax.xml.bind.ValidationEvent;

import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;

/**
 * Stellt den Programmspeicher des PICs dar
 * 
 * @author michael
 * 
 */
public class Speicher {

	public static int SPEICHERGROESSE = 200;

	private Speicherzelle[] EPROM = new Speicherzelle[SPEICHERGROESSE];
	private Parser parser;

	//For Testing
	public Speicher(){
		initEPROM();
	}
	
	public Speicher(Parser parser) {
		this.parser = parser;

		// Init Speicher mit 0
		initEPROM();
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
				EPROM[i].setWert(asmProg.get(i));
			}
			catch(MemoryOutOfRangeException e) {
				System.err.println("Programmladefehler!");
				e.printStackTrace();
			}
		}

	}

	/**
	 * Init the EPROM with 0 in all Cells
	 */
	private void initEPROM() {
		for(int i = 0; i < SPEICHERGROESSE; i++) {
			try {
				EPROM[i] = new Speicherzelle(new Integer(0));

			}
			catch(MemoryOutOfRangeException e) {
				System.err.println("That shouldn't happen!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Wert in Speicherzelle schreiben
	 * 
	 * @param zelle
	 * @param value
	 */
	public void setZelle(int zelle, int value) throws MemoryOutOfRangeException {
		try {
			EPROM[zelle].setWert(value);
		}
		catch(MemoryOutOfRangeException e) {
			System.err.println("Wert liegt nicht zwischen 0 und 255!");
			throw new MemoryOutOfRangeException();
		}
	}

	/**
	 * Wert aus Speicherzelle lesen
	 * 
	 * @param i
	 * @return
	 */
	public Integer getZelle(int i) {
		try {
			return EPROM[i].getValue();
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Speicherbereich nicht vorhanden");
			e.printStackTrace();
		}
		return -1;
	}

	public Integer getAnzZellen() {
		return EPROM.length;
	}

}
