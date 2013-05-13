package de.rechnertechnik.picsim.commands;

import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.prozessor.MemoryOutOfRangeException;
import de.rechnertechnik.picsim.prozessor.Prozessor;
import de.rechnertechnik.picsim.prozessor.Speicherzelle;
import de.rechnertechnik.picsim.prozessor.Speicherzelle.bits;
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
		cpu.incPC();
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

		PIC_Logger.logger.info("BitNr: " + bitNr + "\nAdresse: " + adresse);

		Integer setValue = (int) Math.pow(2, bitNr);
		PIC_Logger.logger.info("Bitvalue: " + setValue);

		Speicherzelle workingCell = cpu.getRam().getZelle(adresse);

		PIC_Logger.logger.info("Wert vorher: 0x"
				+ Integer.toHexString(workingCell.getValue()));
		try {
			workingCell.setWert(workingCell.getValue() | setValue);
		}
		catch(MemoryOutOfRangeException e) {
			System.err.println("Speicherbereich nicht vorhanden!");
			e.printStackTrace();
		}

		PIC_Logger.logger.info("Wert nacher: 0x"
				+ Integer.toHexString(workingCell.getValue()));
		cpu.incPC();
	}

	public static void asm_clrw(Integer befehl, Prozessor cpu) {
		cpu.getW().setWert(0);
		Speicherzelle status = cpu.getStatus();

		PIC_Logger.logger.info("Status before: "+Integer.toHexString(cpu.getStatus().getValue()));
		
		// Zero Flag
		// status.setWert(status.getValue() | (int) Math.pow(2, 2));
		status.setBit(bits.Z);
		
		PIC_Logger.logger.info("Status after: "+Integer.toHexString(cpu.getStatus().getValue()));

		cpu.incPC();

	}

	/**
	 * BCF 01 00bb bfff ffff
	 * 
	 * @param befehl
	 * @param cpu
	 */
	public static void asm_bcf(Integer befehl, Prozessor cpu) {
		Integer bitNr = getOpcodeFromToBit(befehl, 7, 9);
		bitNr = bitNr >> 7;

		Integer adresse = getOpcodeFromToBit(befehl, 0, 6);

		PIC_Logger.logger.info("BitNr: " + bitNr + "\nAdresse: " + adresse);

		Integer setValue = new Integer(0xFF);

		// Minus !
		setValue = setValue - (int) Math.pow(2, bitNr);
		PIC_Logger.logger.info("Bitvalue: " + setValue);

		Speicherzelle workingCell = cpu.getRam().getZelle(adresse);

		PIC_Logger.logger.info("Wert vorher: 0x"
				+ Integer.toHexString(workingCell.getValue()));
		try {
			// Verunden und dadurch Bit loeschen
			workingCell.setWert(workingCell.getValue() & setValue);
		}
		catch(MemoryOutOfRangeException e) {
			System.err.println("Speicherbereich nicht vorhanden!");
			e.printStackTrace();
		}

		PIC_Logger.logger.info("Wert nacher: 0x"
				+ Integer.toHexString(workingCell.getValue()));

		cpu.incPC();
	}

	/**
	 * MOVWF Wert aus W in F speichern
	 * 
	 * 00 0000 1fff ffff
	 * 
	 * @param befehl
	 * @param cpu
	 */
	public static void asm_movwf(Integer befehl, Prozessor cpu) {
		Integer adresse = getOpcodeFromToBit(befehl, 0, 6);
		Speicherzelle workingCell = cpu.getRam().getZelle(adresse);

		PIC_Logger.logger.info("Adresse: " + adresse + "\nZelleninhalt: "
				+ workingCell.getValue());

		try {
			workingCell.setWert(cpu.getW().getWert());
		}
		catch(MemoryOutOfRangeException e) {
			e.printStackTrace();
		}

		PIC_Logger.logger.info("W: "
				+ Integer.toHexString(cpu.getW().getWert()));

		cpu.incPC();
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

		PIC_Logger.logger.info("Extrahierter Hexwert: "
				+ Integer.toHexString(k));

		return k;
	}

	/*
	 * CALL Befehl
	 * 
	 * 10 0kkk kkkk kkkk
	 */
	public static void asm_call(Integer akt_Befehl, Prozessor cpu) {

		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 10);

		// Push PC + 1 auf Stack
		Integer pc_inc = cpu.getPc().getValue() + 1;
		cpu.getStack().push(pc_inc);

		// PC auf K Wert setzen
		try {
			cpu.getPc().setWert(k);
		}
		catch(MemoryOutOfRangeException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ADDWF Befehl
	 * 
	 * 00 0111 dfff ffff
	 * 
	 * Status: C, DC, Z
	 * 
	 * 
	 * @param akt_Befehl
	 * @param cpu
	 */
	public static void asm_addwf(Integer akt_Befehl, Prozessor cpu) {

	}

}
