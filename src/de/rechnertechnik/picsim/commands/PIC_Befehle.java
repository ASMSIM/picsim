package de.rechnertechnik.picsim.commands;

import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.prozessor.MemoryOutOfRangeException;
import de.rechnertechnik.picsim.prozessor.Prozessor;
import de.rechnertechnik.picsim.prozessor.Speicherzelle;
import de.rechnertechnik.picsim.prozessor.Speicherzelle.bits;
import de.rechnertechnik.picsim.register.SpecialFunctionRegister;
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
	 * CLRF 00 0001 1fff ffff
	 * 
	 * @param befehl
	 * @param cpu
	 */
	
	public static void asm_clrf(Integer befehl, Prozessor cpu){
		
		Speicherzelle status = cpu.getStatus();
		
		Integer f = getOpcodeFromToBit(befehl, 0, 7);
		try {
			cpu.getRam().getZelle(f).setWert(0);
		} catch (MemoryOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		status.setBit(bits.Z);
		cpu.incPC();
	}
	
	/**
	 * ANDWF 00 0101 dfff ffff
	 * 
	 * @param befehl
	 * @param cpu
	 */
	public static void asm_andwf(Integer befehl, Prozessor cpu) {
		Integer w = cpu.getW().getWert();
		Integer f = getOpcodeFromToBit(befehl, 0, 7);
		Integer erg = w & f;
		
		if(getOpcodeFromToBit(befehl, 8, 8) == 1){			
			try {
				cpu.getRam().getZelle(f).setWert(erg);
			} catch (MemoryOutOfRangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {			
			cpu.getW().setWert(erg);
		}
			
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
		System.out.println("BCF!!!!!!");
		Integer bitNr = getOpcodeFromToBit(befehl, 7, 9);
//		bitNr = bitNr >> 7;

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

		k = k >> fromBit;

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
		
				//Komponenten auslesen
				Integer f = getOpcodeFromToBit(akt_Befehl, 0, 7);
				SpecialFunctionRegister w = cpu.getW();
				Integer result = f + w.getWert();
				
				//Speicherort abfragen
				if(getOpcodeFromToBit(akt_Befehl, 8, 8) == 1){			
					try {
						//in f Register speichern
						cpu.getRam().getZelle(f).setWert(result);
					} catch (MemoryOutOfRangeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					//in w Register speichern
					cpu.getW().setWert(result);
				}
				
				// Status setzen DC, Z, C
				setStatus(w, cpu);
				
				//PC ++
				cpu.incPC();
	}
	
	/**
	 * 
	 * COMF 00 1001 dfff ffff
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_comf(Integer akt_Befehl, Prozessor cpu) {
		
		Integer f = getOpcodeFromToBit(akt_Befehl, 0, 7);
		Integer result = 255-f;
		
		//Speicherort abfragen
		if(getOpcodeFromToBit(akt_Befehl, 8, 8) == 1){			
			try {
				//in f Register speichern
				cpu.getRam().getZelle(f).setWert(result);
			} catch (MemoryOutOfRangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//in w Register speichern
			cpu.getW().setWert(result);
		}
		cpu.incPC();
	}
	
		
	/**
	 * 
	 * XORLW
	 * 
	 * 11 1010 kkkk kkkk
	 * 
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_xorlw(Integer akt_Befehl, Prozessor cpu) {

		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);
		PIC_Logger.logger.info("[XORLW]: k=" + k);

		// Get W
		SpecialFunctionRegister w = cpu.getW();
		PIC_Logger.logger.info("[XORLW]: w=" + w.getWert());

		// XOR K ^ W
		Integer result = k ^ w.getWert();
		PIC_Logger.logger.info("[XORLW]: result=k^w=" + result);

		// Ergebnis in W
		w.setWert(result);

		PIC_Logger.logger.info("[XORLW]: w=" + w.getWert());

		// Check Zero Flag, setZero
		if(w.getWert() == 0) {
			PIC_Logger.logger.info("[XORLW]: SetZeroflag");
			checkZero(cpu);
		}

		// PC++
		cpu.incPC();
	}

	/**
	 * 
	 * SUBLW 11 110x kkkk kkkk
	 * 
	 * @param akt_Befehl
	 * @param cpu
	 */
	public static void asm_sublw(Integer akt_Befehl, Prozessor cpu) {
		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);

		// Get W
		SpecialFunctionRegister w = cpu.getW();

		// K - W
		Integer result = k - w.getWert();

		// Ergebnis in W
		w.setWert(result);

		// Status setzen DC, Z, C
		setStatus(w, cpu);
		
		//PC++
		cpu.incPC();

	}

	
	/**
	 * Setzt anhand des W Registers den aktuellen Status (Ueberlauf, zero...)
	 * @param w
	 * @param cpu
	 */
	private static void setStatus(SpecialFunctionRegister w, Prozessor cpu) {
		if(w.getWert() > 255) {
			// Ueberlauf abfangen
			checkC(w, cpu);
		}
		else if(w.getWert() < 0) {
			// Ueberlauf abfangen
			checkDC(w, cpu);
		}
		else if(w.getWert() == 0) {
			checkZero(cpu);
		}
	}

	/**
	 * Check W and set Zero bit
	 * @param cpu
	 */
	private static void checkZero(Prozessor cpu) {
		cpu.getStatus().setBit(bits.Z);
	}

	/**
	 * Check w and set DC bit
	 * @param w
	 * @param cpu
	 */
	private static void checkDC(SpecialFunctionRegister w, Prozessor cpu) {
		// Ueberlauf abfangen
		w.setWert(w.getWert() + 256);
		cpu.getStatus().setBit(bits.DC);
	}

	/**
	 * Check W and set C Bit
	 * @param w
	 * @param cpu
	 */
	private static void checkC(SpecialFunctionRegister w, Prozessor cpu) {
		
		w.setWert(w.getWert() - 256);
		cpu.getStatus().setBit(bits.C);
	}

	
	
	
	
	/**
	 * SLEEP
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_sleep(Integer akt_Befehl, Prozessor cpu) {
		//TODO implement
		cpu.incPC();
	}

	/**
	 * RETURN
	 * 
	 * @param akt_Befehl
	 * @param cpu
	 */
	public static void asm_return(Integer akt_Befehl, Prozessor cpu) {
		
		//Hole PC
		Speicherzelle pc = cpu.getPc();
		
		try {
			pc.setWert(cpu.getStack().pop());
		}
		catch(MemoryOutOfRangeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * RETLW
	 * 
	 * 11 01xx kkkk kkkk
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_retlw(Integer akt_Befehl, Prozessor cpu) {
		
		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);
		
		//Hole W
		SpecialFunctionRegister w = cpu.getW();
		
		//K in W
		w.setWert(k);
		
		//Normaler Return
		asm_return(akt_Befehl, cpu);
	}

	/**
	 * IORLW
	 * 
	 * 11 1000 kkkk kkkk
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_iorlw(Integer akt_Befehl, Prozessor cpu) {

		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);
		
		//Hole W
		SpecialFunctionRegister w = cpu.getW();
		
		//OR
		w.setWert(w.getWert() | k);
		
		//Zero bit
		checkZero(cpu);
		
		//PC++
		cpu.incPC();
		
	}

	
	/**
	 * CLRWDT
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_clrwdt(Integer akt_Befehl, Prozessor cpu) {
		//TODO NOT IMPLEMENTED
		cpu.incPC();
	}

	/**
	 * 
	 * ANDLW
	 * 
	 * 11 1001 kkkk kkkk
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_andlw(Integer akt_Befehl, Prozessor cpu) {
		
		
		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);
				
		//Hole W
		SpecialFunctionRegister w = cpu.getW();

		//AND
		w.setWert(w.getWert() & k);
		
		//Zero ueberpruefen
		checkZero(cpu);
		
		//PC++
		cpu.incPC();
	}

	/**
	 * ADDLW
	 * 
	 * 11 111x kkkk kkkk
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_addlw(Integer akt_Befehl, Prozessor cpu) {
		// Extrahiere K
		Integer k = getOpcodeFromToBit(akt_Befehl, 0, 7);

		// Get W
		SpecialFunctionRegister w = cpu.getW();

		// K - W
		Integer result = k + w.getWert();

		// Ergebnis in W
		w.setWert(result);

		// Status setzen DC, Z, C
		setStatus(w, cpu);
		
		//PC++
		cpu.incPC();

	}

	
	
	/**
	 * NOP
	 * 
	 * @param akt_Befehl
	 * @param cpu
	 */
	public static void asm_nop(Integer akt_Befehl, Prozessor cpu) {
		cpu.incPC();	//PC erhoehen
	}
	
	
	
	/**
	 * BTFSS
	 * 
	 * 01 11bb bfff ffff
	 * 
	 * @param akt_Befehl
	 * @param prozessor
	 */
	public static void asm_btfss(Integer akt_Befehl, Prozessor cpu) {
		
		Integer bitNr = getOpcodeFromToBit(akt_Befehl, 7, 9);
		Integer f = getOpcodeFromToBit(akt_Befehl, 0, 6);
		
		Speicherzelle zelle = cpu.getRam().getZelle(f);
		boolean bitIsSet= zelle.getBit(bitNr);
		
		//Bit is set
		if(bitIsSet){
			asm_nop(akt_Befehl, cpu);
		}
		//Bit not set
		else{
			//Nothing
		}
		
		//PC++
		cpu.incPC();
		
	}

}
