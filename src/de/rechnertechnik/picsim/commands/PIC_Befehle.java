package de.rechnertechnik.picsim.commands;

import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.prozessor.MemoryOutOfRangeException;
import de.rechnertechnik.picsim.prozessor.Prozessor;
import de.rechnertechnik.picsim.prozessor.Speicherzelle;
import de.rechnertechnik.picsim.stringhex.StringHex;

public class PIC_Befehle {

	/**
	 * GOTO Befehl 0010 1KKK KKKK KKKK
	 */
	public static Integer asm_goto(Integer next_Befehl) {
		return getOpcodeFromToBit(next_Befehl, 0, 7);
	}

	/**
	 * MOVLW Befehl 11 00xx kkkk kkkk
	 * 
	 * @param befehl
	 * @param cpu
	 */
	public static void asm_movlw(Integer befehl, Prozessor cpu) {
		cpu.getW().setWert(getOpcodeFromToBit(befehl, 0, 7));
	}

	/**
	 * BSF 01 01bb bfff ffff
	 * 
	 * @param befehl
	 * @param cpu
	 */
	public static void asm_bsf(Integer befehl, Prozessor cpu) {
		Integer bitNr = getOpcodeFromToBit(befehl, 7, 9);
		bitNr = bitNr >> 7;

		Integer adresse = getOpcodeFromToBit(befehl, 0, 6);

		PIC_Logger.LOGGER.info("BitNr: " + bitNr + "\nAdresse: " + adresse);

		Integer setValue = (int) Math.pow(2, bitNr);
		PIC_Logger.LOGGER.info("Bitvalue: " + setValue);

		Speicherzelle workingCell = cpu.getRam().getZelle(adresse);

		PIC_Logger.LOGGER.info("Wert vorher: 0x"
				+ Integer.toHexString(workingCell.getValue()));
		try {
			workingCell.setWert(workingCell.getValue() | setValue);
		}
		catch(MemoryOutOfRangeException e) {
			System.err.println("Speicherbereich nicht vorhanden!");
			e.printStackTrace();
		}

		PIC_Logger.LOGGER.info("Wert nacher: 0x"
				+ Integer.toHexString(workingCell.getValue()));
	}

	
	
	
	public static void asm_clrw(Integer befehl, Prozessor cpu){
		cpu.getW().setWert(0);
		Speicherzelle status = cpu.getStatus();
		
		try {
			status.setWert(status.getValue() | (int)Math.pow(2, 2));
		}
		catch(MemoryOutOfRangeException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * BCF 01 00bb bfff ffff
	 * 
	 * @param befehl
	 * @param cpu
	 */
	public static void asm_bcf(Integer befehl, Prozessor cpu){
		Integer bitNr = getOpcodeFromToBit(befehl, 7, 9);
		bitNr = bitNr >> 7;

		Integer adresse = getOpcodeFromToBit(befehl, 0, 6);

		PIC_Logger.LOGGER.info("BitNr: " + bitNr + "\nAdresse: " + adresse);

		Integer setValue = new Integer(0xFF); 
		
		//Minus !
		setValue = setValue -(int) Math.pow(2, bitNr);
		PIC_Logger.LOGGER.info("Bitvalue: " + setValue);

		Speicherzelle workingCell = cpu.getRam().getZelle(adresse);
		
		
		PIC_Logger.LOGGER.info("Wert vorher: 0x"
				+ Integer.toHexString(workingCell.getValue()));
		try {
			//Verunden
			workingCell.setWert(workingCell.getValue() & setValue);
		}
		catch(MemoryOutOfRangeException e) {
			System.err.println("Speicherbereich nicht vorhanden!");
			e.printStackTrace();
		}

		PIC_Logger.LOGGER.info("Wert nacher: 0x"
				+ Integer.toHexString(workingCell.getValue()));
	}
	
	
	
	/**
	 * MOVWF Wert aus W in F speichern
	 * 
	 *	00 0000 1fff ffff 
	 * 
	 * @param befehl
	 * @param cpu
	 */
	public static void asm_movwf(Integer befehl, Prozessor cpu) {
		Integer adresse = getOpcodeFromToBit(befehl, 0, 6);
		Speicherzelle workingCell = cpu.getRam().getZelle(adresse);
		
		PIC_Logger.LOGGER.info("Adresse: " + adresse + "\nZelleninhalt: " + workingCell.getValue());
		
		try {
			workingCell.setWert(cpu.getW().getWert());
		}
		catch(MemoryOutOfRangeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Extrahiert eine Bitkette aus einer Bytekette
	 * 
	 * @param opcode
	 *            Ausgangsopcode
	 * @param fromBit
	 *            Startbit (startet bei bit0)
	 * @param toBit
	 *            Endbit
	 * 
	 * @return Integerwert der Bitkette
	 */
	public static Integer getOpcodeFromToBit(Integer opcode, int fromBit, int toBit) {

		// Groesser kleiner ;)
		if(fromBit > toBit) {
			int tmp = toBit;
			toBit = fromBit;
			fromBit = tmp;
		}

		int operand = 0;

		for(int i = fromBit; i <= toBit; i++) {
			operand += Math.pow(2, i);
		}

		int k = opcode & 0x3fff;
		k = opcode & operand;

		PIC_Logger.LOGGER.info("Extrahierter Hexwert: "
				+ Integer.toHexString(k));

		return k;
	}

}
