package de.rechnertechnik.picsim.commands;

import java.util.HashMap;

/**
 * Enthält die Adressräume aller PIC-Befehle Es wird zu jedem Befehl der
 * jeweilige Adressraum in einer Hashmap gespeichert
 * 
 * @author michael
 * 
 */
public class BefehlAdressraumZuordnung {

	//Enthält die Zuordnung: Befehl -> Adressraum
	private HashMap<ECommands, Adressraum> assemblerCommand = new HashMap<ECommands, Adressraum>();

	public BefehlAdressraumZuordnung() {

		// Load all Assembler Commands from PIC
		assemblerCommand.put(ECommands.GOTO, new Adressraum(0x2800, 0x2FFF));
		assemblerCommand.put(ECommands.ADDWF, new Adressraum(0x0700, 0x07FF));
		assemblerCommand.put(ECommands.ANDWF, new Adressraum(0x0500, 0x05FF));
		assemblerCommand.put(ECommands.CLRF, new Adressraum(0x0180, 0x01FF));
		assemblerCommand.put(ECommands.CLRW, new Adressraum(0x0100, 0x017F));
		assemblerCommand.put(ECommands.COMF, new Adressraum(0x0900, 0x09FF));
		assemblerCommand.put(ECommands.DECF, new Adressraum(0x0300, 0x03FF));
		assemblerCommand.put(ECommands.DECFSZ, new Adressraum(0x0B00, 0x0BFF));
		assemblerCommand.put(ECommands.INCF, new Adressraum(0x0A00, 0x0AFF));
		assemblerCommand.put(ECommands.INCFSZ, new Adressraum(0x0F00, 0x0FFF));
		assemblerCommand.put(ECommands.IORWF, new Adressraum(0x0400, 0x04FF));
		assemblerCommand.put(ECommands.MOVF, new Adressraum(0x0800, 0x08FF));
		assemblerCommand.put(ECommands.MOVWF, new Adressraum(0x0080, 0x00FF));
		assemblerCommand.put(ECommands.NOP, new Adressraum(0x0000, 0x0060)); // !!!!!
		assemblerCommand.put(ECommands.RLF, new Adressraum(0x0D00, 0x0DFF));
		assemblerCommand.put(ECommands.RRF, new Adressraum(0x0C00, 0x0CFF));
		assemblerCommand.put(ECommands.SUBWF, new Adressraum(0x0200, 0x02FF));
		assemblerCommand.put(ECommands.SWAPF, new Adressraum(0x0E00, 0x0EFF));
		assemblerCommand.put(ECommands.XORWF, new Adressraum(0x0600, 0x06FF));

		assemblerCommand.put(ECommands.BCF, new Adressraum(0x1000, 0x13FF));
		assemblerCommand.put(ECommands.BSF, new Adressraum(0x1400, 0x17FF));
		assemblerCommand.put(ECommands.BTFSC, new Adressraum(0x1800, 0x1BFF));
		assemblerCommand.put(ECommands.BTFSS, new Adressraum(0x1C00, 0x1FFF));

		assemblerCommand.put(ECommands.ADDLW, new Adressraum(0x3E00, 0x3FFF));
		assemblerCommand.put(ECommands.ANDLW, new Adressraum(0x3900, 0x39FF));
		assemblerCommand.put(ECommands.CALL, new Adressraum(0x2000, 0x27FF));
		assemblerCommand.put(ECommands.CLRWDT, new Adressraum(0x0064, 0x0064));
		assemblerCommand.put(ECommands.GOTO, new Adressraum(0x2800, 0x2FFF));
		assemblerCommand.put(ECommands.IORLW, new Adressraum(0x3800, 0x38FF));
		assemblerCommand.put(ECommands.MOVLW, new Adressraum(0x3000, 0x33FF));
		assemblerCommand.put(ECommands.RETFIE, new Adressraum(0x0009, 0x0009));
		assemblerCommand.put(ECommands.RETLW, new Adressraum(0x3400, 0x37FF));
		assemblerCommand.put(ECommands.RETURN, new Adressraum(0x0008, 0x0008));
		assemblerCommand.put(ECommands.SLEEP, new Adressraum(0x0063, 0x0063));
		assemblerCommand.put(ECommands.SUBLW, new Adressraum(0x3C00, 0x3DFF));
		assemblerCommand.put(ECommands.XORLW, new Adressraum(0x3A00, 0x3AFF));

	}

	public HashMap<ECommands, Adressraum> getAssemblerCommand() {
		return assemblerCommand;
	}

}
