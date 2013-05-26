package de.rechnertechnik.picsim.prozessor;

import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.prozessor.Speicherzelle.bits;


/**
 * Stellt den Programmspeicher des PICs dar
 * 
 * @author michael
 * 
 */
public class Speicher {
	
	
	public enum Bank{
		BANK0, BANK1
	}
	

	public static int SPEICHERGROESSE = 300;
	protected Speicherzelle[] speicherZellen = new Speicherzelle[SPEICHERGROESSE];
	private Bank bank = Bank.BANK0;
	
	

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
	public void initSpeicher() {
		for(int i = 0; i < SPEICHERGROESSE; i++) {
				speicherZellen[i] = new Speicherzelle(new Integer(0));

		}
	}

	
	
	
	
	/**
	 * Wert in Speicherzelle schreiben
	 * 
	 * @param adresse
	 * @param value
	 */
	public void setZelle(int adresse, int value) {
		
		speicherZellen[adresse].setWert(value);
	}

	
	public Bank getBank() {
		return bank;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
	/**
	 * Wert aus Speicherzelle lesen
	 * 
	 * @param adresse
	 * @return
	 */
	public Integer getSpeicherzellenWert(int adresse) {
		try {
			return speicherZellen[adresse].getValue();
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Speicherbereich nicht vorhanden");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Wert aus Speicherzelle lesen
	 * 
	 * @deprecated
	 * 
	 * @param i
	 * @return
	 */
	private Speicherzelle getZelle(int i) {
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
			if(lineCnt % 16 == 0)
				System.out.println();
		}

	}

}
