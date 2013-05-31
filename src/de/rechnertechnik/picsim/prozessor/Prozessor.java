package de.rechnertechnik.picsim.prozessor;

import java.util.Stack;
import java.util.logging.Level;

import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.commands.BefehlAdressraumZuordnung;
import de.rechnertechnik.picsim.commands.ECommands;
import de.rechnertechnik.picsim.gui.IGUI;
import de.rechnertechnik.picsim.gui.IProzessor;
import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;
import de.rechnertechnik.picsim.register.SpecialFunctionRegister;
import de.rechnertechnik.picsim.speicher.Speicher;
import de.rechnertechnik.picsim.speicher.Speicherzelle;
import de.rechnertechnik.picsim.speicher.Speicher.Bank;
import de.rechnertechnik.picsim.speicher.Speicherzelle.bits;
import de.rechnertechnik.picsim.prozessor.EStepmode;

public class Prozessor implements Runnable, IProzessor, IPorts {

	private boolean stopProgram = false;
	private BefehlAdressraumZuordnung cmdTable;
	private Programmspeicher programmSpeicher;
	private Speicher ram;
	private IGUI gui;
	private Integer laufzeit = 0;
	
	private EStepmode stepmode;
	
	private Interrupt interruptHandler = new Interrupt();
	private Integer megaHertz = 4;
	
	

	/**
	 * Programmcounter
	 */
	private Speicherzelle pc;
	private Speicherzelle status;
	private SpecialFunctionRegister w = new SpecialFunctionRegister(0);
	private Parser parser;
	private Stack<Integer> stack = new Stack<Integer>();

	public Prozessor(BefehlAdressraumZuordnung cmdTable, Programmspeicher programmSpeicher, Speicher ram, IGUI gui, Parser parser) {
		this.cmdTable = cmdTable;
		this.programmSpeicher = programmSpeicher;
		this.ram = ram;
		this.gui = gui;
		this.parser = parser;

		// Hold
		stepmode = EStepmode.hold;

		status = ram.getStatus(true);
		pc = ram.getPCL(true);

		gui.setLaufzeitCounter(laufzeit);
		
		//Option
		setSpeicherzellenWert(0x03, 0x18, false);
		setSpeicherzellenWert(0x83, 0x18, false);
		
		//Status
		setSpeicherzellenWert(0x81, 0xff, false);
		
		//Init Port A
		gui.show_PortA(0x00);
		gui.show_TrisA(0x00);
		
		//Init Port B
		gui.show_PortB(0x00);
		gui.show_TrisB(0x00);
		

		// Init und Fokus auf 1. Zeile
		gui.showSourcecode(parser.getSourceLine(), parser.getCommand_source_line().get(0));

		Thread runProgram = new Thread(this);
		runProgram.start();
	}

	/**
	 * Enthält die Befehlsverarbeitunsschleife
	 */
	@Override
	public void run() {
		
		while(stepmode==EStepmode.hold){
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// INIT
		while(!stopProgram) {

			Integer akt_Befehl = nextCommand();

			PIC_Logger.logger.log(Level.INFO, "Next command: 0x"
					+ Integer.toHexString(akt_Befehl));

			// GOTO
			if(isIntegerCommand(ECommands.GOTO, akt_Befehl)) {
				System.out.println("GOTO");
				PIC_Befehle.asm_goto(akt_Befehl, this);
			}

			// ADDWF
			else if(isIntegerCommand(ECommands.ADDWF, akt_Befehl)) {
				System.out.println("ADDWF");
				PIC_Logger.logger.info("-> ADDWF");
				PIC_Befehle.asm_addwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.ANDWF, akt_Befehl)) {
				System.out.println("ANDWF");
				PIC_Logger.logger.info("-> ANDWF");
				PIC_Befehle.asm_andwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CLRF, akt_Befehl)) {
				System.out.println("CLRF");
				PIC_Logger.logger.info("-> CLRF");
				PIC_Befehle.asm_clrf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CLRW, akt_Befehl)) {
				System.out.println("CLRW");
				PIC_Logger.logger.info("-> CLRW");
				PIC_Befehle.asm_clrw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.COMF, akt_Befehl)) {
				PIC_Logger.logger.info("-> COMF");
				PIC_Befehle.asm_comf(akt_Befehl, this);
				System.out.println("COMF");
			}

			else if(isIntegerCommand(ECommands.DECF, akt_Befehl)) {
				System.out.println("DECF");
				PIC_Logger.logger.info("-> DECF");
				PIC_Befehle.asm_decf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.DECFSZ, akt_Befehl)) {
				System.out.println("DECFSZ");
				PIC_Logger.logger.info("-> DECFSZ");
				PIC_Befehle.asm_decfsz(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.INCF, akt_Befehl)) {
				System.out.println("INCF");
				PIC_Logger.logger.info("-> INCF");
				PIC_Befehle.asm_incf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.INCFSZ, akt_Befehl)) {
				System.out.println("INCFSZ");
				PIC_Logger.logger.info("-> INCFSZ");
				PIC_Befehle.asm_incfsz(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.IORWF, akt_Befehl)) {
				System.out.println("IORWF");
				PIC_Logger.logger.info("-> IORWF");
				PIC_Befehle.asm_iorwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.MOVF, akt_Befehl)) {
				System.out.println("MOVF");
				PIC_Logger.logger.info("-> MOVF");
				PIC_Befehle.asm_movf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.MOVWF, akt_Befehl)) {
				System.out.println("MOVWF");
				PIC_Befehle.asm_movwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.NOP, akt_Befehl)) {
				System.out.println("NOP");
				PIC_Logger.logger.info("-> NOP");
				PIC_Befehle.asm_nop(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RLF, akt_Befehl)) {
				System.out.println("RLF");
				PIC_Logger.logger.info("-> RLF");
				PIC_Befehle.asm_rlf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RRF, akt_Befehl)) {
				System.out.println("RRF");
				PIC_Befehle.asm_rrf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.SUBWF, akt_Befehl)) {
				System.out.println("SUBWF");
				PIC_Befehle.asm_subwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.SWAPF, akt_Befehl)) {
				System.out.println("SWAPF");
				PIC_Befehle.asm_swapf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.XORWF, akt_Befehl)) {
				System.out.println("XORWF");
				PIC_Befehle.asm_xorwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.BCF, akt_Befehl)) {
				System.out.println("BCF");
				PIC_Befehle.asm_bcf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.BSF, akt_Befehl)) {
				System.out.println("BSF");
				PIC_Befehle.asm_bsf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.BTFSC, akt_Befehl)) {
				System.out.println("BTFSC");
				PIC_Befehle.asm_btfsc(akt_Befehl, this);

			}

			else if(isIntegerCommand(ECommands.BTFSS, akt_Befehl)) {
				System.out.println("BTFSS");
				PIC_Befehle.asm_btfss(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.ADDLW, akt_Befehl)) {
				System.out.println("ADDLW");
				PIC_Befehle.asm_addlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.ANDLW, akt_Befehl)) {
				System.out.println("ANDLW");
				PIC_Befehle.asm_andlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CALL, akt_Befehl)) {
				System.out.println("CALL");
				PIC_Befehle.asm_call(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CLRWDT, akt_Befehl)) {
				System.out.println("CLRWDT: TODO");
				PIC_Befehle.asm_clrwdt(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.IORLW, akt_Befehl)) {
				System.out.println("IORLW");
				PIC_Befehle.asm_iorlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.MOVLW, akt_Befehl)) {
				System.out.println("MOVLW");
				PIC_Befehle.asm_movlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RETFIE, akt_Befehl)) {
				System.out.println("RETFIE");
				PIC_Befehle.asm_retfie(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RETLW, akt_Befehl)) {
				System.out.println("RETLW");
				PIC_Befehle.asm_retlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RETURN, akt_Befehl)) {
				System.out.println("RETURN");
				PIC_Befehle.asm_return(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.SLEEP, akt_Befehl)) {
				System.out.println("SLEEP: TODO");
				PIC_Befehle.asm_sleep(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.SUBLW, akt_Befehl)) {
				System.out.println("SUBLW");
				PIC_Befehle.asm_sublw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.XORLW, akt_Befehl)) {
				System.out.println("XORLW");
				PIC_Befehle.asm_xorlw(akt_Befehl, this);
			}

			else {
				System.out.println("Befehl nicht implementiert !");
				this.stopProgram = true;
			}

			gui.setFocus(parser.getCommand_source_line().get(pc.getValue()));
			gui.show_Register(0x02, pc.getValue());
			gui.show_Register(0x82, pc.getValue());
			gui.setLaufzeitCounter(laufzeit);		//Laufzeit aktualisieren
			
			//Check Interrupt
			interruptHandler.checkInterrupt(this);
			
			
			
			if(stepmode == EStepmode.onestep) {
				stepmode = EStepmode.hold;
			}
			else if(stepmode == EStepmode.go) {
				try {
					Thread.sleep(10);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}

			while(stepmode == EStepmode.hold) {
				try {
					Thread.sleep(200);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	
	
	public Integer getMegaHertz() {
		return megaHertz;
	}
	
	
	
	/**
	 * Inkrementiere Programmcounter
	 */
	public void incPC() {
		Integer value =pc.getValue();
		setPCL(value + 1);
		gui.show_PC(value);			//TODO CHECK?
		gui.show_Register(0x02, value);
		gui.show_Register(0x82, value);
	}

	
	/**
	 * Liefert den Wert einer Speicherzelle zurück
	 * @param adresse
	 * @return
	 */
	public Integer getSpeicherzellenWert(Integer adresse){
		
		if(adresse == 0x00){
				String logprefix = "[INDIREKTE ADRESSIERUNG]: ";
				
				Integer indirect_address = get_RAM_Value(0x04);		//FSR
				Integer indirect_value = get_RAM_Value(indirect_address);
				
				PIC_Logger.logger.info(logprefix+"Ind. Adresse= "+indirect_address);
				PIC_Logger.logger.info(logprefix+"Ind. Wert= "+indirect_value);
				
				return indirect_value;					//TODO CHECK
		}
		else{
			return ram.getValueFromCell(adresse);
		}
	}
	
	
	
	
	
	/**
	 * Schreibt einen Wert in eine Speicherzelle
	 * 
	 * @param adresse
	 * @param value
	 * @param status_effect
	 */
	public void setSpeicherzellenWert(Integer adresse, Integer value, boolean status_effect){
		
		//Passive Beeinträchtigung des Statusregister
		//Überlauf wird abgefangen 
		if((value == 0) && status_effect) {
			status.setBit(bits.Z);				//TODO STATUS AUF GUI!???
		}
		else if(value > 255) {
			value -= 256;
			if(status_effect){
				status.setBit(bits.C);
			}
		}
		else if(value < 0) {
			value += 256;
			if(status_effect){
				status.setBit(bits.DC);
			}
		}

		
		//Adresse bei Bank 1 erhöhen
		if(ram.getBank() == Bank.BANK1){
			adresse += 0x80;							//Bank 1 startet bei 0x80h
			PIC_Logger.logger.info("BANK1: Adresse += 0x80 ==>"+Integer.toHexString(adresse));
		}
		
		
	

		//GUI AUSGABE
//		gui.show_Register(0x03, status.getValue());
//		gui.show_Register(0x83, status.getValue());
		
		
		
		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Ab hier wird auf spezielle Funktionsregister SFR überprüft, da die
		 * Veränderung dieser Register spezielle Funktionen nach sich ziehen
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		//Indirect. Adress
		if(adresse == 0x00 || adresse == 0x80){		//Indirekte Adressierung
			String logprefix = "[SPECIALFUNCTIONREGISTER]: ";
			
			Integer indirect_address = get_RAM_Value(0x04);		//FSR
			
			PIC_Logger.logger.info(logprefix+"Ind. Adresse= "+indirect_address);
			PIC_Logger.logger.info(logprefix+"Setze Wert= "+value);
			
			ram.writeValueToCell(indirect_address, value);
			gui.show_Register(indirect_address, value);			
			return;		
		}
		
		// TMR0
		else if( adresse == 0x01){
			//TODO
		}
		
		//Option_REG
		else if ( adresse == 0x81){
			//TODO
		}
		
		//Programmcounter
		else if(adresse == 0x02 || adresse == 0x82){
			setPCL(value);
		}
		
		//Status
		else if(adresse == 0x03 || adresse == 0x83){
			checkStatusActive(adresse, value);
			
			ram.writeValueToCell(0x03, value);	
			gui.show_Register(0x03, value);		
			ram.writeValueToCell(0x83, value);	
			gui.show_Register(0x83, value);		
			
			return;
			
		}
		
		//FSR
		else if( adresse == 0x04 || adresse == 0x84){	
			ram.writeValueToCell(0x04, value);		//In beide Bänke schreiben
			ram.writeValueToCell(0x84, value);	
			gui.show_Register(0x04, value);		
			gui.show_Register(0x84, value);	
			gui.showFSR(value);
			return;	
		}
		
		//Port A
		else if(adresse == 0x05){	
			gui.show_PortA(value);
		}
		
		//Tris A
		else if(adresse == 0x85){	
			gui.show_TrisA(value);
		}
		
		//Port B
		else if (adresse == 0x06){
			gui.show_PortB(value);
		}
		
		//Tris B ueberpruefen
		else if(adresse == 0x86){	
			gui.show_TrisB(value);
		}
		
		//INTCON
		else if( adresse == 0x0B || adresse == 0x8B ){
			ram.writeValueToCell(0x0B, value);		//In beide Bänke schreiben
			ram.writeValueToCell(0x8B, value);	
			gui.show_Register(0x0B, value);		
			gui.show_Register(0x8B, value);	
			return;	//TODO CHECK IF OK=
		}
		
		
		
		ram.writeValueToCell(adresse, value);	
		gui.show_Register(adresse, value);		//TODO CHECK?
	}
	
	
	
	
//	/**
//	 * Schreibt einen Wert in eine Speicherzelle und gibt diesen auf der GUI aus
//	 * 
//	 * @param adresse
//	 * @param value
//	 * @param status_effect
//	 */
//	public void setSpeicherzellenWertAndShow(Integer adresse, Integer value, boolean status_effect){
//		setSpeicherzellenWert(adresse, value, status_effect);
//		gui.show_Register(Integer.toHexString(adresse), Integer.toHexString(value));
//	}

	
	/**
	 * Überprüft beim Setzen eines Wertes, ob das Statusregisters direkt angesprochen und geändert wurde
	 *
	 * Bsp: bsf status,5
	 *
	 * @param adresse
	 * @param value
	 */
	private void checkStatusActive(Integer adresse, Integer value) {
			
			String logstring = "[Aktive Beeinträchtigung des Statusregisters]: ";
			
			//CARRY
			if( (value & 0x01) == 0x01){	//Carrybit manuell gesetzt
				status.setBit(bits.C);
				PIC_Logger.logger.info(logstring+"Set C");
			}
			else{	//Carrybit gelöscht
				status.clearBit(bits.C);
				PIC_Logger.logger.info(logstring+"Clear C");
			}
			
			
			//DC BIT
			if( (value & 0x02) == 0x02){	//DC GESETZT
				status.setBit(bits.DC);
				PIC_Logger.logger.info(logstring+"Set DC");
			}	
			else{						//DC GELÖSCHT
				status.clearBit(bits.DC);
				PIC_Logger.logger.info(logstring+"Clear DC");
			}
			
			
			//Z
			if( (value & 0x04) == 0x04){	//Z GESETZT
				status.setBit(bits.Z);
				PIC_Logger.logger.info(logstring+"Set Z");
			}	
			else{						//Z GELÖSCHT
				status.clearBit(bits.Z);
				PIC_Logger.logger.info(logstring+"Clear Z");
			}
			
			//RP0
			if( (value & 0x20) == 0x20){	//RP0 GESETZT
				status.setBit(bits.RP0);
				ram.setBank(Bank.BANK1);
				PIC_Logger.logger.info(logstring+"Switched to Bank1");
			}	
			else{						//RP0 GELÖSCHT
				status.clearBit(bits.RP0);
				ram.setBank(Bank.BANK0);
				PIC_Logger.logger.info(logstring+"Switched to Bank0");
			}
			
			gui.showStatus(status.getValue());
			
	}
	

	/**
	 * Liefert den Wert des W Registers zurück
	 * @return
	 */
	public Integer getW() {
		return w.getWert();
	}

	
	
	public Integer getLaufzeit() {
		return laufzeit;
	}
	
	public void setLaufzeit(Integer laufzeit) {
		this.laufzeit = laufzeit;
	}
	
	
	
	/**
	 * Setzt einen Wert in W und gibt diesen auf der GUI aus
	 * 
	 * @param value zu setzender Wert
	 * @param status_effect Wenn Status gesetzt werden soll
	 */
	public void setW(Integer value, boolean status_effect) {

		if((value == 0) && status_effect) {
			status.setBit(bits.Z);
			PIC_Logger.logger.info("Flag: Zero");
		}
		else if(value > 255) {
			value -= 256;
			if(status_effect){
				status.setBit(bits.C);
				PIC_Logger.logger.info("Flag: C");
			}
		}
		else if(value < 0) {
			value += 256;
			if(status_effect){
				status.setBit(bits.DC);
				PIC_Logger.logger.info("Flag: DC");
			}
		}

		this.w.setWert(value);
		gui.show_W_Register(value);	//Ausgabe auf GUI		TODO CHECK
	}


	
	/**
	 * Liefert true, wenn das gewünschte Bit gesetzt ist
	 * sonst false
	 * 
	 * @param bit
	 * @return
	 */
	public boolean getStatus(bits bit){
		if(status.getBit(bit)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	//TODO STATUSREGISTER AUF GUI ausgeben
	public void setStatus(bits bit) {
		status.setBit(bit);
		//AUSGABE GUI TODO
	}

	//TODO STATUSREGISTER AUF GUI ausgeben
	public void clearStatus(bits bit){
		status.clearBit(bit);
		//TODO ausgabe GUI
	}
	
	
	/**
	 * Liefert den Stack zurück
	 * @return
	 */
	public Stack<Integer> getStack() {
		return stack;
	}
	
	
	/**
	 * Liefert den Wert des Programmcounters zurück
	 * @return
	 */
	public Integer getPCValue() {
		return pc.getValue();
	}

	
	/**
	 * Übernimmt den value wert für den Programmcounter
	 * 
	 * @param value Neuer Programmcounterwert
	 */
	public void setPCL(Integer value) {
		pc.setWert(value);
		
		//Ausgabe auf GUI
		gui.show_PC(value);			//Extralabel
		gui.show_Register(0x02, value);	//Speicher Bank0
		gui.show_Register(0x82, value);	//Speicher Bank1
	}

	
	
	/**
	 * Testet, ob einen Integerwert auf einen bestimmten Befehl.
	 * Liefert true, wenn der Wert im Befehlsadressraum liegt, sonst false
	 * 
	 * @param command_enum
	 * @param actual_cmd
	 * @return
	 */
	private boolean isIntegerCommand(ECommands command_enum, Integer actual_cmd) {
		if(actual_cmd >= cmdTable.getAssemblerCommand().get(command_enum).getFrom()) {
			if(actual_cmd <= cmdTable.getAssemblerCommand().get(command_enum).getTo()) {
				return true;
			}
		}
		return false;

	}

	
	/**
	 * Holt den naechsten Befehl, auf den der Programmcounter zeigt
	 * 
	 * @return
	 */
	private Integer nextCommand() {
		return programmSpeicher.getValueFromCell(pc.getValue());
	}

	/**
	 * Ein Programmschritt	TODO LAUFZEITZAEHLER ERHOEHEN
	 */
	@Override
	public void nextStep() {
		stepmode = EStepmode.onestep;
	}
	

	/**
	 * Programm läuft automatisch weiter
	 */
	@Override
	public void go() {
		if(stepmode == EStepmode.go) {
			stepmode = EStepmode.hold;
		}
		else {
			stepmode = EStepmode.go;
		}
	}

	/**
	 * Simulator wird zurückgesetzt TODO Initialwerte
	 */
	@Override
	public void reset() {
		ram.initSpeicher();
		setW(0, false);
		setPCL(0);
		setPortA(0);
		
		//Status
		setSpeicherzellenWert(0x03, 0x18, false);	
		gui.show_Register(0x03, 0x18);
		
		setSpeicherzellenWert(0x83, 0x18, false);	
		gui.show_Register(0x83, 0x18);
		
		//Option
		setSpeicherzellenWert(0x81, 0xff, false);	
		gui.show_Register(0x81, 0xff);
		
		//Tris A
		setSpeicherzellenWert(0x85, 0x1F, false);
		gui.show_Register(0x85, 0x1F);
		
		//Tris B
		setSpeicherzellenWert(0x86, 0xFF, false);	
		gui.show_Register(0x86, 0xFF);
		
		gui.setFocus(parser.getCommand_source_line().get(0));
		
		stepmode = EStepmode.hold;
	}

	/**
	 * Port A mit Wert belegen
	 */
	@Override
	public void setPortA(Integer value) {
		//TODO TRIS ABFRAGE I/0
		setSpeicherzellenWert(0x05, value, false);
	}

	/**
	 * Liefert Wert von Port A zurück
	 */
	@Override
	public Integer getPortA() {
		return get_RAM_Value(0x05);
	}

	/**
	 * Tris A mit Wert belegen
	 */
	@Override
	public void setTrisA(Integer value) {
		setSpeicherzellenWert(0x85, value, false);
	}

	/**
	 * Gibt Wert von Tris A zurück
	 */
	@Override
	public Integer getTrisA() {
		return get_RAM_Value(0x85);
	}

	
	/**
	 * Speicherzelle wird direkt von der GUI verändert
	 */
	@Override
	public void change_RAM_Value(Integer adresse, Integer to_Value) {
		setSpeicherzellenWert(adresse, to_Value, false);
	}

	/**
	 * Wird von der GUI benötigt, um bei Falscheingabe den alten
	 * Wert zu erhalten
	 */
	@Override
	public Integer get_RAM_Value(Integer adresse) {
		return getSpeicherzellenWert(adresse);
	}

	@Override
	public Integer getQuartzTakt() {
		return megaHertz;
	}

	
	@Override
	public void setBitPortA(Integer bitNr) {
		setBitPort(bitNr, 0x05);
	}
	
	public void setBitPort(Integer bitNr, Integer adresse){
		Integer tris = get_RAM_Value(adresse+0x80);
		Integer port = get_RAM_Value(adresse);
		
		boolean trisBit =  PIC_Befehle.getBit(tris, bitNr);
		Integer setValue = port | (int)Math.pow(2, bitNr);
		
		//Nur verändern wenn Tris richtig gesetzt ist
		if(trisBit){
			setSpeicherzellenWert(adresse, setValue , false);
		}
	}
	

	@Override
	public void clearBitPortA(Integer bitNr) {
		clearBitPort(bitNr, 0x05);
	}
	

	private void clearBitPort(Integer bitNr, Integer adresse) {
		Integer tris = get_RAM_Value(adresse+0x80);
		Integer port = get_RAM_Value(adresse);
		
		boolean trisBit =  PIC_Befehle.getBit(tris, bitNr);
		Integer setValue = (int)Math.pow(2, bitNr);
		setValue = 255 - setValue;
		setValue = port & setValue;
		
		//Tris als input gesetzt
		if(trisBit){
			setSpeicherzellenWert(adresse, setValue , false);
		}
	}



	@Override
	public void clearBitPortB(Integer bitNr) {
		clearBitPort(bitNr, 0x06);		
	}

	@Override
	public void setBitPortB(Integer bitNr) {
		setBitPort(bitNr, 0x06);		
	}

	@Override
	public void setPortB(Integer value) {
		setSpeicherzellenWert(0x06, value, false);
	}

	@Override
	public Integer getPortB() {
		return getSpeicherzellenWert(0x06);
	}

	@Override
	public void setTrisB(Integer value) {
		setSpeicherzellenWert(0x86, value, false);
	}

	@Override
	public Integer getTrisB() {
		return getSpeicherzellenWert(0x86);
	}
}
