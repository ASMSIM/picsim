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

	public static int SPEICHERGROESSE = 300;

	protected Speicherzelle[] speicherZellen = new Speicherzelle[SPEICHERGROESSE];

	/**
	 * Creates Memory and init with Zeros
	 */
	public Speicher() {
		// Init Speicher mit 0
		initSpeicher();
	}

	/**
	 * Init the Memory with 0 in all Cells
	 */
	protected void initSpeicher() {
		for(int i = 0; i < SPEICHERGROESSE; i++) {
				speicherZellen[i] = new Speicherzelle(new Integer(0));

		}
	}

	/**
	 * Wert in Speicherzelle schreiben
	 * 
	 * @param zelle
	 * @param value
	 */
	public void setZelle(int zelle, int value) throws MemoryOutOfRangeException {
			speicherZellen[zelle].setWert(value);
	}

	/**
	 * Wert aus Speicherzelle lesen
	 * 
	 * @param i
	 * @return
	 */
	public Speicherzelle getZelle(int i) {
		try {
			return speicherZellen[i];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Speicherbereich nicht vorhanden");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Gibt das Statusregister zurück
	 * 
	 * @param bank1 true=bank1, false=bank2
	 * @return
	 */
	public Speicherzelle getStatus(boolean bank1){
		if(bank1) return speicherZellen[0x03];
		else return speicherZellen[0x83];
	}
	
 
	/**
	 * Gibt den Programmcounter zurück
	 * 
	 * @param bank1 true=bank1, false=bank2
	 * @return
	 */
	public Speicherzelle getPCL(boolean bank1) {
		if(bank1) return speicherZellen[0x02];
		else return speicherZellen[0x82];
	}
	
	
	public Integer getAnzZellen() {
		return speicherZellen.length;
	}

	public void printDump() {
		System.out.println("\nSpeicherdump:");
		int lineCnt = 0;
		for(int i = 0; i < SPEICHERGROESSE; i++) {
			System.out.print(Integer.toHexString(speicherZellen[i].getValue()) + "\t");
			lineCnt++;
			if(lineCnt % 8 == 0)
				System.out.println();
		}

	}

}
